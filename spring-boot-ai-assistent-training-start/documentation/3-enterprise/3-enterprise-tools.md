# Enterprise Tools

## Ziel

Ziel dieser Einheit ist es, **Tools** in Spring AI zu verstehen und zu implementieren. 


## Domain

ToDo: 
- LLMs wurden mit Welt Wissen trainiert 
- LLMs kennen nicht die Daten aus einer Domäne z.B. Kundendaten oder Produktdaten einer Versicherung 
- Tools können Daten aus einer Domäne z.B. einem Unternehmen lesen und dem Modell bereitstellen 
- Beispielsweise Produkt oder Kundendaten aus einer Datenbank lesen 
- Daten aus einem LLM können nicht der Domäne bereitgestellt werden 
- Tools erweitern LLMs da Tools aktuelle Daten aus dem LLM über Tools in die Domäne Schreiben können 
- Tools können beispielsweise Änderungen in den Kundendaten in eine Datenbank schreiben 


> **Wichtig:** Das Modell wählt das passende Tool anhand seiner Beschreibung und der aktuellen Anfrage selbst aus. Wenn ein Tool ausgewählt wurde, ruft Spring AI die entsprechende Methode automatisch auf und gibt das Ergebnis an das Modell zurück.


## Architecture

ToDo: 

- Beschreibe hier bitte wie der Tool Aufruf grob funktioniert 
- Hier ist mir wichtig, dass das Tool eine Tool Beschreibung erhält 
- Das Modell entscheidet werlches Tool aufgrufen werden soll 
- Die AI Anwendung für den Aufruf des Tools verantwortlich ist 
- Das Modell die Antwort zur weiteren Verarbeitung verwenden kann. 

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

    private final InsuranceCustomerService insuranceCustomerService;

    public InsuranceCustomerDetailsTool(InsuranceCustomerService insuranceCustomerService) {
        this.insuranceCustomerService = insuranceCustomerService;
    }

    @Tool(name = "get_CustomerDetails",
          description = "Gibt die Kundendaten eines Versicherungskunden zurück.")
    public InsuranceCustomer getCustomerDetails(
            @ToolParam(description = "Der Name des Kunden") String customerName) {
        return insuranceCustomerService.findByName(customerName);
    }
}
```

### Registrierung am Chat Client

Das Tool wird einmalig am `ChatClient.Builder` registriert:

```java
@Bean
public ChatClient createChatClient(ChatClient.Builder builder,
                                   InsuranceCustomerDetailsTool customerDetailsTool) {
    return builder
        .defaultTools(customerDetailsTool)
        .build();
}
```


## Test

1. Anwendung starten: `mvn spring-boot:run`
2. Anfrage mit Kundenbezug stellen, z. B.: *„Was sind die Vertragsdaten von Max Mustermann?"*
3. Prüfen, ob das Modell das Tool `get_CustomerDetails` aufruft und die Kundendaten korrekt in die Antwort einbezieht
4. Im Log prüfen, ob der Tool-Aufruf protokolliert wurde
