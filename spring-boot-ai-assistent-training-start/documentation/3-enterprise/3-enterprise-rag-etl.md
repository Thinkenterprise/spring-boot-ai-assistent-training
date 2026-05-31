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

Spring AI bietet ein **ApplicationRunner**-Pattern für die ETL-Pipeline:

```text
Anwendungsstart
        ↓
InsuranceProcessEtlPipeline (@Configuration)
        ↓
run() - ApplicationRunner Bean
        ↓
PagePdfDocumentReader
  └─ Extrahiert Pages aus PDF
        ↓
InsuranceParagraphTextSplitter
  └─ Teilt Pages in Paragraphen
        ↓
Vector-Datenbank (später)
  └─ Speichert Embeddings
```

**Kernkomponenten:**

| Komponente | Funktion |
|-----------|----------|
| `PagePdfDocumentReader` | Liest PDF-Dateien ein und extrahiert Seiten als Dokumente |
| `TextSplitter` | Spring AI Base-Klasse für Text-Segmentierung |
| `InsuranceParagraphTextSplitter` | Custom-Implementation, splittet nach Paragraphen-Grenzen |
| `ApplicationRunner` | Spring Boot Hook, führt ETL beim Start aus |


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
