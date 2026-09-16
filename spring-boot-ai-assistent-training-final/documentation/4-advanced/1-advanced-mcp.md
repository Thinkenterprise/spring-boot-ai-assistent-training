# Advanced MCP

## Ziel

In dieser Übung wird das **Model Context Protocol (MCP)** eingeführt. Der AI-Assistent wird als **MCP Client** konfiguriert und bezieht die Tool-Definitionen automatisch von einem laufenden MCP Server. Ziel ist es zu verstehen, wie Spring AI den MCP Client integriert und wie Tools über das MCP Protokoll standardisiert vom Server bezogen und im `ChatClient` verwendet werden.

> **Hinweis:** Der MCP Server wird als laufende Instanz auf Port 8081 vorausgesetzt. Die Implementierung des MCP Servers ist in einem separaten Projekt beschrieben.

## Motivation

Ohne MCP implementiert jeder AI-Assistent seine eigenen Tools direkt. Das führt zu Redundanz: Dieselben Kundendaten-Tools müssen in jedem Assistenten neu implementiert und gewartet werden.

Das **Model Context Protocol (MCP)** wurde von **Anthropic** entwickelt und standardisiert den Zugang zu Fähigkeiten für Generative AI Modelle. Es ermöglicht Unternehmen, eigene **MCP Server** bereitzustellen, die dann von einer Vielzahl von AI-Assistenten genutzt werden können:

- Unternehmen behalten die **zentrale Kontrolle** über die Bereitstellung von Tools, Ressourcen und Prompts
- AI-Assistenten müssen keine Tools mehr selbst implementieren, um auf Unternehmensressourcen zuzugreifen
- **Wiederverwendbarkeit** und konsistente Bereitstellung von Unternehmensdaten

**Beispiele für öffentliche MCP Server:** GitHub (Repository-Informationen), Slack (Kommunikationsdaten), Google Drive (Dokumente), PostgreSQL (Datenbankzugriff).

Im Insurance Use Case werden zentral Tools für Kundendaten über einen MCP Server bereitgestellt — nutzbar von verschiedenen AI-Assistenten im Unternehmen.

## Architektur

Der **MCP Server** stellt Fähigkeiten zur Verfügung. Der **MCP Client** greift auf diese Fähigkeiten zu. Die Kommunikation kann auf verschiedene Arten erfolgen:

| Kommunikationsart | Beschreibung |
|---|---|
| **HTTP synchron (SYNC)** | Remote-Kommunikation, blockierend |
| **HTTP asynchron (ASYNC)** | Remote-Kommunikation, nicht-blockierend |
| **IPC / Stdio** | In-Process-Kommunikation im selben Prozess |

Die drei **Server-Fähigkeitstypen** des MCP Protokolls (Server → Client):

| Typ | Beschreibung |
|---|---|
| **Tools** | Aufrufbare Funktionen — entsprechen den bekannten Spring AI Tools |
| **Ressourcen** | Informationen für die Kontext-Anreicherung (z. B. für RAG), adressierbar über URI |
| **Prompts** | Zentral verwaltete Prompt-Vorlagen, abrufbar über URI |

### Client-Fähigkeiten

Neben dem Zugriff auf Server-Fähigkeiten besitzt der MCP Client eigene Fähigkeiten, auf die der Server zugreifen kann (Server → Client Kommunikation):

| Fähigkeit | Beschreibung |
|---|---|
| **Sampling** | Der Server delegiert einen LLM-Aufruf an den Client. Nützlich wenn der Server selbst keinen direkten Zugang zu einem Modell hat |
| **Logging** | Der Server sendet Log-Nachrichten an den Client — ermöglicht zentrales Logging aus der Server-Logik heraus |
| **Progress** | Der Server sendet Fortschrittsmeldungen an den Client bei lang laufenden Operationen |
| **Elicitation** | Der Server fragt den Client (und damit den Nutzer) nach zusätzlichen Eingaben während einer Tool-Ausführung |
| **Tools-Change** | Der Server benachrichtigt den Client, wenn sich seine Tool-Liste ändert — der Client invalidiert daraufhin seinen Cache |
| **Prompts-Change** | Der Server benachrichtigt den Client, wenn sich seine Prompt-Liste ändert |
| **Resources-Change** | Der Server benachrichtigt den Client, wenn sich seine Ressourcen-Liste ändert |

Spring AI registriert diese Client-Fähigkeiten automatisch über die `McpClientAutoConfiguration`, sofern eine `ClientMcpSyncHandlersRegistry` im Application Context vorhanden ist:

