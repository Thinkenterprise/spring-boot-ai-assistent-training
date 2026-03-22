# Advanced MCP

## Ziel

Ziel dieser Einheit ist es, das **Model Context Protocol (MCP)** zu verstehen und in Spring AI zu verwenden. MCP ermöglicht es, AI-Modellen über einen standardisierten Protokoll-Server zentralisierte Fähigkeiten (Tools, Ressourcen, Prompts) bereitzustellen.


## Motivation

Das **Model Context Protocol (MCP)** wurde von **Anthropic** entwickelt und standardisiert die Art und Weise, wie Generative AI-Modelle mit externen Fähigkeiten erweitert werden.

Ohne MCP implementiert jeder AI-Assistent seine eigenen Tools direkt. Mit MCP können Unternehmen stattdessen:

- **Zentrale MCP Server** bereitstellen, die von vielen AI-Assistenten genutzt werden
- Die Kontrolle über Fähigkeiten an einer Stelle halten
- AI-Assistenten-Entwickler von der Tool-Implementierung für Unternehmensdaten entlasten

> **Hinweis:** Im Insurance Use Case könnten über einen MCP Server zentral Tools für Kundendaten, Ressourcen für Versicherungspolicies und Prompts für typische Anfragen bereitgestellt werden – nutzbar von verschiedenen AI-Assistenten im Unternehmen.


## Architecture

```text
+-------------------+    MCP Client    +-------------------+
| AI-Assistent 1    | -------------->  |                   |
+-------------------+    HTTP / IPC    |   MCP Server      |
                                       |                   |
+-------------------+    MCP Client    | - Tools           |
| AI-Assistent 2    | -------------->  | - Ressourcen      |
+-------------------+                 | - Prompts         |
                                       |                   |
+-------------------+    MCP Client    +-------------------+
| AI-Assistent n    | -------------->
+-------------------+
```

**Drei Fähigkeitstypen:**

| Typ | Beschreibung |
|---|---|
| **Tools** | Aufrufbare Funktionen – entsprechen den bekannten Spring AI Tools |
| **Ressourcen** | Informationen für die Kontext-Anreicherung (z. B. für RAG), adressierbar über URI |
| **Prompts** | Zentral verwaltete Prompt-Vorlagen, abrufbar über URI |

**Kommunikationsarten:**

- **HTTP (synchron/asynchron)** – für Remote-Kommunikation
- **IPC / Stdio** – für In-Process-Kommunikation


## Libraries

### MCP Server (Spring Boot Anwendung)

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-mcp-server-webmvc</artifactId>
</dependency>
```

### MCP Client (AI-Assistent)

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-mcp-client</artifactId>
</dependency>
```


## Implementation

### MCP Server

Der MCP Server stellt das `getCustomerDetails`-Tool über HTTP bereit, anstatt es direkt im AI-Assistenten zu implementieren:

```yaml
spring:
  ai:
    mcp:
      server:
        name: insurance-mcp-server
        version: 1.0.0
        type: SYNC
        instructions: "This server provides insurance customer details tools"
        capabilities:
          tool: true
          resource: false
          prompt: false
```

### MCP Client

Der AI-Assistent bindet den MCP Server als Client ein:

```yaml
spring:
  ai:
    mcp:
      client:
        connections:
          insurance-server:
            url: http://localhost:8090
```


## Test

1. MCP Server starten: `mvn spring-boot:run` (Port 8090)
2. AI-Assistenten starten: `mvn spring-boot:run` (Port 8080)
3. Anfrage mit Kundenbezug stellen: *„Was sind die Vertragsdaten von Max Mustermann?"*
4. Prüfen, ob der AI-Assistent das Tool über den MCP Server aufruft und die Daten korrekt einbezieht
