# Advanced Monitoring

## Ziel

In dieser Übung wird **Observability** für den AI-Assistenten eingeführt. Über den Spring Boot Actuator werden **Logs**, **Metriken** und **Tracing-Informationen** bereitgestellt und an spezialisierte Server übermittelt — Metriken an **Prometheus** und Tracing-Informationen an **Zipkin**. Ziel ist es zu verstehen, wie Spring AI Observability für Chat Client, Chat Model, Advisors und Vektordatenbanken automatisch unterstützt und wie diese Informationen für das Monitoring im Insurance Use Case genutzt werden.

## Motivation

Das **Monitoring** ist eine kontinuierliche Überwachung einer Anwendung. Werden bestimmte Schwellwerte überschritten, werden Maßnahmen ergriffen, entweder manuell oder automatisch. Das Monitoring ist gerade für AI-Anwendungen besonders wichtig, da für die Sicherung der Qualität und Compliance AI-Anwendungen überwacht werden müssen.

Das Monitoring setzt voraus, dass sich die AI-Assistenten überwachen lassen. **Observability** ist eine nicht-funktionale Anforderung an eine AI-Anwendung, Informationen aus dem laufenden Betrieb bereitzustellen.

Ein zentraler Aspekt ist dabei die **Transparenz**: Ein AI-Assistent trifft im Hintergrund eine Reihe von Entscheidungen, die für den Nutzer nicht sichtbar sind — welches Modell antwortet, welche Tools aufgerufen werden, welche Advisors die Anfrage anreichern oder verändern. Ohne Observability bleiben diese Entscheidungen eine Black Box.

Folgende Fragestellungen lassen sich über Observability beantworten:

- Gibt das Modell einen Fehler zurück?
- Wie viele Token werden an das Modell gesendet, um Kosten zu überwachen oder zu optimieren?
- Welches **Tool** wurde vom Modell mit welchen Argumenten aufgerufen, und welches Ergebnis hat es zurückgegeben?
- Welche **Advisors** waren an einer Anfrage beteiligt, und in welcher Reihenfolge wurden sie durchlaufen?
- Welche Dokumente wurden aus dem Vector Store gelesen, und wie viel Zeit wurde dafür benötigt?

> **Hinweis:** Im Insurance Use Case soll über das Tracing ein **Audit** bereitgestellt werden — die Nachvollziehbarkeit aller Entscheidungen und Interaktionen des AI-Assistenten, insbesondere der Tool-Aufrufe, ist eine zentrale Compliance-Anforderung im Versicherungsbereich. Nur wenn jede Interaktion rückverfolgbar ist, lässt sich im Nachgang erklären, warum der AI-Assistent eine bestimmte Antwort gegeben hat.

## Architektur

Observability besteht aus drei Bausteinen, die an unterschiedliche spezialisierte Server übermittelt werden:

| Baustein | Beschreibung | Server im Use Case |
|---|---|---|
| **Logs** | Informationen zu User Prompts sowie Anfragen/Antworten der Vektordatenbank | Konsole |
| **Metriken** | Quantitative Messwerte wie Anzahl, Dauer und Token-Verbrauch von Operationen | Prometheus |
| **Tracing-Informationen** | Nachvollziehbarkeit einzelner Arbeitsschritte über Spans und Tags | Zipkin |

Diese Informationen können anschließend über ein Monitoring-System wie Grafana ausgewertet werden.

Während Prometheus die Metriken über ein **proprietäres Protokoll** pull-basiert abruft, wird das Tracing push-basiert über **OTLP** (OpenTelemetry Protocol) an Zipkin übertragen — ein von der OpenTelemetry-Organisation standardisiertes Protokoll. Die Übersetzung der Micrometer-Spans in das OTLP-Format und die Übertragung an Zipkin übernimmt der **OpenTelemetry-Zipkin-Exporter** (`opentelemetry-exporter-zipkin`), der über die Micrometer-Tracing-Bridge (`micrometer-tracing-bridge-otel`) angebunden wird.

In **Spring AI** ist Observability bereits eingebaut. Dazu wird das **Observability API von Micrometer** verwendet. Alle dafür notwendigen Spring Beans werden von der Spring AI Autoconfiguration automatisch erzeugt — für die Module **Chat Client**, **Chat Model**, **Advisors** und **Vektordatenbanken** werden Logs, Metriken und Tracing-Informationen bereitgestellt.

Voraussetzung für die Bereitstellung dieser Informationen ist der **Spring Boot Actuator**.

```text
+----------------------+        Logs        +-----------+
|                      | ------------------> |  Konsole  |
|   AI-Assistent       |                     +-----------+
|  (Chat Client,       |       Metriken      +-------------+
|   Chat Model,        | ------------------> | Prometheus  |  (Pull, 5s)
|   Advisors,           |   (push via         +-------------+
|   Vektordatenbank)    |    /actuator/         Tracing
|                      |    prometheus)      +-----------+
|  Spring Boot Actuator | ------------------> |  Zipkin   |  (Push, OTLP)
+----------------------+                     +-----------+
```

