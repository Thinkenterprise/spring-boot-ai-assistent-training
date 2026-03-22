# Foundation Controller

## Ziel

Ziel dieser Einheit ist es, einen Spring MVC REST Controller zu implementieren, der als Schnittstelle zwischen der Web-UI und dem AI-Assistenten dient. Der Controller nimmt Anfragen des Benutzers entgegen und gibt die Antworten des Chat Models zurück.


## Architecture

Der Controller bildet die mittlere Schicht zwischen dem Browser und der Spring AI Logik. Er delegiert die eigentliche Modellkommunikation an einen `ChatService`.

```text
+--------------------+      HTTP POST      +----------------------+
| Browser / chat.html| -----------------> | ChatController       |
|                    | <----------------- | @RestController      |
+--------------------+      JSON          +----------+-----------+
                                                     |
                                                     | delegiert
                                                     v
                                          +----------+-----------+
                                          | InsuranceChatService |
                                          | ChatClient           |
                                          +----------------------+
```


## Implementation

### ChatController

Der `ChatController` stellt einen REST-Endpunkt bereit. Die Benutzeranfrage wird als Request-Parameter entgegengenommen und die Antwort des Modells als einfacher String zurückgegeben.

```java
@RestController
@RequestMapping("/api")
public class ChatController {

    private final InsuranceChatService chatService;

    public ChatController(InsuranceChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatService.chatService(message);
    }
}
```

### InsuranceChatService

Der Service kapselt den Zugriff auf den `ChatClient` und stellt die eigentliche AI-Kommunikation bereit.

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
}
```


## Configuration

Die Basis-URL und der Anwendungsname werden weiterhin in `application.yaml` konfiguriert:

```yaml
spring:
  application:
    name: spring-ai-assistent
```


## Test

1. Anwendung starten: `mvn spring-boot:run`
2. Endpunkt aufrufen (z. B. mit curl):

```bash
curl -X POST "http://localhost:8080/api/chat?message=Welche+Lebensversicherungen+bieten+Sie+an?"
```

3. Die Antwort des Modells wird als Text zurückgegeben.
4. Alternativ: Browser öffnen und `http://localhost:8080` aufrufen – die Chat-UI kommuniziert nun über diesen Endpunkt mit dem AI-Assistenten.
