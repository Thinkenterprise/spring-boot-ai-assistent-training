# Enterprise Prompts

## Ziel

Ziel dieser Einheit ist es, den Einsatz von **Prompts** in Spring AI zu verstehen. Prompts sind der zentrale Mechanismus zur Steuerung des Modellverhaltens. Je besser ein Prompt formuliert ist, desto besser und relevanter ist die Antwort des Modells. Diesen Prozess nennt man **Prompt Engineering**.


## Domain 

**Prompt Engineering** bezeichnet die Kunst, Prompts so zu formulieren, dass das Modell die gewünschten Ausgaben mit hoher Qualität liefert. Statt das Modell selbst zu ändern, wird der Prompt um zusätzliche Kontext-Informationen angereichert — beispielsweise durch klare Anweisungen, rollenbasierte Vorgaben oder strukturierte Beispiele. Im Insurance-Kontext bedeutet das: Ein gut formulierter System Prompt legt fest, dass der Assistent nur Versicherungsfragen beantwortet und dabei sein Domänenwissen einsetzt. Die wichtigsten Techniken sind klare Anweisungen, rollenbasierte Prompts und Few-Shot Learning, die zusammen die Modellqualität deutlich verbessern.

Das System Prompt für den **AI Insurance Assistant** ist in der Template-Datei `systemPrompt.st` abgelegt und wurde nach Best Practices der Prompt Engineering Forschung entwickelt[1].



## Architecture

Ein **Prompt** wird durch die Klasse ``Prompt`` repräsentiert und kann mehrere ``Message``-Objekte enthalten. Diese Nachrichten werden bei einer Anfrage an das Modell gesendet — ebenso auch die Antwort des Modells, die ebenfalls als ``Message`` repräsentiert wird.

Die abstrakte ``Message``-Klasse definiert zwei zentrale Attribute:

- `content` – Der textuelle Inhalt der Nachricht
- `messageType` – Der Typ der Nachricht (USER, SYSTEM, ASSISTANT, TOOL)

Die folgende Tabelle zeigt die verschiedenen konkreten Implementierungen: 


| Typ | Klasse | Beschreibung |
|---|---|---|
| `USER` | `UserMessage` | Anfrage des Benutzers |
| `SYSTEM` | `SystemMessage` | Verhaltensanweisung für das Modell |
| `ASSISTANT` | `AssistantMessage` | Antwort des Modells (für Konversationshistorie) |
| `TOOL` | `ToolResponseMessage` | Antwort eines ausgeführten Tools |


Diese unterscheidung sind notwendig, damit das Modell und Spring AI selbst die Nachrichten richtig erkennen und behandeln kann. 

## Implementation

### User Prompt

Ein einfacher **User Prompt** enthält die konkrete Anfrage des Anwenders:

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

**System Prompts** definieren das grundlegende Verhalten des AI-Assistenten. Sie werden typischerweise einmalig im `ChatClient.Builder` konfiguriert und gelten für alle Anfragen:

```java
List<Message> messages = List.of(
    new SystemMessage("Du bist ein Experte für Lebensversicherungen des Insurance Unternehmens."),
    new UserMessage("Welche Vorteile hat eine fondsgebundene Lebensversicherung?")
);
Prompt prompt = new Prompt(messages);
```

> **Hinweis:** System Prompts sollten rollen- und domänenspezifisch formuliert sein. Im Insurance Use Case legen sie fest, dass der AI-Assistent ausschließlich Fragen rund um Versicherungsprodukte beantwortet.


### Prompt Templates

Spring AI verwendet die Template-Engine **StringTemplate 4 (ST4)** für dynamische Prompts. Platzhalter werden mit ``{variable}`` definiert:

```
Guten Tag, {customerName}. Ihre Police {policyNumber} läuft noch {daysRemaining} Tage.
```

**Drei Template-Typen:**

- `PromptTemplate` – erzeugt eine `UserMessage`
- `SystemPromptTemplate` – erzeugt eine `SystemMessage`
- `AssistantPromptTemplate` – erzeugt eine `AssistantMessage`

**Beispiel PromptTemplate:**

```java
String answerClientParameter = chatClient.prompt(PromptTemplate.builder()
															    .template("Hallo ich bin der Makler mit dem Namen {brokerName}")
															    .variables(Map.of("brokerName", "Best Broker Ever"))
															    .build()
																.create())
																.call()
																.content();
```

**Beispiel SystemPromptTemplate aus einer Datei:**

```java
@Value("classpath:/systemPrompt.st")
private Resource systemPromptResource;

private Prompt createSystemPrompt() {

        return SystemPromptTemplate.builder()
                                    .resource(systemPromptResource)
                                    .variables(Map.of("assistentName", "AI Insurance Assistent"))
                                    .build()
                                    .create();
    }
```

## Test

1. Anwendung starten: `mvn spring-boot:run`
2. Chat-UI aufrufen unter `http://localhost:8080`
3. Prüfen, ob sich der AI-Assistent gemäß dem System Prompt verhält (z. B. nur Versicherungsfragen beantwortet)


## Best Practices & Referenzen

<a id="1">[1] Das System Prompt wurde unter Berücksichtigung von Prompt Engineering Best Practices entwickelt. Wichtige Ressourcen zum Thema:</a>

- [OpenAI Prompt Engineering Guide](https://platform.openai.com/docs/guides/prompt-engineering) — Offizielle Best Practices von OpenAI
- [Anthropic Prompt Engineering Guide](https://docs.anthropic.com/en/docs/build-a-bot) — Best Practices von Anthropic für den Umgang mit Claude
- [DeepLearning.AI Short Courses](https://www.deeplearning.ai/) — Praktische Kurse zu Prompt Engineering
