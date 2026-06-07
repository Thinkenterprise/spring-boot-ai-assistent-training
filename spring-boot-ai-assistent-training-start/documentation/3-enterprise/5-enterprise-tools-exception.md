# Enterprise Tools - Exception

## Ziel

Das Ziel dieser Einheit ist es, **Exception Handling in Tools** zu verstehen und umzusetzen. Tools können Fehler werfen — beispielsweise wenn Datenbankzugriffe fehlschlagen oder Validierungen nicht bestanden werden. Damit die Anwendung stabil bleibt und der Benutzer aussagekräftige Fehlermeldungen erhält, muss Exception Handling sauber implementiert werden.


## Domain

Im fachlichen Kontext der Versicherungsanwendung können Tools verschiedene Fehler wirken:

- Ein Kunde existiert nicht in der Datenbank
- Ein Makler hat keine Berechtigung, auf bestimmte Kundendaten zuzugreifen
- Eine Produktinformation ist nicht verfügbar
- Eine Geschäftsregel wird verletzt (z.B. Versicherungsdauer ungültig)

Diese **fachlichen Fehler** (Business Exceptions) sollen dem Benutzer verständlich mitgeteilt werden — nicht als technische Stack-Traces, sondern als klare Fehlerauskünfte.


## Architecture

Spring AI unterscheidet zwei Exception-Szenarien in Tools:

**Default-Verhalten (Exceptions werden geschluckt):**
- Eine Exception im Tool wird von Spring AI abgefangen
- Sie wird in eine `ToolExecutionException` konvertiert
- Das Modell erhält eine generische Fehlermeldung
- Der Benutzer sieht kein Detail über den echten Fehler

**Explizites Exception Handling (Exceptions werden weitergeleitet):**
- Mit Konfiguration `throw-exception-on-error: true` wird die Exception weitergeleitet
- Der Controller fängt sie mit `@ExceptionHandler` ab
- Eine strukturierte Fehlermeldung (RFC 9457 ProblemDetail) wird an den Client gesendet

**Ablauf mit Konfiguration:**

```text
Tool wirft Exception
        ↓
Spring AI ToolExceptionProcessor
        ↓
Wird Exception-Konfiguration beachtet?
        ├─ throw-exception-on-error: false → Exception wird geschluckt
        ├─ throw-exception-on-error: true  → Exception wird weitergeleitet
                                              ↓
                                        @ExceptionHandler im Controller
                                              ↓
                                        ProblemDetail → Client
```


## Konfiguration

Die Exception-Behandlung in Tools erfordert **zwei unabhängige Konfigurationseinstellungen** in der `application.yaml`:

**1. Exception-Simulation im Tool aktivieren** — steuert, ob das Tool eine Exception wirft:

```yaml
thinkenterprise:
  exception: true
```

**2. Spring AI Exception Handling konfigurieren** — steuert, wie Spring AI mit Exceptions umgeht:

```yaml
spring:
  ai:
    tools:
      throw-exception-on-error: true
```

Mit `throw-exception-on-error: true` werden Exceptions aus Tools weitergeleitet, statt sie zu schlucken. Der `@ExceptionHandler` im Controller fängt sie ab.

> **Wichtig:** Beide Einstellungen arbeiten unabhängig nebeneinander und sollten beide gesetzt werden, um vollständiges Exception Handling zu ermöglichen.


## Implementation

### Tool mit Exception-Handling

Das Tool `InsuranceCustomerDetailsTool` wirft eine `InsuranceException`, wenn das Property `exception: true` gesetzt ist:

```java
@Tool(name = "getCustomerDetails", description = "Ermittelt Kundendaten eines Kunden")
public Customer getCustomerDetails(
    @ToolParam(required = true, description = "Name des Kunden") String name, 
    ToolContext context) {
    
    logger.info(context.getContext().get("session").toString());
    
    // Wenn Property exception = true, wird eine Exception geworfen
    if(insuranceProperties.exception()) {
        throw new InsuranceException("Konnte Customer Details nicht ermitteln");
    }
    
    return insuranceCustomerService.getCustomerDetails(name);
}
```

Die `InsuranceException` ist eine **custom RuntimeException**:

```java
public class InsuranceException extends RuntimeException {
    public InsuranceException(String message) {
        super(message);
    }
}
```

### Exception Handler im Controller

Mit `throw-exception-on-error: true` wird die Exception an den Controller weitergeleitet. Der `@ExceptionHandler` fängt sie ab und konvertiert sie zu einer strukturierten Fehlerantwort (RFC 9457 ProblemDetail):

```java
@ExceptionHandler(value = InsuranceException.class)
ResponseEntity<ProblemDetail> handleException(InsuranceException exception) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problemDetail.setType(URI.create("http://thinkenterprise.com/InsuranceException"));
    problemDetail.setTitle("Insurance Exception");
    problemDetail.setDetail(exception.getMessage());
    return ResponseEntity.badRequest().body(problemDetail);
}
```

Diese Antwort wird als strukturiertes JSON an den Client gesendet — kein Stack-Trace, sondern ein **businessgerechter Fehler**.


## Test

1. Anwendung starten: `mvn spring-boot:run`
2. **Normaler Betrieb** — Anfrage stellen: *„Was sind die Vertragsdaten von Max Mustermann?"*
   - Ergebnis: AI-Assistent antwortet basierend auf Kundendaten
3. **Exception-Modus aktivieren** — In `application.yaml` folgende Einstellungen hinzufügen:
   ```yaml
   thinkenterprise:
     exception: true
   spring:
     ai:
       tools:
         throw-exception-on-error: true
   ```
4. Anwendung neu starten
5. **Gleiche Anfrage** stellen — Das Tool wirft jetzt `InsuranceException`
6. **Erwartetes Ergebnis:** In der **Chat-Oberfläche** erscheint die strukturierte Fehlermeldung:
   ```json
   {
     "type": "http://thinkenterprise.com/InsuranceException",
     "title": "Insurance Exception",
     "status": 400,
     "detail": "Konnte Customer Details nicht ermitteln"
   }
   ```
7. Exception-Modus deaktivieren, um zur Normalfunktion zurückzukehren


