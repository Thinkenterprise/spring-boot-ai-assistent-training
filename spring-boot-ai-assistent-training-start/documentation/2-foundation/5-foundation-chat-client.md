# Foundation Chat Client

## Ziel

Ziel dieser Einheit ist es, den Spring AI **Chat Client** zu verstehen und zu verwenden. Der Chat Client ist die zentrale Abstraktion für die Kommunikation mit einem Chat Model und stellt ein Fluent API bereit, das eine intuitive und flexible Interaktion ermöglicht.


## Architecture

Der Chat Client kapselt den Zugriff auf ein Chat Model und sitzt als Schicht zwischen dem Service und dem eigentlichen Modell-Provider.

```text
+----------------------+
| InsuranceChatService |
+----------+-----------+
           |
           | Fluent API
           v
+----------+-----------+
|     ChatClient       |
+----------+-----------+
           |
           | delegate
           v
+----------+-----------+
|  OllamaChatModel     |
| (oder anderer Provider) |
+----------------------+
```


## Libraries

Der Chat Client wird automatisch über die Spring AI Autokonfiguration bereitgestellt, sobald ein Modell-Starter eingebunden ist. Es sind keine zusätzlichen Dependencies notwendig.


## Implementation

### Builder

Der `ChatClient` wird über `ChatClient.Builder` erstellt und als Spring Bean konfiguriert. Standardverhalten wie ein System Prompt oder Tools können einmalig hinterlegt werden:

| Methode | Beschreibung |
|---|---|
| `defaultSystem()` | System Prompt für alle Aufrufe |
| `defaultUser()` | User Prompt als Basis |
| `defaultOptions()` | Chat Options für alle Aufrufe |
| `defaultAdvisors()` | Advisors für Request/Response-Verarbeitung |
| `defaultTools()` | Registrierung von Tools |

```java
@Configuration
public class ChatClientConfiguration {

    @Bean
    public ChatClient createChatClient(ChatClient.Builder builder) {
        return builder.build();
    }
}
```

### Synchrone Aufrufe

Ein synchroner Aufruf erfolgt über die Methode `call()`. Es gibt drei Möglichkeiten, auf das Ergebnis zuzugreifen:

```java
// Direkter Zugriff auf den Text (häufigste Variante)
String content = chatClient
    .prompt(new Prompt(new UserMessage("Welche Lebensversicherungen bieten Sie an?")))
    .call()
    .content();

// Zugriff auf die komplette ChatResponse (z. B. für Tests und Monitoring)
ChatResponse response = chatClient
    .prompt(new Prompt(new UserMessage("Welche Lebensversicherungen bieten Sie an?")))
    .call()
    .chatResponse();

// Strukturierte Ausgabe als Java Bean
InsuranceProduct product = chatClient
    .prompt("Empfehle eine Lebensversicherung für einen 45-jährigen Kunden")
    .call()
    .entity(InsuranceProduct.class);
```

- `content()` – gibt direkt den Textinhalt zurück (kürzeste Form)
- `chatResponse()` – gibt die komplette `ChatResponse` zurück, inklusive Metadaten und Token-Informationen
- `entity()` – konvertiert die Modellantwort automatisch in eine Java Bean

### Asynchrone Aufrufe

Ein asynchroner Aufruf erfolgt über `stream()`. Die Antwort wird dabei Stück für Stück geliefert, was für eine reaktionsfähige UI genutzt werden kann:

```java
Flux<String> stream = chatClient
    .prompt(new Prompt(new UserMessage("Erkläre fondsgebundene Lebensversicherungen.")))
    .stream()
    .content();
```

### InsuranceChatService

```java
@Service
public class InsuranceChatService {

    private final ChatClient chatClient;

    public InsuranceChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chatService(String input) {
        return chatClient
            .prompt(new Prompt(new UserMessage(input)))
            .call()
            .content();
    }

    public ChatResponse chatServiceWithResponse(String input) {
        return chatClient
            .prompt(new Prompt(new UserMessage(input)))
            .call()
            .chatResponse();
    }
}
```


## Test

1. Anwendung starten: `mvn spring-boot:run`
2. Chat-UI aufrufen unter `http://localhost:8080`
3. Eine Frage stellen, z. B. *„Welche Lebensversicherungen bieten Sie an?"*
4. Die Antwort des Modells erscheint in der UI
