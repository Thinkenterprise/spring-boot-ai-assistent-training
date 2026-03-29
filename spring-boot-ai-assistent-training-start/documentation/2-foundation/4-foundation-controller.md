# Foundation Controller

## Ziel

Ziel dieser Einheit ist es, einen Spring MVC REST Controller zu implementieren, der als Schnittstelle zwischen Client-Aufrufen (z. B. curl oder späterer UI) und dem Service dient. 


## Architecture
Der Controller bildet die Schnittstelle zur AI Assistent Anwendung und delegiert die eigentliche Modellkommunikation an den Service ``InsuranceAssistantService``.

**Fokus dieser Einheit:** Der Schwerpunkt liegt auf dem **Controller** (Request annehmen, an den Service delegieren, Response zurückgeben).

```text
       Chat Model
       (Provider-spezifische Implementierung)
          ↑
      Chat Client
      (Abstraction über Content Model API)
          ↑
      Service
      (Business Logic)
          ↑
      Controller
      (REST Endpoints)
          ↑
      Client
      (curl/Postman, später UI)
```
## Implementation

### InsuranceChatController
Der ``InsuranceAssistantController`` stellt über `POST /chat` einen REST-Endpunkt bereit. Der Request-Body wird als String entgegengenommen und direkt an den ``InsuranceAssistantService`` weitergegeben, der die Modellantwort erzeugt und als String zurückliefert.

```java
@RestController
@RequestMapping("/chat")
public class InsuranceAssistantController {

    private InsuranceAssistantService insuranceChatService;
   
    InsuranceAssistantController(InsuranceAssistantService insuranceChatService) {
        this.insuranceChatService=insuranceChatService;
    }

    @PostMapping
    public String chat(@RequestBody String input) {
       return insuranceChatService.chatService(input);
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
    -H "Content-Type: text/plain" \
    -d "Hallo"
```

3. Die Antwort des Modells wird als Text zurückgegeben.
