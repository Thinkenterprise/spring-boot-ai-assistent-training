# Enterprise RAG - Advisor

## Ziel

Das Ziel dieser Einheit ist es, **RAG Advisors in Spring AI** zu verstehen und zu implementieren. Advisors sind die Orchestrierungskomponenten, die den gesamten RAG-Flow steuern: Sie transformieren User-Queries, rufen relevante Dokumente aus dem Vector Store ab und bereiten diese als Kontext für das Chat-Modell auf.


## Domain

Im Versicherungs-Kontext orchestriert der RAG Advisor den kompletten Informationsbeschaffungsprozess:

- **Query-Transformation**: User-Frage wird optimiert für die Vector-Suche
- **Kontext-Retrieval**: Relevante Versicherungsbedingungen werden aus Qdrant gefunden
- **Document-Processing**: Gefundene Dokumente werden gefiltert und angereichert
- **Prompt-Integration**: Kontextinformationen werden dem System-Prompt beigefügt
- **Antwortgenerierung**: Das Modell antwortet basierend auf Kontext + User-Frage

Dies ermöglicht präzise, faktenbasierte Antworten statt Halluzinationen.


## Architecture

Spring AI RAG Advisors arbeiten in **drei Phasen**:

```text
User fragt: "Welche Bedingungen für Unfallversicherung?"
        ↓
Phase 1: Query Transformation
  InsuranceQueryTransformer.transform(Query)
  └─ Optimiert die Frage für Vektorsuche
        ↓
Phase 2: Document Retrieval
  Vector Store Advisor
  ├─ Sucht ähnliche Vektoren in Qdrant
  ├─ Gibt Top-K Dokumente zurück (z.B. Top-5)
  └─ Payload wird extrahiert
        ↓
Phase 3: Document Post-Processing
  InsuranceDocumentProcessor.process(Query, Documents)
  ├─ Filtert/validiert Dokumente
  ├─ Fügt Metadaten hinzu
  └─ Bereitet für Prompt vor
        ↓
Chat Model erhält:
  System Prompt + RAG-Kontext + User-Query
        ↓
Antwort: Basierend auf echten Versicherungsbedingungen
```

**Komponenten:**

| Komponente | Funktion |
|-----------|----------|
| `QueryTransformer` | Pre-Retrieval: Transformiert User-Query |
| `VectorStoreAdvisor` | Core: Sucht relevante Dokumente |
| `DocumentPostProcessor` | Post-Retrieval: Verarbeitet gefundene Dokumente |
| `ChatClient` mit Advisors | Orchestriert alle Phasen |


## Konfiguration

Die RAG Advisors werden in `InsuranceAssistantConfiguration` beim `ChatClient`-Aufbau registriert:

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

private Advisor createChatMemoryAdvisor(ChatMemory chatMemory) {
    return MessageChatMemoryAdvisor.builder(chatMemory).build();
}
```

**Weitere Advisors könnten hinzugefügt werden:**

```yaml
# application.yaml
spring:
  ai:
    vectorstore:
      qdrant:
        collection-name: insurance
        initialize-schema: true
        # RAG Advisor wird automatisch registriert, wenn:
        # 1. Vector Store konfiguriert ist
        # 2. Query/Document Processor Beans existieren
```


## Implementation

### Query Transformer

Der `InsuranceQueryTransformer` optimiert die User-Query vor der Vector-Suche:

```java
public class InsuranceQueryTransformer implements QueryTransformer {

    @Override
    public Query transform(Query query) {
       return query;  // Placeholder für Custom-Logik
    }
}
```

**Mögliche Transformationen:**

```java
@Override
public Query transform(Query query) {
    String originalText = query.text();
    
    // Beispiel 1: Synonym-Expansion
    String expanded = originalText
        .replace("Unfall", "Invalidität, Behinderung, Unfallversicherung")
        .replace("Haftung", "Schadensersatz, Haftpflicht, Forderung");
    
    // Beispiel 2: Kontext hinzufügen
    String contextualized = "Versicherungsbedingungen: " + expanded;
    
    // Beispiel 3: Mehrsprachig
    return new Query(contextualized, query.metadata());
}
```

Die Transformation hilft, bessere Matching-Ergebnisse zu erzielen.


### Document Post Processor

Der `InsuranceDocumentProcessor` verarbeitet Dokumente **nach dem Retrieval**:

```java
public class InsuranceDocumentProcessor implements DocumentPostProcessor {

    @Override
    public List<Document> process(Query query, List<Document> documents) {
        return documents;  // Placeholder für Custom-Logik
    }
}
```

**Mögliche Post-Processing-Szenarien:**

```java
@Override
public List<Document> process(Query query, List<Document> documents) {
    return documents.stream()
        // Filtern: Nur relevante Versicherungstypen
        .filter(doc -> doc.getMetadata().containsKey("product_type"))
        
        // Sortieren: Nach Relevanz-Score
        .sorted(Comparator.comparing(doc -> 
            (Double) doc.getMetadata().getOrDefault("score", 0.0)))
        
        // Begrenzen: Top 3 Dokumente
        .limit(3)
        
        // Metadaten anreichern
        .peek(doc -> doc.getMetadata().put("processed", true))
        
        .toList();
}
```

Dies ermöglicht **Quality Gating** und **Context Window Optimization**.


### Advisor Integration im ChatClient

```java
@Bean
public ChatClient createClient(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
    
    return chatClientBuilder
        // Advisor 1: RAG-Vector-Store-Advisor (automatisch erkannt)
        .defaultAdvisors(
            new VectorStoreRagAdvisor(vectorStore, new InsuranceQueryTransformer(), 
                                     new InsuranceDocumentProcessor())
        )
        // Advisor 2: Chat Memory (für Gesprächsverlauf)
        .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
        
        .build();
}
```

Alle Advisors wirken zusammen:
1. **Query kommt an**
2. **QueryTransformer** optimiert sie
3. **VectorStore** sucht Dokumente
4. **DocumentPostProcessor** bereitet sie auf
5. **Chat Model** antwortet mit Kontext


## Test

1. Anwendung starten: `mvn spring-boot:run`

2. **Chat-UI öffnen** und eine Frage stellen:
   ```
   "Was sind die Bedingungen für die Unfallversicherung?"
   ```

3. **Backend-Logs beobachten** — sollte zeigen:
   ```
   Query transformed: [Original Query]
   Retrieved 5 documents from vector store
   Processing documents: [Titles]
   Sending to Chat Model with context
   ```

4. **Antwort prüfen**:
   - ✅ Antwortet der Assistent basierend auf echten Bedingungen?
   - ✅ Zitiert er relevante Paragraphen?
   - ✅ Gibt es keine Halluzinationen?

5. **Mit verschiedenen Fragen testen**:
   - "Abdeckung von Invalidität?"
   - "Höhe der Selbstbeteiligung?"
   - "Ausschlüsse in der Haftpflicht?"

6. **RAG deaktivieren** (zum Vergleich):
   - Vector Store Advisor deaktivieren
   - Beobachten, wie der Assistent ohne Kontext antwortet (Unterschied sollte deutlich sein)