## Libraries

Für **Logs**, **Metriken** und **Tracing** ist zunächst der Spring Boot Actuator notwendig, um die Observability-Informationen über Spring AI bereitzustellen:

```xml
<!-- Actuator as base Observability -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Für die **Metriken** wird Prometheus als Registry eingebunden:

```xml
<!-- Metrics for Standard Prometheus Server and Protokoll  -->
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

Für das **Tracing** wird im Gegensatz zu Prometheus, das ein proprietäres Protokoll verwendet, mit **OTLP** ein Standardprotokoll der OpenTelemetry-Organisation verwendet. Dazu sind die Bridge zwischen Micrometer und OpenTelemetry sowie der Zipkin-Exporter notwendig:

```xml
<!-- Trace with OpenTelemetry Bridge (Spring Boot 4 modularized Autoconfiguration) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-micrometer-tracing-opentelemetry</artifactId>
</dependency>
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-otel</artifactId>
</dependency>

<!-- Zipkin Autoconfiguration (Sender/Connection Details) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-zipkin</artifactId>
</dependency>

<!-- Zipkin Export for OpenTelemetry -->
<dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-exporter-zipkin</artifactId>
</dependency>
```

> **Achtung:** Seit Spring Boot 4 wurde die Tracing-Autoconfiguration aus dem `spring-boot-actuator-autoconfigure` in eigene Module ausgelagert. Es reicht nicht aus, nur die Bridge-Bibliothek (`micrometer-tracing-bridge-otel`) und den OTel-Zipkin-Exporter einzubinden — ohne `spring-boot-micrometer-tracing-opentelemetry` (liefert den eigentlichen `Tracer`-Bean) und `spring-boot-zipkin` (liefert den HTTP-Sender) bleibt der Tracer ein No-op, und es werden keine Spans an Zipkin gesendet.

Prometheus und Zipkin werden als Container über die `docker-compose.yml` gestartet, gruppiert im Compose-Profile `monitoring`:

```yaml
services:
  prometheus:
    image: prom/prometheus:latest
    profiles: ["monitoring"]
    volumes:
      - ./prometheus/:/etc/prometheus/
      - prometheus_data:/prometheus
    command:
      - '--config.file=/etc/prometheus/prometheus.yml'
      - '--storage.tsdb.path=/prometheus'
      - '--web.console.libraries=/usr/share/prometheus/console_libraries'
      - '--web.console.templates=/usr/share/prometheus/consoles'
    ports:
      - 9090:9090
    networks:
      - back-tier
  zipkin:
    image: 'openzipkin/zipkin:latest'
    profiles: ["monitoring"]
    ports:
      - '9411:9411'
```

## Configuration

### Logging

Das Logging bezieht sich auf die Informationen der User Prompts und der Anfragen/Antworten der Vektordatenbank. Da es sich um sehr sensible Daten handelt, ist das Logging dieser Informationen **per Default nicht eingeschaltet**. Über die folgenden Properties werden sie für das Log freigegeben:

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

> **Hinweis:** Im Insurance Use Case werden die Logs direkt auf der Konsole gelesen. Ein dedizierter Log Server wie Logstash ist nicht vorgesehen, kann aber gemäß Spring Boot Dokumentation über den Actuator integriert werden.

**Wichtige Datenpunkte im Log:**

- **User Prompt** — die ursprüngliche Anfrage des Nutzers an den Chat Client
- **Completion** — die vollständige Antwort des Modells
- **Error-Informationen** — Fehler bei der Modell- oder Tool-Ausführung (`include-error-logging`)
- **Vectorstore Query** und **Vectorstore Response** — die an die Vektordatenbank gestellte Anfrage sowie die zurückgegebenen Dokumente

### Metriken

Damit der Actuator die Metriken sowie die HTTP-Exchanges bereitstellt, ist die folgende Konfiguration in der `application.yaml` notwendig:

```yaml
management:
  endpoints:
    access:
      default: unrestricted
    web:
      exposure:
        include: "*"
  observations:
    annotations:
      enabled: true
  httpexchanges:
    recording:
      enabled: true
```

Folgende Properties werden dafür benötigt:

| Property | Beschreibung |
|---|---|
| `management.endpoints.access.default` | Steuert den Standard-Zugriff auf Actuator-Endpunkte. `unrestricted` erlaubt den Zugriff ohne zusätzliche Security-Konfiguration — für die Trainingsumgebung ausreichend, in Produktion sollte der Zugriff eingeschränkt werden |
| `management.endpoints.web.exposure.include` | Legt fest, welche Actuator-Endpunkte über HTTP erreichbar sind. Per Default ist nur `health` exponiert; `"*"` öffnet alle Endpunkte, u. a. `/actuator/prometheus` und `/actuator/httpexchanges` |
| `management.observations.annotations.enabled` | Aktiviert die Auswertung von Micrometer-`@Observed`-Annotationen, über die auch eigener Anwendungscode (z. B. die ETL-Pipeline) in die Observability einbezogen werden kann |
| `management.httpexchanges.recording.enabled` | Zeichnet die letzten HTTP-Exchanges (Request/Response) auf, abrufbar über `/actuator/httpexchanges` — hilfreich, um die zuletzt eingehenden Anfragen an den AI-Assistenten nachzuvollziehen |

