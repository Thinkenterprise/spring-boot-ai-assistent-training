# Enterprise RAG - Vector Store

## Ziel

Das Ziel dieser Einheit ist es, **Vector Stores in Spring AI** zu verstehen und zu konfigurieren. Vector Stores sind spezialisierte Datenbanken, die Embedding-Vektoren speichern und semantische Ähnlichkeitssuchen ermöglichen. Sie sind das Herzstück einer RAG-Architektur (Retrieval Augmented Generation), da sie ermöglichen, relevante Kontextinformationen aus großen Datenmengen in Echtzeit zu finden.


## Domain

Im Versicherungs-Kontext speichert der Vector Store aufbereitete Paragraphen aus dem PDF Dokument als semantische Vektoren:

- **Semantische Suche**: Wenn der Makler fragt *"Welche Bedingungen gelten für Unfallversicherung?"*, sucht der Vector Store nach semantisch ähnlichen Paragraphen
- **Kontextanreicherung**: Die gefundenen Bedingungen werden dem AI-Assistenten übergeben, um präzise Antworten zu geben
- **Schnelle Abfragen**: Selbst bei tausenden von Bedingungen können relevante Dokumente in Millisekunden gefunden werden
- **Mehrere Versicherungsprodukte**: Jedes Produkt (Leben, Haftung, Sachversicherung) kann als separate Sammlung im Vector Store organisiert werden


## Dependencies

Bevor wir die Vector Store Konfiguration erklären, benötigen wir die notwendigen Spring AI Bibliotheken.

### Starter für die Vector-Datenbank

Die zentrale Dependency für Qdrant-Integration:

```xml
<!-- Enterprise RAG Qdrant Vector Database -->
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-vector-store-qdrant</artifactId>
</dependency>
```

Diese Dependency stellt Spring AI die **Vector Store API** bereit — eine abstrakte Schnittstelle für alle Vector Store Operationen wie Speichern, Suchen und Löschen von Vektoren. Sie enthält auch die Qdrant-spezifische Implementierung.

### Starter für die Vector-Datenbank Container

Damit Qdrant als Docker-Container automatisch startet:

```xml
<!-- Enterprise RAG Qdrant Vector Database Start with Docker Compose -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-docker-compose</artifactId>
</dependency>
```

Diese Dependency ermöglicht Spring Boot, `docker-compose.yml` automatisch beim Anwendungsstart zu erkennen und auszuführen.


## Architecture

**Vector Store API und ETL Integration**: Die Spring AI **Vector Store API** definiert die Schnittstelle für alle Vector Store Operationen:

```java
public interface VectorStore {
    void add(List<Document> documents);           // ETL Writer: Speichert Embeddings
    List<Document> similaritySearch(String query); // Retrieval: Findet ähnliche Dokumente
    void delete(List<String> documentIds);        // Cleanup: Löscht Vektoren
}
```

Der ETL-Prozess nutzt diese API als **Writer**: Die aufbereiteten Paragraphen werden durch den Vector Store in Vektoren konvertiert und in Qdrant gespeichert.

```text
ETL Pipeline
  ├─ Reader: PagePdfDocumentReader (PDF laden)
  ├─ Processor: InsuranceParagraphTextSplitter (Aufteilen)
  └─ Writer: VectorStore.add(documents)  ← speichert Embeddings in Qdrant
```

**Qdrant — Die Vector-Datenbank**: Qdrant ist eine moderne, **open-source Vector-Datenbank** der Berliner Firma Qdrant. Sie ist optimiert für:

- **Hohe Geschwindigkeit**: Sub-Millisekunden-Latenz bei Millionen von Vektoren
- **Produktionsreife**: Replication, Snapshots, API Security
- **Flexible Filterung**: Metadaten-Filter während der Suche
- **Cloud & On-Premise**: Flexible Deployment-Optionen

Der Vector Flow in dieser Anwendung:

```text
Paragraph aus PDF (Text)
        ↓
Ollama Embedding (Text → 1024-dim Vektor)
        ↓
Vector Store (speichert Vektor + Metadaten)
        ↓
Qdrant Database
  ├─ Collection: "insurance"
  └─ Speichert:
       - Embedding-Vektoren (1024-dimensional)
       - Metadaten (Source, Text, Paragraph-ID)
```

**Kernkonzepte:**

| Begriff | Erklärung |
|---------|-----------|
| **Embedding** | Numerische Vektorrepräsentation eines Textes (1024 Dimensionen für `mxbai-embed-large`) |
| **Collection** | Logische Sammlung von Dokumenten in Qdrant (z.B. "insurance") |
| **Vector Store Bean** | Spring-verwalteter Service, der Zugriff auf Vector-Datenbank bietet |
| **Similarity Search** | Findet Vektoren, die dem Query-Vektor ähnlich sind (geringe Euklidische Distanz) |


