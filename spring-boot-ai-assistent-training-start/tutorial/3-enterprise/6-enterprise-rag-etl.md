# Enterprise RAG - ETL

## Ziel

Das Ziel dieser Einheit ist es, die **Extract-Transform-Load (ETL) Pipeline** in Spring AI zu verstehen und zu implementieren. ETL ist der Prozess, um Rohdaten (wie PDF-Dokumente) in strukturierte, durchsuchbare Informationen für die Vector-Datenbank umzuwandeln. Eine gut gestaltete ETL-Pipeline stellt sicher, dass nur relevante, gefilterte und optimal aufbereitete Daten dem Retrieval-System zur Verfügung stehen.


## Domain

Im Kontext der Versicherungsanwendung müssen Versicherungsbedingungen aus PDF-Dokumenten extrahiert und in eine durchsuchbare Form umgewandelt werden:

- **Bedingungen laden**: Versicherungsdokumente (z.B. `conditions.pdf`) werden beim Anwendungsstart automatisch geladen
- **Dokumente splitten**: Lange PDF-Seiten werden in sinnvolle Paragraphen aufgeteilt
- **Kontextrelevanz**: Nur Versicherungsbedingungen, die für Makler relevant sind, werden gespeichert
- **Durchsuchbarkeit**: Die aufbereiteten Paragraphen sind später über die Vector-Datenbank schnell auffindbar

Dies ermöglicht dem AI-Assistenten, relevante Versicherungsbedingungen in Echtzeit zu finden und in seine Antworten einzubeziehen.




## Architecture

**ETL ist ein Data Engineering Prozess**: ETL ist nicht nur eine technische Implementierung, sondern ein unternehmenseigener Prozess der Datenaufbereitung. Datenteams und Data Engineers sind typischerweise verantwortlich für die Gestaltung und Umsetzung von ETL-Pipelines. Sie definieren, welche Datenquellen relevant sind, welche Transformationsregeln angewendet werden und wie Daten in die Produktionsumgebung gelangen. Spring AI bietet dafür nur die technischen Bausteine — die Architektur und Orchestrierung ist Aufgabe des Unternehmens.

**Spring AI ETL Abstraktion**: Spring AI folgt einem klassischen ETL-Muster mit drei Komponenten:

- **Reader** (Extract): Liest Rohdaten aus verschiedenen Quellen (PDF, Datenbanken, Web APIs)
- **Processor** (Transform): Transformiert Daten in verwertbare Form (Splitting, Filterung, Anreicherung)
- **Writer** (Load): Schreibt verarbeitete Daten in Zielssysteme (Vector Store, Datenbanken)

Spring AI bietet dafür abstrakte Klassen und konkrete Implementierungen:

```text
Anwendungsstart
        ↓
InsuranceProcessEtlPipeline (@Configuration)
        ↓
run() - ApplicationRunner Bean
        ↓
[READER] PagePdfDocumentReader
  └─ Extrahiert Pages aus PDF
        ↓
[PROCESSOR] InsuranceParagraphTextSplitter
  └─ Teilt Pages in sinnvolle Chunks
        ↓
[WRITER] Vector-Datenbank (später)
  └─ Speichert Embeddings
```

**Kernkomponenten:**

| Komponente | Funktion | Spring AI Abstraktion |
|-----------|----------|----------------------|
| `PagePdfDocumentReader` | Liest PDF-Dateien ein und extrahiert Seiten als Dokumente | DocumentReader |
| `TextSplitter` | Spring AI Base-Klasse für Text-Segmentierung | Processor |
| `InsuranceParagraphTextSplitter` | Custom-Implementation, splittet nach Paragraphen-Grenzen | TextSplitter |
| `ApplicationRunner` | Spring Boot Hook, führt ETL beim Start aus | Orchestrator |


## Konfiguration

Die ETL-Pipeline lädt Dateien **automatisch beim Anwendungsstart**. Es ist keine explizite Konfiguration erforderlich, wenn:

- Die PDF-Dateien im Classpath verfügbar sind (z.B. `src/main/resources/conditions.pdf`)
- Die Klasse `InsuranceProcessEtlPipeline` als `@Configuration` mit `@Bean` gekennzeichnet ist