Damit werden die Metriken über die URL `http://localhost:8080/actuator/prometheus` dem Prometheus Server bereitgestellt. Prometheus selbst greift **pull-basiert** alle 5 Sekunden auf diese URL zu, konfiguriert über `scrape_configs` in der `prometheus.yml`:

```yaml
scrape_configs:
  - job_name: 'demo'
    scrape_interval: 30s
    scheme: http
    metrics_path: '/actuator/prometheus'
    static_configs:
         - targets: ['host.docker.internal:8080']
```

**Wichtige und oft verwendete Metriken:**

| Metrik | Beschreibung |
|---|---|
| `gen_ai_chat_client_operation_seconds_count` / `_max` / `_sum` | Anzahl, langsamste und Gesamtdauer der Chat-Client-Operationen |
| `db_vector_client_operation_seconds_count` / `_max` / `_sum` | Anzahl, langsamste und Gesamtdauer der Vektordatenbank-Operationen |
| `gen_ai_client_token_usage_total` | Gesamtzahl der verbrauchten Token — zentral für die Kostenüberwachung |
| `http_server_requests_seconds_*` | Anzahl und Dauer der HTTP-Requests an den AI-Assistenten, z. B. an `/chat` |

### Tracing

Für das Tracing wird die **Sampling-Rate** konfiguriert. Mit dem Wert `1.0` wird jede Anfrage zu 100% getraced — sinnvoll für die Trainingsumgebung, in Produktion würde man hier üblicherweise einen niedrigeren Wert wählen, um die Datenmenge zu reduzieren:

```yaml
management:
  tracing:
    sampling:
      probability: 1.0
```

`management.tracing.sampling.probability` ist die einzige Tracing-Property, die explizit gesetzt werden muss — das Anlegen des `Tracer`-Beans, die Span-Erzeugung sowie der Export übernehmen die in den **Libraries** beschriebenen Autoconfiguration-Module automatisch.

Der Spring Boot Actuator sendet alle Tracing-Informationen automatisch **push-basiert** über **OTLP** an Zipkin (Standard-Endpoint `http://localhost:9411/api/v2/spans`, passend zum Port-Mapping des Zipkin-Containers). Die Tracing-Informationen können über die Zipkin-Oberfläche unter `http://localhost:9411/` angezeigt werden.

**Wichtige und oft verwendete Span-Tags:**

| Tag | Beschreibung |
|---|---|
| `gen_ai.operation.name` | Art der Operation, z. B. `chat` oder `embedding` |
| `gen_ai.request.model` / `gen_ai.response.model` | Angefordertes bzw. tatsächlich antwortendes Modell |
| `gen_ai.usage.input_tokens` / `output_tokens` / `total_tokens` | Token-Verbrauch je Span — Basis für das Audit der Kosten |
| `spring.ai.tool.definition.name` | Name des vom Modell aufgerufenen Tools — zentral für die Nachvollziehbarkeit von Tool-Aufrufen |
| `db.vector.query.top_k` / `db.collection.name` | Abgefragte Anzahl Treffer bzw. verwendete Collection der Vektordatenbank |

Damit Spring Boot Docker Compose beim Start der Anwendung sowohl Qdrant als auch Prometheus und Zipkin startet, werden die jeweiligen Compose-Profile aktiviert:

```yaml
spring:
  docker:
    compose:
      enabled: true
      profiles:
        active: vector, monitoring
      stop:
        command: down
      start:
        command: up
```

`spring.docker.compose.profiles.active` steuert, welche Compose-Profile beim Anwendungsstart hochgefahren werden — `vector` startet Qdrant, `monitoring` startet Prometheus und Zipkin (siehe `docker-compose.yml`).

## Test

1. Sicherstellen, dass `spring.docker.compose.profiles.active` die Profile `vector` und `monitoring` enthält, damit Qdrant, Prometheus und Zipkin gestartet werden
2. AI-Assistenten starten und über die Web-Oberfläche (`http://localhost:8080`) eine Anfrage stellen, z. B. *„Welche Versicherungen bietet ihr an?"*
3. Unter `http://localhost:9090/` in Prometheus die Metriken `gen_ai_chat_client_operation_*` und `db_vector_client_operation_*` suchen und die Werte für `count`, `max` und `sum` prüfen
4. Unter `http://localhost:9411/` in Zipkin den Service `spring-ai-assistent` auswählen und den zugehörigen Trace mit den Spans für Chat Client, Advisors und Vektordatenbank öffnen
5. Im Trace prüfen, dass Tags mit Kontextinformationen zu den einzelnen Spans hinterlegt sind
