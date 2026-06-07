# Enterprise RAG - Vector Store

## Ziel

Das Ziel dieser Einheit ist es, **Vector Stores in Spring AI** zu verstehen und zu konfigurieren. Vector Stores sind spezialisierte Datenbanken, die Embedding-Vektoren speichern und semantische Ähnlichkeitssuchen ermöglichen. Sie sind das Herzstück einer RAG-Architektur (Retrieval Augmented Generation), da sie ermöglichen, relevante Kontextinformationen aus großen Datenmengen in Echtzeit zu finden.


## Domain

Im Versicherungs-Kontext speichert der Vector Store aufbereitete Versicherungsbedingungen als semantische Vektoren:

- **Semantische Suche**: Wenn der Makler fragt *"Welche Bedingungen gelten für Unfallversicherung?"*, sucht der Vector Store nach semantisch ähnlichen Paragraphen
- **Kontextanreicherung**: Die gefundenen Bedingungen werden dem AI-Assistenten übergeben, um präzise Antworten zu geben
- **Schnelle Abfragen**: Selbst bei tausenden von Bedingungen können relevante Dokumente in Millisekunden gefunden werden
- **Mehrere Versicherungsprodukte**: Jedes Produkt (Leben, Haftung, Sachversicherung) kann als separate Sammlung im Vector Store organisiert werden


## Architecture

Spring AI unterstützt mehrere Vector Store Implementierungen. In dieser Anwendung verwenden wir **Qdrant** — eine moderne, open-source Vector-Datenbank:

```text
ETL Pipeline (Dokumente + Embeddings)
        ↓
Vector Store (Qdrant)
        ├─ Collection: "insurance"
        └─ Speichert:
             - Embedding-Vektoren (1024-dimensional)
             - Metadaten (Source, Paragraph-ID)
        ↓
RAG Advisor
        ├─ Benutzer fragt: "Versicherungsbedingung für XYZ?"
        ├─ Query wird in Embedding konvertiert
        ├─ Semantische Similarität wird berechnet
        ├─ Top-K relevante Dokumente werden zurückgegeben
        └─ Werden dem Chat-Prompt als Kontext übergeben
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
  ai:
    ollama:
      embedding:
        model: mxbai-embed-large  # Embedding-Modell für Vektor-Generierung
      init:
        embedding:
          additional-models:
            - mxbai-embed-large  # Modell wird beim Ollama-Start geladen
    vectorstore:
      qdrant:
        collection-name: insurance    # Name der Sammlung in Qdrant
        initialize-schema: true       # Qdrant-Schema wird automatisch erstellt
```

**Erklärung:**

- `embedding.model: mxbai-embed-large` — Ollama lädt dieses Embedding-Modell, das Texte in 1024-dimensionale Vektoren konvertiert
- `collection-name: insurance` — Qdrant speichert alle Versicherungsbedingungen in dieser Collection
- `initialize-schema: true` — Spring AI erstellt die Collection automatisch beim Start


### InsuranceAssistantConfiguration.java

Die **Vector Store Integration** geschieht über den `ChatClient`:

```java
@Bean
public ChatClient createClient(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, 
                               InsuranceAssistantCustomerDetailsTool insuranceCustomerDetailsTool) {

    var chatClient = chatClientBuilder.defaultOptions(createChatOptions())
                                      .defaultSystem(createSystemPrompt().toString())
                                      .defaultAdvisors(createChatMemoryAdvisor(chatMemory))
                                      .defaultAdvisors(a -> a.param(ChatMemory.CONVERSATION_ID, "InsuranceAssistent"))
                                      .defaultTools(insuranceCustomerDetailsTool)
                                      .defaultToolContext(new HashMap<String, Object>(Map.of("session", "No Id")))
                                      .build();
    return chatClient;
}
```

Der Vector Store wird **implizit** durch Spring AI autowired, sobald:
- Die Dependency `spring-ai-starter-vector-store-qdrant` vorhanden ist
- Die Konfiguration `spring.ai.vectorstore.qdrant.*` gesetzt ist
- Qdrant-Container läuft (via docker-compose)


## Implementation

### Dependencies in pom.xml

```xml
<!-- Enterprise RAG Qdrant Vector Database -->
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-vector-store-qdrant</artifactId>
</dependency>

<!-- Enterprise RAG Qdrant Vector Database Start with Docker Compose -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-docker-compose</artifactId>
</dependency>
```

Diese Dependencies stellen Spring AI die Vector-Store-Implementierung und Docker-Integration bereit.


### docker-compose.yml

Qdrant wird als Docker-Container ausgeführt. Spring Boot startet ihn automatisch mit `spring-boot-docker-compose`:

```yaml
services:
  qdrant:
    image: qdrant/qdrant:latest
    ports:
      - "6333:6333"  # REST API
      - "6334:6334"  # gRPC API
    volumes:
      - qdrant_data:/qdrant/storage
    environment:
      - QDRANT_API_KEY=your-api-key

volumes:
  qdrant_data:
```

**Der Ablauf beim Anwendungsstart:**

1. Spring Boot erkennt `docker-compose.yml`
2. Docker Compose startet Qdrant-Container
3. `initialize-schema: true` erstellt die Collection "insurance" in Qdrant
4. ETL Pipeline lädt Versicherungsbedingungen und speichert sie als Embeddings
5. Chat-Requests nutzen den Vector Store via RAG Advisor (siehe nächste Einheit)


### Embedding-Prozess

Wenn die ETL-Pipeline Dokumente in den Vector Store speichert:

```
Paragraph: "Die Unfallversicherung deckt Invalidität ab"
        ↓
Ollama embedding:mxbai-embed-large (Text → 1024-dim Vector)
        ↓
Vector: [0.23, -0.15, 0.67, ..., -0.42]
        ↓
Qdrant speichert:
  {
    "vector": [0.23, -0.15, 0.67, ..., -0.42],
    "payload": {
      "text": "Die Unfallversicherung deckt Invalidität ab",
      "source": "conditions.pdf"
    }
  }
```


## Test

1. Sicherstellen, dass Qdrant läuft:
   ```bash
   docker ps  # Sollte qdrant/qdrant Container zeigen
   ```

2. Anwendung starten: `mvn spring-boot:run`
   - Docker Compose startet Qdrant
   - ETL Pipeline lädt Versicherungsbedingungen in Qdrant
   - Logs zeigen: `Splitter erzeugte X Paragraphs`

3. **Qdrant Admin UI öffnen**: `http://localhost:6333/dashboard`
   - **Collections** anschauen → sollte "insurance" sehen
   - Anzahl der Vektoren sollte mit Paragraph-Anzahl übereinstimmen

4. **REST API testen** (mit curl):
   ```bash
   curl -X POST http://localhost:6333/collections/insurance/points/search \
     -H "Content-Type: application/json" \
     -d '{
       "vector": [0.1, -0.2, 0.3, ...],
       "limit": 5
     }'
   ```

5. In der Chat-UI (nächste Einheit) werden relevante Versicherungsbedingungen automatisch gefunden

