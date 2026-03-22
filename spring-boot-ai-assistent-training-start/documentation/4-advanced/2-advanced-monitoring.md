# Advanced Monitoring

## Ziel

Ziel dieser Einheit ist es, den AI-Assistenten mit **Monitoring** auszustatten. Monitoring ermöglicht die kontinuierliche Überwachung des laufenden Betriebs und ist gerade für AI-Anwendungen essenziell, um Qualität und Compliance sicherzustellen.


## Motivation

**Observability** ist eine nicht-funktionale Anforderung an eine AI-Anwendung: Sie muss Informationen aus dem laufenden Betrieb bereitstellen. Typische Fragestellungen im Betrieb:

- Gibt das Modell Fehler zurück?
- Wie viele Token werden pro Anfrage versendet (Kostenkontrolle)?
- Welche Dokumente werden aus dem Vector Store geladen und wie lange dauert das?
- Wie ist die Antwortlatenz?

In Spring AI ist **Observability** bereits eingebaut. Es wird das Observability API von **Micrometer** verwendet. Alle notwendigen Spring Beans werden automatisch durch die Spring AI Autokonfiguration erzeugt – für Chat Client, Chat Model, Advisors und Vektordatenbanken.


## Architecture

```text
+-------------------+    Logs      +-------------+    Visualisierung
| Spring AI         | ----------> | Logstash    | -----------------> Grafana
| Chat Client       |             +-------------+
| Chat Model        |    Metriken
| Advisors          | ----------> +-------------+
| VectorStore       |             | Prometheus  | -----------------> Grafana
+-------------------+             +-------------+
                       Tracing
                    -----------> +-------------+
                                 | Zipkin      | -----------------> Grafana
                                 +-------------+
```


## Libraries

Der **Spring Boot Actuator** ist die Voraussetzung für die Bereitstellung von Logs, Metriken und Tracing über Spring AI:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```


## Configuration

### Logging

Das Logging ist per Default **deaktiviert**, da User Prompts und Modellantworten sensible Daten enthalten können. Es kann gezielt über Properties freigeschaltet werden:

```yaml
spring:
  ai:
    chat:
      observations:
        log-completion: true
        log-prompt: true
        include-error-logging: true
      client:
        observations:
          log-prompt: true
    vectorstore:
      observations:
        log-query-response: true
```

> **Hinweis:** In Produktivumgebungen sollte Logging sensibler Daten sorgfältig abgewogen werden – insbesondere unter DSGVO-Gesichtspunkten.

### Metriken

Spring AI stellt automatisch Metriken über Micrometer bereit, z. B. Tokenverbrauch, Latenz und Fehlerrate. Diese können über den Actuator-Endpunkt abgerufen werden:

```
GET http://localhost:8080/actuator/metrics
```

### Tracing

Tracing liefert einen vollständigen Aufruf-Trace durch alle Schichten der AI-Anwendung. Im Insurance Use Case dient das Tracing als **Audit-Trail** für alle Modellinteraktionen.

```yaml
management:
  tracing:
    sampling:
      probability: 1.0
```


## Test

1. Anwendung starten: `mvn spring-boot:run`
2. Actuator-Endpunkte prüfen: `http://localhost:8080/actuator`
3. Logging aktivieren und Anfrage stellen – Prompt und Antwort sollten im Konsolenlog erscheinen
4. Metriken abrufen: `http://localhost:8080/actuator/metrics/spring.ai.chat.client.operation`
