# Spring Boot AI Foundation Chat Client Training

## Ziel

Ziel dieser Einheit ist es, den Spring AI **Chat Client** zu verstehen und zu verwenden. Der Chat Client ist die zentrale Abstraktion für die Kommunikation mit einem Chat Model und stellt ein Fluent API bereit, das eine intuitive und flexible Interaktion ermöglicht.

**Didaktische Reihenfolge im Training:** Wir starten mit dem **Model**, gehen dann zum **ChatClient**, anschließend zum **Controller** und zum Schluss zum **User Interface**.


## Architecture

In dieser Einheit betrachten wir ausschließlich den ``ChatClient`` als zentrale Zugriffsschicht auf das Modell. Er kapselt das ``ChatModel`` und bietet ein Fluent API, mit dem Prompts, Optionen, Tools und weitere Defaults konsistent verwendet werden können.

Der ``ChatClient`` wird in der Konfiguration als Bean erstellt und typischerweise im Service verwendet, damit Controller und spätere UI nicht direkt mit Modell-Details arbeiten müssen.

Die typische Schicht-Architektur einer Anwendung mit Spring AI:

```text
      User Interface
      (Web UI)
          ↓
      Controller
      (REST Endpoints)
          ↓
      Service
      (Business Logic)
          ↓
      **Chat Client**
      **(Abstraction über Content Model API)**
          ↓
      Chat Model
      (Provider-spezifische Implementierung)
```

**Fokus dieser Einheit:** Wir beschäftigen uns mit dem **Chat Client** als primäre Schnittstelle für die Kommunikation mit dem ``ChatModel``.


## Libraries

Für die Verwendung des ``ChatClient`` sind keine weiteren Bibliotheken notwendig. Mit dem Model-Starter (z.B. `spring-ai-starter-model-ollama`) werden automatisch alle abhängigen Bibliotheken mitgeladen — darunter die `spring-ai-client-chat.jar`, in der der ``ChatClient`` und das zugehörige Fluent API definiert sind.



## Implementation

### Builder

Der ``ChatClient`` wird über den ``ChatClient.Builder`` erstellt und als Spring Bean bereitgestellt. Der ``ChatClient.Builder`` selbst wird von der Spring AI Autoconfiguration erzeugt. Der ``ChatClient.Builder`` verwendet dabei das vom Model-Starter bereitgestellte ``ChatModel``.

**Standardverhalten** wie ein System Prompt oder Tools können einmalig hinterlegt werden:

> **Hinweis:** Die Begriffe wie **System Prompt**, **Tools**, **Advisors** und **Options** werden in den folgenden Kapiteln schrittweise im Detail erklärt.

| Methode | Beschreibung |
|---|---|
| `defaultSystem()` | System Prompt für alle Aufrufe |
| `defaultUser()` | User Prompt als Basis |
| `defaultOptions()` | Chat Options für alle Aufrufe |
| `defaultAdvisors()` | Advisors für Request/Response-Verarbeitung |
| `defaultTools()` | Registrierung von Tools |

Das folgende Beispiel erzeugt einen ``ChatClient`` als Spring Bean und setzt mit `defaultOptions(...)` eine Standardkonfiguration, bei der Antworten auf maximal 512 Tokens begrenzt werden.

```java
@Configuration
public class InsuranceAssistentConfiguration {
  
    @Bean
    public ChatClient createClient(ChatClient.Builder builder) {
        var chatClient = builder.defaultOptions(ChatOptions.builder().
                                                            maxTokens(512)
                                                            .build())
                                .build();
        return chatClient;
    }
}
```

> Sollen mehrere ``ChatModel`` verwendet werden und von unterschiedlichen ``ChatClient`` bereitgestellt werden, müssen die `ChatClient.Builder` über eine eigene Autoconfiguration bereitgestellt werden. Das ist ähnlich, wie bei der Verwendung von mehreren Datenbanken. 

### Synchrone Aufrufe

Ein synchroner Aufruf erfolgt über die Methode `call()`. Es gibt drei Möglichkeiten, auf das Ergebnis zuzugreifen:

```java
// Direkter Zugriff auf den Text (häufigste Variante)
String content = chatClient
    .prompt("Hello")
    .call()
    .content();

// Zugriff auf die komplette ChatResponse (z. B. für Tests und Monitoring)
ChatResponse response = chatClient
    .prompt("Hello")
    .call()
    .chatResponse();

// Strukturierte Ausgabe als Java Bean
InsuranceProduct product = chatClient
    .prompt("Hello")
    .call()
    .entity(InsuranceProduct.class);
```

- `content()` – gibt direkt den Textinhalt zurück (kürzeste Form)
- `chatResponse()` – gibt die komplette `ChatResponse` zurück, inklusive Metadaten und Token-Informationen
- `entity()` – konvertiert die Modellantwort automatisch in eine Java Bean

### Asynchrone Aufrufe

Ein asynchroner Aufruf erfolgt über `stream()`. Die Antwort wird dabei Stück für Stück geliefert, was für eine reaktionsfähige UI genutzt werden kann. Analog zu `call()` gibt es auch bei `stream()` mehrere Zugriffsmöglichkeiten:

```java
// Direkter Zugriff auf den Textstrom
Flux<String> stream = chatClient
    .prompt(new Prompt(new UserMessage("Erkläre fondsgebundene Lebensversicherungen.")))
    .stream()
    .content();

// Zugriff auf den vollständigen ChatResponse-Strom (z. B. inkl. Metadaten)
Flux<ChatResponse> responses = chatClient
    .prompt(new Prompt(new UserMessage("Erkläre fondsgebundene Lebensversicherungen.")))
    .stream()
    .chatResponse();
```

- `stream().content()` – liefert einen `Flux<String>` mit den Textfragmenten
- `stream().chatResponse()` – liefert einen `Flux<ChatResponse>` inkl. Metadaten je Fragment

Für das asynchrone API gibt es keinen direkten `stream().entity(...)`-Aufruf wie bei `call().entity(...)`. Wenn strukturierte Daten benötigt werden, wird der Stream in der Regel zuerst als Text (`Flux<String>`) verarbeitet und anschließend manuell in ein Objekt konvertiert.

### InsuranceChatService

In der Praxis wird der ``ChatClient`` in der Regel nicht direkt vom Controller verwendet, sondern über einen Service gekapselt. In unserem Fall übernimmt der ``InsuranceChatService`` diese Aufgabe. Er erhält den ``ChatClient`` per Constructor Injection und stellt fachliche Methoden bereit, die intern das Fluent API des ``ChatClient`` nutzen. Damit bleibt die Kommunikation mit dem Modell sauber von der Business-Logik getrennt.

```java
@Service
public class InsuranceChatService {

    private final ChatClient chatClient;

    public InsuranceChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chatService(String input) {
        return chatClient
            .prompt(input)
            .call()
            .content();
    }

    public ChatResponse chatServiceWithResponse(String input) {
        return chatClient
            .prompt(input)
            .call()
            .chatResponse();
    }
}
```


## Test

1. Ollama starten und das Modell laden: `ollama run llama3.2`
2. Anwendung starten: `mvn spring-boot:run`
3. Prüfen, ob auf der Konsole die Antworten für `Hello` ausgegeben werden

Erwartung: Beim Start der Anwendung werden nacheinander Antworten aus den Aufrufen über ``ChatModel``, ``ChatClient`` und den Service ausgegeben.
