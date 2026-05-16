# Enterprise Memory

## Ziel

Das Ziel dieser Einheit ist es, **Chat Memory** in Spring AI zu verstehen und einzusetzen. Chat Memory ermöglicht es, den Konversationsverlauf zwischen mehreren Anfragen zu speichern und dem LLM zur Verfügung zu stellen — ähnlich einer **Web Session**. Ohne Memory ist eine AI-Anwendung zustandslos: Das Modell kennt die vorherigen Anfragen und Antworten nicht. Mit Memory können Informationen über den gesamten Gesprächsverlauf hinweg konsistent verwendet werden.


## Domain

Im Insurance Use Case profitieren wir von Memory auf mehreren Ebenen:

- Der AI-Assistent kann sich den **Namen des Maklers** merken und ihn persönlich ansprechen
- Der Assistent kann sich **Kundendaten und Produktinformationen** über mehrere Fragen hinweg merken und darauf Bezug nehmen, ohne diese wiederholt abzufragen
- Mehrere **Chat Sessions** können isoliert voneinander geführt werden, jede mit ihrer eigenen **Conversation ID**

Dies ermöglicht ein kontextbewusstes und personalisiertes Gesprächserlebnis.


## Architecture

Spring AI stellt eine **`ChatMemory`** Abstraktion bereit, die Nachrichten speichert und verwaltet. Der Speicher arbeitet über **Conversation IDs**, vergleichbar mit Session IDs in Web-Anwendungen.

**Zentrale Komponenten:**

| Komponente | Beschreibung |
|---|---|
| `ChatMemory` | Abstraktion für Speicherverwaltung |
| `MessageWindowChatMemory` | Standard-Implementierung mit konfiguriertem Nachrichtenfenster |
| `ChatMemoryRepository` | Persistierungs-Layer (in-memory oder Datenbank) |
| `MessageChatMemoryAdvisor` | Integriert Memory in den ChatClient |

**Wichtige Konzepte:**

- **Conversation ID:** Eindeutige Kennung pro Gesprächskanal (ähnlich Session ID). Alle Nachrichten werden über diese ID verwaltet.
- **Message Window:** Nur die letzten N Nachrichten werden im Speicher behalten, um das Context Window nicht zu überlasten.
- **Persistence:** Verschiedene `ChatMemoryRepository`-Implementierungen ermöglichen unterschiedliche Speichervarianten (in-memory, Datenbank, Cache).


## Konfiguration

Spring AI stellt über **`ChatMemoryAutoConfiguration`** automatisch ein `ChatMemory` mit einem `InMemoryChatMemoryRepository` zur Verfügung. Dies ist ideal für Entwicklung und Testing.

```java
@Bean
public ChatMemory createChatMemory() {
    return new MessageWindowChatMemory();
}
```

Das Memory wird dem `ChatClient` über einen **`MessageChatMemoryAdvisor`** hinzugefügt:

```java
@Bean
public ChatClient createClient(ChatClient.Builder builder, ChatMemory chatMemory) {
    var chatClient = builder
        .defaultOptions(createChatOptions())
        .defaultSystem(createSystemPrompt().toString())
        .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
        .build();
    return chatClient;
}
```

> **Hinweis:** Eine Standard-Conversation ID muss gesetzt werden, sonst tritt zur Laufzeit ein Fehler auf.


## Implementation

Das Memory wird über den `MessageChatMemoryAdvisor` in den ChatClient integriert. Beim Aufruf des Modells kann die Conversation ID übergeben oder überschrieben werden.

**Standard Conversation ID in der Konfiguration:**

```java
.defaultAdvisors(a -> a.param(ChatMemory.CONVERSATION_ID, "default-conversation"))
```

**Dynamische Conversation ID beim Aufruf:**

Die Frontend-Anwendung verwaltet mehrere Chat Sessions und sendet die Session ID mit jedem Request. Im Service wird diese ID verwendet, um die Konversation zu isolieren:

```java
public String chatService(String input, String conversationId) {
    return chatClient
        .prompt(input)
        .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
        .call()
        .content();
}
```

**Memory Repository Optionen:**

Spring AI bietet mehrere `ChatMemoryRepository`-Implementierungen:

| Repository | Speicher | Persistent | Use Case |
|---|---|---|---|
| `InMemoryChatMemoryRepository` | RAM | ❌ Nein | Entwicklung, Testing, Single-Server |
| `JdbcChatMemoryRepository` | PostgreSQL, MySQL, etc. | ✅ Ja | Enterprise, Multi-Server |
| `CassandraChatMemoryRepository` | Cassandra | ✅ Ja | High-Scale, Auditing |
| `Neo4jChatMemoryRepository` | Neo4j | ✅ Ja | Graph-basierte Relationen |

**`MessageWindowChatMemory` Konfiguration:**

```java
MessageWindowChatMemory.builder()
    .messageWindowSize(20)  // Standardwert: 20 Nachrichten
    .build();
```

Das Memory behält nur die letzten N Nachrichten im Context Window, ältere Nachrichten werden verworfen.


## Test

1. Anwendung starten: `mvn spring-boot:run`
2. Chat-UI aufrufen unter `http://localhost:8080`
3. **Test 1 — Same Session:** Ihren Namen eingeben → Fragen Sie nach Ihrem Namen → Der Assistent sollte sich erinnern
4. **Test 2 — Different Session:** Neue Konversation erstellen → Nach Ihrem Namen fragen → Der Assistent sollte den Namen NICHT kennen (isolierte Conversation ID)
5. Wechsel zwischen Sessions prüfen → Memory sollte konversationsspezifisch sein
