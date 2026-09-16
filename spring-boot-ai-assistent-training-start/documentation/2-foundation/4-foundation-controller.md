# Spring Boot AI Foundation Controller Training

## Ziel

Ziel dieser Einheit ist es, einen Spring MVC REST Controller zu implementieren, der als Schnittstelle zwischen Client-Aufrufen (z. B. curl oder späterer UI) und dem Service dient. 


## Architecture
In dieser Einheit betrachten wir ausschließlich den **Controller**. Er nimmt HTTP-Requests entgegen, validiert bzw. übernimmt die Eingabedaten und delegiert die eigentliche Verarbeitung an den ``InsuranceAssistantService``.

**Fokus dieser Einheit:** Der Schwerpunkt liegt auf dem **Controller** (Request annehmen, an den Service delegieren, Response zurückgeben).

```text
      User Interface
      (Web UI)
          ↓
      **Controller**
      **(REST Endpoints)**
          ↓
      Service
      (Business Logic)
          ↓
      Chat Client
      (Abstraction über Content Model API)
          ↓
      Chat Model
      (Provider-spezifische Implementierung)
```
## Implementation

### InsuranceAssistantController
Der ``InsuranceAssistantController`` stellt unter `/chat` zwei REST-Endpunkte bereit: `POST /chat` erwartet ein JSON-Objekt mit `message` und `conversationId` und liefert ein JSON-Objekt mit `reply`. `POST /chat/console` erwartet dagegen einfachen Text und liefert die Modellantwort als String.

```java
@RestController
@RequestMapping("/chat")
public class InsuranceAssistantController {

    private InsuranceAssistantService insuranceChatService;
   
    InsuranceAssistantController(InsuranceAssistantService insuranceChatService) {
        this.insuranceChatService=insuranceChatService;
    }

    @PostMapping("console")
    public String chatConsole(@RequestBody String input) {
       return insuranceChatService.chatServiceWithoutMemory(input);
    }

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        String conversationId = payload.get("conversationId");
        String result = insuranceChatService.chatService(message, conversationId);
        return Map.of("reply", result);
    }
}

```
## Configuration

Für diesen Schritt ist keine zusätzliche Konfiguration notwendig. Die Spring Boot Anwendung ist standardmäßig unter `http://localhost:8080` erreichbar.

## Test

1. Anwendung starten: `mvn spring-boot:run`
2. Endpunkt aufrufen (z. B. mit curl):


```bash
curl -X POST "http://localhost:8080/chat" \
    -H "Content-Type: application/json" \
    -d '{"message":"Hallo","conversationId":"demo"}'
```

3. Die Antwort des Modells wird als Text zurückgegeben.
