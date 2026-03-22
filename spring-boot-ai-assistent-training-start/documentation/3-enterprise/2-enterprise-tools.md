# Enterprise Tools

## Ziel

Ziel dieser Einheit ist es, **Tools** in Spring AI zu verstehen und zu implementieren. Tools ermöglichen es, das Modell mit aktuellen und unternehmensspezifischen Daten anzureichern, die nicht Teil des Trainingswissens sind.


## Motivation

Die Modelle sind mit Daten aus der Vergangenheit trainiert und kennen daher keine aktuellen Informationen wie die aktuelle Uhrzeit, Börsenkurse oder unternehmensspezifische Daten wie Kundenstatus oder Vertragsinformationen.

Mit **Tools** können wir das Modell mit solchen Daten anreichern. Tools können Daten lesen (z. B. Kundenstatus aus einer Datenbank) oder schreiben (z. B. Vertragsstatus aktualisieren).

> **Wichtig:** Das Modell wählt das passende Tool anhand seiner Beschreibung und der aktuellen Anfrage selbst aus. Wenn ein Tool ausgewählt wurde, ruft Spring AI die entsprechende Methode automatisch auf und gibt das Ergebnis an das Modell zurück.


## Architecture

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