```java
// McpClientAutoConfiguration — automatisch durch Spring AI konfiguriert
spec.sampling(samplingRequest -> registry.handleSampling(...))
    .elicitation(elicitationRequest -> registry.handleElicitation(...))
    .loggingConsumer(logMessage -> registry.handleLogging(...))
    .progressConsumer(progress -> registry.handleProgress(...))
    .toolsChangeConsumer(newTools -> registry.handleToolListChanged(...))
    .promptsChangeConsumer(newPrompts -> registry.handlePromptListChanged(...))
    .resourcesChangeConsumer(newResources -> registry.handleResourceListChanged(...));
```

```text
+---------------------+   listTools() / callTool()   +---------------------+
| AI-Assistent        | ---------------------------> |   MCP Server        |
| (MCP Client)        |                              |                     |
|                     | <--- sampling, logging,  --- | - getCustomerDetails|
| SyncMcpToolCallback |      progress, elicitation   |   (Tool)            |
| Provider            | <--- tools/prompts/resources |                     |
+---------------------+       -change notifications  +---------------------+
```

## Libraries

```xml
<!-- Advanced MCP Support -->
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-mcp-client</artifactId>
</dependency>
```

## Implementation

### MCP Client — Konfiguration (application.yaml)

Der MCP Client ist im `mcp`-Profil konfiguriert. `initialized: false` verhindert, dass Spring beim Anwendungsstart sofort eine Verbindung aufbaut — der AI-Assistent startet dadurch auch dann, wenn der MCP Server noch nicht läuft:

```yaml
---
spring:
  config:
    activate:
      on-profile: mcp
  ai:
    mcp:
      client:
        enabled: true
        initialized: false
        name: insurance-company-tool-mcp-client
        version: 1.0.0
        request-timeout: 30s
        type: SYNC
        streamable-http:
          connections:
            webmvc-mcp-server:
              url: http://localhost:8081
```

Unter `connections` wird der Name des MCP Servers (`webmvc-mcp-server`) als Schlüssel verwendet. Für jede Connection erstellt Spring AI automatisch einen `McpSyncClient`.

> **Hinweis:** In Spring AI 2.0.0 wurde `sse.connections` durch `streamable-http.connections` ersetzt. Das alte `sse`-Property ist als `@Deprecated` markiert und wird in einer künftigen Version entfernt.

### MCP Client — ChatClient Konfiguration

Der `SyncMcpToolCallbackProvider` wird automatisch von Spring als Bean erstellt und enthält alle Tools des MCP Servers. In der `InsuranceAssistantConfiguration` wird er über das `ToolCallbackProvider`-Interface injiziert und dem `ChatClient` übergeben — das bisherige direkte Tool `InsuranceAssistantCustomerDetailsTool` entfällt damit:

```java
@Bean
public ChatClient createClient(ChatClient.Builder chatClientBuilder,
        MessageChatMemoryAdvisor messageChatMemoryAdvisor,
        ToolCallbackProvider tools,                          // SyncMcpToolCallbackProvider
        RetrievalAugmentationAdvisor retrievalAugmentationAdvisor) {

    return chatClientBuilder
            .defaultOptions(createChatOptions())
            .defaultSystem(createSystemPrompt().toString())
            .defaultAdvisors(messageChatMemoryAdvisor)
            .defaultAdvisors(a -> a.param(ChatMemory.CONVERSATION_ID, "InsuranceAssistent"))
            .defaultTools(tools)                             // MCP Tools automatisch verfügbar
            .defaultToolContext(new HashMap<>(Map.of("session", "No Id")))
            .defaultAdvisors(retrievalAugmentationAdvisor)
            .build();
}
```

Spring AI holt die Tool-Liste automatisch vom MCP Server (`listTools()`), sobald der `ChatClient` einen Request verarbeitet. Ruft das Modell das Tool auf, delegiert `SyncMcpToolCallback.call()` den Aufruf transparent über den `McpSyncClient` an den MCP Server.

## Test

1. Sicherstellen, dass der MCP Server auf Port 8081 läuft
2. AI-Assistenten mit dem `mcp`-Profil starten (`spring.profiles.active=mcp`)
3. Über die Web-Oberfläche (`http://localhost:8080`) eine Anfrage mit Kundenbezug stellen, z. B. *„Was sind die Vertragsdaten von Max Mustermann?"*
4. Im Log des AI-Assistenten prüfen, dass `listTools()` die Tool-Definition vom MCP Server geladen hat
5. Prüfen, dass die Antwort des AI-Assistenten die Kundendaten korrekt enthält