## Konfiguration

### application.yaml

Die Vector-Store-Konfiguration erfolgt über Spring AI Properties:

```yaml
spring:
  docker:
    compose:
      enabled: true              # Docker Compose automatisch starten
      start:
        command: up              # Kommando beim Start
      stop:
        command: down            # Kommando beim Stopp
  ai:
    ollama:
      embedding:
        model: mxbai-embed-large  # Embedding-Modell für Vektor-Generierung
      init:
        pull-model-strategy: always
        embedding:
          additional-models:
            - mxbai-embed-large   # Modell wird beim Ollama-Start geladen
    vectorstore:
      qdrant:
        collection-name: insurance    # Name der Sammlung in Qdrant
        initialize-schema: true       # Qdrant-Schema wird automatisch erstellt
```

**Erklärung:**

- `docker.compose.enabled: true` — Spring Boot startet automatisch Docker Compose
- `docker.compose.start/stop.command` — Definiert die Kommandos (up/down)
- `embedding.model: mxbai-embed-large` — Ollama lädt dieses Embedding-Modell, das Texte in 1024-dimensionale Vektoren konvertiert
- `collection-name: insurance` — Qdrant speichert alle Versicherungsbedingungen in dieser Collection
- `initialize-schema: true` — Spring AI erstellt die Collection automatisch beim Start

### Docker Compose Konfiguration

Qdrant wird als Docker-Container ausgeführt. Die Konfiguration in `docker-compose.yml`:

```yaml
services:
  qdrant:
    image: qdrant/qdrant:latest
    container_name: qdrant
    ports:
      - "6333:6333"  # REST API
      - "6334:6334"  # gRPC API
    tmpfs:
      - /qdrant/storage  # In-Memory Storage für schnelle Tests
```

**Wichtig — tmpfs vs. Volumes:**

- `tmpfs: /qdrant/storage` — Speichert Daten im **RAM** (temporär, schnell)
- Ideal für **Entwicklung und Testing** — beim Container-Stopp werden Daten gelöscht
- Für **Produktionsumgebungen** sollte ein persistentes Volume verwendet werden

Spring Boot erkennt diese Datei durch die `spring-boot-docker-compose` Dependency und startet den Qdrant-Container automatisch beim Anwendungsstart.


## Implementation

### Embedding-Prozess

Wenn die ETL-Pipeline Dokumente speichert, passiert folgendes:

```
Paragraph: "Die Unfallversicherung deckt Invalidität ab"
        ↓
EmbeddingModel.embed(text) 
  └─ Ollama mxbai-embed-large: Text → 1024-dim Vektor
        ↓
Vector: [0.23, -0.15, 0.67, ..., -0.42]
        ↓
VectorStore.add(document)
  └─ Qdrant speichert:
       {
         "vector": [0.23, -0.15, 0.67, ..., -0.42],
         "payload": {
           "text": "Die Unfallversicherung deckt Invalidität ab",
           "source": "conditions.pdf"
         }
       }
```

Die **Vector Store Bean wird automatisch von Spring AI erstellt**, sobald:
1. Die Dependency `spring-ai-starter-vector-store-qdrant` vorhanden ist
2. Die Konfiguration `spring.ai.vectorstore.qdrant.*` in `application.yaml` gesetzt ist
3. Qdrant über `docker-compose.yml` läuft

Eine explizite Bean-Definition ist nicht erforderlich. Spring AI kümmert sich um alles automatisch.


## Test

1. **docker-compose.yml** prüfen — sollte Qdrant Service enthalten

2. Anwendung starten: `mvn spring-boot:run`
   - Docker Compose startet Qdrant-Container
   - Logs sollten zeigen: `docker-compose up ...`
   - ETL Pipeline lädt Versicherungsbedingungen in Qdrant
   - Logs zeigen: `Splitter erzeugte X Paragraphs`

3. **Qdrant läuft?** Prüfen:
   ```bash
   docker ps  # Sollte qdrant/qdrant Container zeigen
   ```

4. **Qdrant Admin UI öffnen**: `http://localhost:6333/dashboard`
   - **Collections** anschauen → sollte "insurance" sehen
   - Anzahl der Vektoren sollte mit Paragraph-Anzahl übereinstimmen
   - Auf einen Punkt klicken → Metadaten und Payload sehen

5. **REST API testen** (mit curl):
   ```bash
   curl -X GET "http://localhost:6333/collections/insurance/points?limit=5" | jq .
   ```

6. **Embedding-Modell prüfen** — Ollama sollte `mxbai-embed-large` geladen haben:
   ```bash
   curl http://localhost:11434/api/tags | jq .models[].name
   ```
