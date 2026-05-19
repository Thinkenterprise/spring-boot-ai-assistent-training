# Enterprise Tools

## Ziel

Ziel dieser Einheit ist es, **Tools** in Spring AI zu verstehen und zu implementieren. 


## Domain

LLMs wurden mit umfassendem Weltwissen trainiert, kennen aber nicht die spezifischen Daten aus einer Domäne — beispielsweise Kundendaten, Produktinformationen oder Verträge aus einem Unternehmen. **Tools** schließen diese Lücke: Sie ermöglichen es dem AI-Assistenten, externe Datenquellen zu lesen und zu schreiben.

Im Insurance Use Case nutzen Tools beispielsweise:
- Kundendaten aus einer Datenbank abrufen
- Produktinformationen bereitstellen
- Änderungen an Verträgen speichern

Das Modell entscheidet selbst, welches Tool aufgrund der Anfrage und der Tool-Beschreibung aufgerufen werden soll. 


> **Wichtig:** Das Modell wählt das passende Tool anhand seiner Beschreibung und der aktuellen Anfrage selbst aus. Wenn ein Tool ausgewählt wurde, ruft Spring AI die entsprechende Methode automatisch auf und gibt das Ergebnis an das Modell zurück.


## Architecture

Der Tool-Aufruf erfolgt nach einem definierten Ablauf: Das Modell analysiert die Anfrage, wählt basierend auf der Tool-Beschreibung das passende Tool aus, und Spring AI ruft die entsprechende Methode auf. Das Tool liefert die Daten zurück, die das Modell in seine Antwort einbezieht.

**Ablauf:**

1. **Anfrage** an das Modell
2. **Tool-Auswahl** durch das Modell basierend auf Beschreibung und Anfrage
3. **Tool-Aufruf** durch Spring AI mit den vom Modell extrahierten Parametern
4. **Daten-Abruf** aus Service/Datenbank
5. **Ergebnis** zurück an das Modell
6. **Antwort-Generierung** mit den bereitgestellten Daten

**Schaubild:**

```text
+------------------+    1. Anfrage    +------------------+
| InsuranceChat    | --------------> | Chat Model       |
| Service          |                 |                  |
|                  | <-------------- | wählt Tool aus   |
|                  |    2. Tool Call  |                  |
|                  |                 +------------------+
|                  |
|  3. Tool-Aufruf  v
+------------------+    4. Daten     +------------------+
| InsuranceCustomer| <-------------- | InsuranceCustomer|
| DetailsTool      | --------------> | Service / DB     |
+------------------+    5. Antwort   +------------------+
         |
         | 6. Daten zurück an Modell
         v
+------------------+
| Chat Model       |
| generiert Antwort|
+------------------+
```


## Implementation

Spring AI bietet drei Ansätze für Tool-Implementierungen:

| Ansatz | Beschreibung |
|---|---|
| **Method as a Tool (MaaT)** | Einfachste Variante – Methode mit `@Tool`-Annotation |
| **Function as a Tool (FaaT)** | Lambda/Functional Interface als Tool |
| **Spring Tool Native Ansatz (STNA)** | Direkte Implementierung der Tool-Interfaces |

Im Regelfall reicht **MaaT** aus.

### Method as a Tool (MaaT)

Die `@Tool`-Annotation markiert eine Methode als Tool. Die Beschreibung (`description`) ist entscheidend – das Modell wählt das Tool anhand dieser Beschreibung aus:

```java
@Component
public class InsuranceCustomerDetailsTool {

    static final Logger logger = LoggerFactory.getLogger(InsuranceCustomerDetailsTool.class);

    private InsuranceCustomerService insuranceCustomerService;
   
    public InsuranceCustomerDetailsTool(InsuranceCustomerService insuranceCustomerService) {
       this.insuranceCustomerService=insuranceCustomerService;
    }

    @Tool(name = "getCustomerDetails", description = "Ermittelt Kundendaten eines Kunden")
    public Customer getCustomerDetails(@ToolParam(required = true, description = "Name des Kunden") String name) {
        return insuranceCustomerService.getCustomerDetails(name);
    }

}
```

### Registrierung am Chat Client
Das Tool wird einmalig am `ChatClient.Builder` registriert

```java
@Configuration
public class InsuranceAssistantConfiguration {

    @Bean
    public ChatClient createClient(ChatClient.Builder chatClientBuilder,ChatMemory chatMemory, 
                                   InsuranceCustomerDetailsTool insuranceCustomerDetailsTool) {

        var chatClient = chatClientBuilder.defaultOptions(createChatOptions())
                                          .defaultSystem(createSystemPrompt().toString())
                                          .defaultAdvisors(createChatMemoryAdvisor(chatMemory))
                                          .defaultAdvisors(a -> a.param(ChatMemory.CONVERSATION_ID, "InsuranceAssistent"))
                                          .defaultTools(insuranceCustomerDetailsTool)
                                          .build();
        return chatClient;
    }
```

> **Hinweis:** Tools können einmalig mit `defaultTools()` registriert werden, oder auch dynamisch beim Aufruf mit `.tools()` übergeben werden, falls nur für einzelne Anfragen ein Tool benötigt wird.

### System Prompt 

Das System Prompt kann das Modell anweisen, welche Tools verfügbar sind und in welchen Situationen diese eingesetzt werden. Die Datei `systemPrompt.st` enthält beispielsweise:

```
# Verfügbare Tools
Der Assistent hat Zugriff auf das Tool "getCustomerDetails", um Kundendaten zu ermitteln.
Das Tool soll verwendet werden, wenn der Makler nach Informationen zu einem Kunden fragt.
```

Das Template wird mit Variablen befüllt:

```java
.variables(Map.of(
    "assistentName", "AI Insurance Assistent",
    "availableTools", "getCustomerDetails - Kundendaten abrufen"
))
``` 


## Test

1. Anwendung starten: `mvn spring-boot:run`
2. Anfrage mit Kundenbezug stellen, z. B.: *„Was sind die Vertragsdaten von Max Mustermann?"*
3. Prüfen, ob das Modell das Tool `get_CustomerDetails` aufruft und die Kundendaten korrekt in die Antwort einbezieht
4. Im Log prüfen, ob der Tool-Aufruf protokolliert wurde
