# Enterprise Tools - Context

## Ziel

Das Ziel dieser Einheit ist es, den **Tool Context** zu verstehen und zu nutzen. Der Tool Context ermöglicht es, zusätzliche Informationen zwischen dem AI-Assistenten und den Tools auszutauschen — beispielsweise Session-Daten, Benutzeridentifikationen oder andere Kontextinformationen, die Tools für ihre Verarbeitung benötigen.


## Domain

Der **Tool Context** ist ein Mechanismus, um Kontextinformationen an Tools weiterzugeben. Im Insurance Use Case nutzen wir den Context, um den **Namen oder die ID des Maklers** an die Tools zu übergeben. Auf diese Weise können Tools Datenbankzugriffe im Namen des Maklers durchführen und die Zugriffsrechte korrekt validieren.

Beispiel:
- Ein Tool benötigt die `session` oder `brokerId`, um Kundendaten für den richtigen Makler zu laden
- Der Context wird vom ChatClient an das Tool übergeben
- Das Tool nutzt diese Informationen, um korrekt zu arbeiten


## Architecture

Der **Tool Context** wird als `Map<String, Object>` definiert und beim ChatClient entweder als Default oder pro Aufruf gesetzt. Spring AI übergibt diese Informationen an jedes Tool über den `ToolContext` Parameter.

**Ablauf:**

1. **Context Definition** in der Konfiguration (Default) oder beim Aufruf
2. **Context Übergabe** an den ChatClient via `defaultToolContext()` oder `toolContext()`
3. **Context Zugriff** in der Tool-Methode über `ToolContext context` Parameter
4. **Context Nutzung** in der Tool-Logik zur korrekten Datenverarbeitung

**Schaubild:**

```text
+------------------+
| ChatClient       |
| Context: {       |
|  session: "123"  |  ← defaultToolContext() oder toolContext()
| }                |
+--------+----------
         |
         | übergibt Context
         v
+------------------+
| Tool-Methode     |
| (ToolContext)    |  ← Empfängt Context über Parameter
+------------------+
```


## Konfiguration

Der **defaultToolContext** wird einmalig in der `ChatClient`-Konfiguration gesetzt und gilt für alle Tool-Aufrufe:

```java
@Bean
public ChatClient createClient(ChatClient.Builder builder, ChatMemory chatMemory) {
    var chatClient = builder
        .defaultOptions(createChatOptions())
        .defaultSystem(createSystemPrompt().toString())
        .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
        .defaultToolContext(new HashMap<>())  // ← Erforderlich für ToolContext
        .build();
    return chatClient;
}
```

> **Wichtig:** Ein `defaultToolContext` MUSS gesetzt werden, sonst wirft Spring AI einen Fehler beim Tool-Aufruf.

Alternativ kann der Context auch beim **Aufruf** überschrieben werden, wenn sich der Context pro Anfrage ändert (z.B. unterschiedliche Sessions).


## Implementation

### Tool mit Context-Parameter

In jedem Tool kann über einen **`ToolContext`** Parameter auf die übergebenen Kontextinformationen zugegriffen werden:

```java
@Tool(name = "getCustomerDetails", description = "Ermittelt Kundendaten eines Kunden")
public Customer getCustomerDetails(
    @ToolParam(required = true, description = "Name des Kunden") String name, 
    ToolContext context) {
    
    String sessionId = context.getContext().get("session").toString();
    logger.info("Tool aufgerufen für Session: " + sessionId);
    return insuranceCustomerService.getCustomerDetails(name);
}
```

### Context-Konfiguration

Der **`defaultToolContext()`** MUSS immer in der `ChatClient`-Konfiguration gesetzt werden. Dies ist die Basis für alle Tool-Aufrufe:

```java
@Bean
public ChatClient createClient(ChatClient.Builder builder, ChatMemory chatMemory) {
    var chatClient = builder
        .defaultOptions(createChatOptions())
        .defaultSystem(createSystemPrompt().toString())
        .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
        .defaultToolContext(new HashMap<>())  // ← ERFORDERLICH!
        .build();
    return chatClient;
}
```

### Context pro Aufruf überschreiben

Falls sich der Context pro Anfrage ändern muss (z.B. unterschiedliche Session-IDs), kann der **Default via `.toolContext()`** überschrieben werden:

```java
public String chatService(String input, String conversationId) {
    return chatClient
        .prompt(input)
        .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
        .toolContext(new HashMap<>(Map.of("session", conversationId)))  // ← Überschreibt default
        .call()
        .content();
}
```

Diese Variante ist empfohlen, wenn sich die Session pro Konversation ändert.



## Test

1. Anwendung starten: `mvn spring-boot:run`
2. Anfrage mit Kundenbezug stellen, z. B.: *„Was sind die Vertragsdaten von Max Mustermann?"*
3. Im Log prüfen, ob der Tool-Aufruf mit der Session ID protokolliert wurde: `Tool aufgerufen für Session: ...`
4. Verschiedene Sessions testen → Jede Session sollte mit ihrer eigenen Context-ID arbeiten

