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
Der ``InsuranceAssistantController`` stellt unter `/chat` einen REST-Endpunkt bereit: `POST /chat/console` erwartet einfachen Text und liefert die Modellantwort als String zurück.

> **Hinweis:** Ein weiterer Endpunkt (`POST /chat`, JSON mit `message` und `conversationId`) kommt erst in der Einheit **User Interface** hinzu, da er die dort eingeführte `chatService(...)`-Methode mit Conversation-ID voraussetzt.

```java
@RestController
@RequestMapping("/chat")
public class InsuranceAssistantController {

    private InsuranceAssistantService insuranceChatService;
   
    InsuranceAssistantController(InsuranceAssistantService insuranceChatService) {
        this.insuranceChatService=insuranceChatService;
    }

    @PostMapping("console")
    public String chat(@RequestBody String input) {
       return insuranceChatService.chatServiceWithoutMemory(input);
    }
}

```
## Configuration

Für diesen Schritt ist keine zusätzliche Konfiguration notwendig. Die Spring Boot Anwendung ist standardmäßig unter `http://localhost:8080` erreichbar.

## Test

1. Anwendung starten: `mvn spring-boot:run`
2. Endpunkt aufrufen (z. B. mit curl):


```bash
curl -X POST "http://localhost:8080/chat/console" \
    -H "Content-Type: text/plain" \
    -d 'Hallo'
```

3. Die Antwort des Modells wird als Text zurückgegeben.



