# Enterprise Prompts

## Ziel

Ziel dieser Einheit ist es, den Einsatz von **Prompts** in Spring AI zu verstehen. Prompts sind der zentrale Mechanismus zur Steuerung des Modellverhaltens. Je besser ein Prompt formuliert ist, desto besser und relevanter ist die Antwort des Modells. Diesen Prozess nennt man **Prompt Engineering**.


## Architecture

Das Spring AI Prompt-Modell unterscheidet mehrere Nachrichtentypen:

| Typ | Klasse | Beschreibung |
|---|---|---|
| `USER` | `UserMessage` | Anfrage des Benutzers |
| `SYSTEM` | `SystemMessage` | Verhaltensanweisung für das Modell |
| `ASSISTANT` | `AssistantMessage` | Antwort des Modells (für Konversationshistorie) |
| `TOOL` | `ToolResponseMessage` | Antwort eines ausgeführten Tools |


## Implementation

### User Prompt

Ein einfacher User Prompt enthält die konkrete Anfrage des Anwenders:

```java
Prompt prompt = new Prompt("Welche Lebensversicherungen bieten Sie an?");

// Mit expliziter UserMessage
UserMessage userMessage = new UserMessage("Kann die Versicherungsdauer verlängert werden?");
Prompt prompt = new Prompt(userMessage);

// Mehrere Nachrichten
List<Message> messages = List.of(
    new UserMessage("Mein Kunde ist 45 Jahre alt."),
    new UserMessage("Welche Lebensversicherung empfehlen Sie?")
);
Prompt prompt = new Prompt(messages);
```

### System Prompt

System Prompts definieren das grundlegende Verhalten des AI-Assistenten. Sie werden typischerweise einmalig im `ChatClient.Builder` konfiguriert und gelten für alle Anfragen:

```java
List<Message> messages = List.of(
    new SystemMessage("Du bist ein Experte für Lebensversicherungen des Insurance Unternehmens."),
    new UserMessage("Welche Vorteile hat eine fondsgebundene Lebensversicherung?")
);
Prompt prompt = new Prompt(messages);
```

> **Hinweis:** System Prompts sollten rollen- und domänenspezifisch formuliert sein. Im Insurance Use Case legen sie fest, dass der AI-Assistent ausschließlich Fragen rund um Versicherungsprodukte beantwortet.

### Conversation History

Die **Conversation History** enthält den bisherigen Gesprächsverlauf und ermöglicht kontextbewusstes Antworten:

```java
List<Message> conversationHistory = List.of(
    new UserMessage("Welche Lebensversicherungen bieten Sie an?"),
    new AssistantMessage("Wir bieten kapitalbildende und fondsgebundene Lebensversicherungen an."),
    new UserMessage("Was sind die Unterschiede zwischen den beiden?")
);
Prompt prompt = new Prompt(conversationHistory);
```

### Prompt Templates

Spring AI verwendet die Template-Engine **StringTemplate 4 (ST4)** für dynamische Prompts. Platzhalter werden mit `{variable}` definiert:

```
Guten Tag, {customerName}. Ihre Police {policyNumber} läuft noch {daysRemaining} Tage.
```

**Drei Template-Typen:**

- `PromptTemplate` – erzeugt eine `UserMessage`
- `SystemPromptTemplate` – erzeugt eine `SystemMessage`
- `AssistantPromptTemplate` – erzeugt eine `AssistantMessage`

**Beispiel PromptTemplate:**

```java
String templateText = """
    Sie haben noch {remainingQuestions} von 10 Fragen übrig.
    Meine Frage: {question}
    """;

Map<String, Object> parameters = Map.of(
    "remainingQuestions", 7,
    "question", "Welche Lebensversicherungen bieten Sie für Kunden ab 50 an?"
);

PromptTemplate promptTemplate = new PromptTemplate(templateText);
Prompt prompt = promptTemplate.create(parameters);
```

**Beispiel SystemPromptTemplate aus einer Datei:**

```java
@Value("classpath:/systemPrompt.st")
private Resource systemPromptResource;

public Prompt createSystemPrompt() {
    SystemPromptTemplate systemPromptTemplate =
        new SystemPromptTemplate(systemPromptResource);

    Map<String, Object> parameters = Map.of(
        "maxQuestions", 10,
        "toolCustomerDetails", "get_CustomerDetails"
    );

    return systemPromptTemplate.create(parameters);
}
```


## Test

1. Anwendung starten: `mvn spring-boot:run`
2. Chat-UI aufrufen unter `http://localhost:8080`
3. Prüfen, ob sich der AI-Assistent gemäß dem System Prompt verhält (z. B. nur Versicherungsfragen beantwortet)
4. Eine Folgefrage stellen und prüfen, ob der Kontext aus der Conversation History korrekt einbezogen wird