**Logging aktivieren** — Um ETL-Prozesse zu debuggen, können Logs in `application.yaml` gesetzt werden:

```yaml
logging:
  level:
    com.thinkenterprise.ai.etl: DEBUG
```


## Implementation

### Pipeline-Konfiguration

Die `InsuranceProcessEtlPipeline` ist eine Spring `@Configuration`-Klasse, die ein `ApplicationRunner`-Bean definiert:

```java
@Configuration
public class InsuranceProcessEtlPipeline {

	static protected Logger logger = LoggerFactory.getLogger(InsuranceProcessEtlPipeline.class);

	@Bean
	public ApplicationRunner run() {
    
		return arg -> {
        logger.info("Starte Laden von Versicherungsbedingungen aus PDF");

		PagePdfDocumentReader pdfReader = new PagePdfDocumentReader("classpath:/conditions.pdf");

		List<Document> documents = pdfReader.get();
		logger.info("Extrahierte {} Dokumente aus PDF", documents.size());

		InsuranceParagraphTextSplitter splitter = new InsuranceParagraphTextSplitter();
		List<Document> paragraphs = splitter.split(documents);
		logger.info("Splitter erzeugte {} Paragraphs", paragraphs.size());
		};
	}
}
```

**Erklärung:**

- `@Bean` — Registriert die Methode `run()` als Spring Bean
- `ApplicationRunner` — Wird **nach dem Start** der Anwendung automatisch aufgerufen
- `PagePdfDocumentReader("classpath:/conditions.pdf")` — Lädt die PDF-Datei aus dem Classpath
- `.get()` — Extrahiert alle Seiten als `Document`-Objekte
- `logger.info()` — Gibt die Anzahl der Dokumente und Paragraphs aus


### Text-Splitting

Der `InsuranceParagraphTextSplitter` teilt lange Texte sinnvoll an **Paragraph-Grenzen**:

```java
public class InsuranceParagraphTextSplitter extends TextSplitter {
    @Override
    protected List<String> splitText(String text) {
        // Teilt den Text an doppelten Zeilenumbrüchen (typisch für Paragraphen)
        return Arrays.asList(text.split("\\n\\s*\\n"));
    }
}
```

**Wie es funktioniert:**

- Erbt von Spring AI `TextSplitter`
- Überschreibt `splitText()` mit Custom-Logik
- Regex `\\n\\s*\\n` — Splittet an doppelten Zeilenumbrüchen (typische Paragraph-Grenzen in Texten)
- Gibt eine `List<String>` mit den aufgeteilten Paragraphen zurück

Dies ist besser als willkürliches Token-basiertes Splitting, da es **semantische Einheiten** (Paragraphen) bewahrt.

**Context Window Limitation**: Crucially, Paragraphen dürfen nicht größer als das **Context Window** des Embedding-Modells sein. Das Modell `mxbai-embed-large` hat ein Kontext-Limit von ~512-1024 Tokens. Ist ein Paragraph größer, wirft das Embedding-Modell eine `AccessDeniedException`. Der `InsuranceParagraphTextSplitter` implementiert daher ein **Two-Level Splitting**: 

1. Erst nach Paragraph-Grenzen (`\n\n`)
2. Falls ein Paragraph > 800 Zeichen (~200 Tokens), wird er zusätzlich nach **Sätzen** aufgeteilt

Dies verhindert das Context Window Overflow-Problem und garantiert, dass alle Chunks erfolgreich in Vektoren konvertiert werden können.   

## Test

1. Sicherstellen, dass `conditions.pdf` in `src/main/resources/` vorhanden ist
2. Anwendung starten: `mvn spring-boot:run`
3. **Logs beobachten** — Bei Startup sollte folgende Ausgabe erscheinen:
   ```
   Starte Laden von Versicherungsbedingungen aus PDF
   Extrahierte 5 Dokumente aus PDF
   Splitter erzeugte 42 Paragraphs
   ```
4. Die Anzahl der Dokumente und Paragraphs ist abhängig von der PDF-Größe
5. Logging-Level auf DEBUG setzen, um weitere Informationen zu erhalten
