# Enterprise RAG - Advisor

## Ziel

Das Ziel dieser Einheit ist es, **RAG Advisors in Spring AI** zu verstehen und zu implementieren. Advisors sind die Orchestrierungskomponenten, die den gesamten RAG-Flow steuern. Sie transformieren User-Queries, rufen relevante Dokumente aus dem Vector Store ab und bereiten diese als Kontext für das Chat-Modell auf.


## Domain

Im Versicherungs-Kontext orchestriert der RAG Advisor den kompletten Informationsbeschaffungsprozess:

- **Query-Transformation**: User-Frage wird optimiert für die Vector-Suche
- **Kontext-Retrieval**: Relevante Versicherungsbedingungen werden aus Qdrant gefunden
- **Document-Processing**: Gefundene Dokumente werden gefiltert und angereichert
- **Prompt-Integration**: Kontextinformationen werden dem System-Prompt beigefügt
- **Antwortgenerierung**: Das Modell antwortet basierend auf Kontext + User-Frage

Dies ermöglicht präzise, faktenbasierte Antworten statt Halluzinationen.


## Dependencies

Bevor wir den RAG Advisor konfigurieren, benötigen wir die notwendige Spring AI Dependency.

### Starter für RAG Advisor

Die Dependency für RAG Orchestrierung:

```xml
<!-- Enterprise RAG Advisor -->
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-rag</artifactId>
</dependency>
```

Diese Dependency stellt Spring AI folgende Komponenten bereit:

- `RetrievalAugmentationAdvisor` — Master-Orchestrator für den RAG-Flow
- `VectorStoreDocumentRetriever` — Konfigurierbare Vector-Suche
- `QueryTransformer` Interface — Hook für Query-Transformation
- `DocumentPostProcessor` Interface — Hook für Document-Verarbeitung
- `QueryAugmenter` — Integration von Kontext in den Prompt


## Architecture

**Verschiedene RAG-Flows**: Es gibt nicht nur einen RAG-Ansatz. Das "Modular RAG" Paper beschreibt verschiedene RAG-Implementierungsmuster, je nach Anforderung:

- **Sequenzieller RAG-Flow** (dieser Kurs): Retrieve → Process → Generate
- **Advanced RAG-Flows**: Pre-Retrieval, Iterative Retrieval, Hybrid-Approaches
- **Multi-Modul RAG**: Komplexe Szenarien mit mehreren Retrieval-Strategien

Spring AI hat sich für einen **sequenziellen, erweiterbaren Ansatz** entschieden, bei dem man sich durch eigene Implementierungen einhängen kann (Hooks):

- **Pre-Retrieval**: QueryTransformer
- **Retrieval**: VectorStoreDocumentRetriever
- **Post-Retrieval**: DocumentPostProcessor
- **Generation**: ContextualQueryAugmenter

Dies ermöglicht einfache Basis-Konfiguration und gleichzeitig tiefe Customization durch Interfaces.

Spring AI RAG Advisors arbeiten in **vier Phasen**:

```text
User fragt: "Welche Bedingungen für Unfallversicherung?"
        ↓
Phase 1: Query Transformation (Pre-Retrieval)
  InsuranceQueryTransformer.transform(Query)
  └─ Hook: Eigene Query-Optimierung
        ↓
Phase 2: Document Retrieval
  VectorStoreDocumentRetriever
  ├─ Sucht ähnliche Vektoren in Qdrant
  ├─ TopK Dokumente mit Similarity-Threshold
  └─ Payload wird extrahiert
        ↓
Phase 3: Document Post-Processing (Post-Retrieval)
  InsuranceDocumentProcessor.process(Query, Documents)
  ├─ Hook: Filtern/Validieren/Anreichern
  ├─ Quality Gating
  └─ Bereitet für Prompt vor
        ↓
Phase 4: Context Generation
  ContextualQueryAugmenter
  ├─ Kontext wird in System-Prompt integriert
  └─ Chat Model erhält: System Prompt + RAG-Kontext + User-Query
        ↓
Antwort: Basierend auf echten Versicherungsbedingungen
```

**Erweiterungspunkte (Hooks):**

| Extension Point | Funktion |
|----------|----------|
| `QueryTransformer` | Pre-Retrieval Hook — Query optimieren, Synonyme, Kontextanpassung |
| `VectorStoreDocumentRetriever` | Core Retrieval — Qdrant-Suche mit TopK + Similarity-Threshold |
| `DocumentPostProcessor` | Post-Retrieval Hook — Filtern, Ranking, Anreicherung |
| `QueryAugmenter` | Generation Hook — Kontext in Prompt integrieren |

**Komponenten:**

| Komponente | Funktion |
|-----------|----------|
| `RetrievalAugmentationAdvisor` | Master-Orchestrator für den gesamten RAG-Flow |
| `InsuranceQueryTransformer` | Implementiert QueryTransformer Hook |
| `VectorStoreDocumentRetriever` | Spring AI Retriever mit TopK + Similarity |
| `InsuranceDocumentProcessor` | Implementiert DocumentPostProcessor Hook |
| `ContextualQueryAugmenter` | Integriert Kontext in Prompt |


## Konfiguration

Die RAG Advisors werden in `InsuranceAssistantConfiguration` beim `ChatClient`-Aufbau registriert. Die Konfiguration besteht aus **zwei Teilen**:

**1. VectorStoreDocumentRetriever Bean** — Konfiguriert die Retrieval-Parameter:

```java
@Bean
public VectorStoreDocumentRetriever creaStoreDocumentRetriever(VectorStore vectorStore) {
    return VectorStoreDocumentRetriever.builder()
            .vectorStore(vectorStore)
            .topK(3)                          // Top-3 Dokumente zurückgeben
            .similarityThreshold(0.8)         // Nur Hits mit > 0.8 Ähnlichkeit
            .build();
}
```

**2. RetrievalAugmentationAdvisor Bean** — Orchestriert die gesamte RAG-Pipeline:

```java
@Bean
public RetrievalAugmentationAdvisor creaRetrievalAugmentationAdvisor(
        VectorStoreDocumentRetriever vectorStoreDocumentRetriever) {
    return RetrievalAugmentationAdvisor.builder()
            .queryTransformers(new InsuranceQueryTransformer())        // Pre-Retrieval Hook
            .documentRetriever(vectorStoreDocumentRetriever)           // Retrieval Core
            .documentPostProcessors(new InsuranceDocumentProcessor())  // Post-Retrieval Hook
            .queryAugmenter(ContextualQueryAugmenter.builder()
                    .allowEmptyContext(true)                           // Generation Hook
                    .build())
            .build();
}
```

Der `RetrievalAugmentationAdvisor` wird dann in den `ChatClient` injiziert und als Advisor registriert.


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


### ChatClient mit Advisors

Der `ChatClient` wird mit den Advisors konfiguriert:

```java
@Bean
public ChatClient createClient(ChatClient.Builder chatClientBuilder,
        MessageChatMemoryAdvisor messageChatMemoryAdvisor,
        InsuranceAssistantCustomerDetailsTool insuranceCustomerDetailsTool,
        RetrievalAugmentationAdvisor retrievalAugmentationAdvisor) {

    var chatClient = chatClientBuilder.defaultOptions(createChatOptions())
            .defaultSystem(createSystemPrompt().toString())
            .defaultAdvisors(messageChatMemoryAdvisor)                    // Chat History
            .defaultAdvisors(a -> a.param(ChatMemory.CONVERSATION_ID, "InsuranceAssistent"))
            .defaultTools(insuranceCustomerDetailsTool)                  // Tools
            .defaultToolContext(new HashMap<String, Object>(Map.of("session", "No Id")))
            .defaultAdvisors(retrievalAugmentationAdvisor)                // RAG Advisor
            .build();
    return chatClient;
}
```

**Advisor-Reihenfolge:**

1. **Chat Memory Advisor** — Verwaltet Gesprächsverlauf
2. **Retrieval Augmentation Advisor** — RAG-Flow (Query → Retrieve → Process → Augment)
3. Das Chat-Modell erhält: Memory + RAG-Kontext + User-Query

Alle Advisors wirken zusammen, um eine vollständige, kontextbewusste Antwort zu generieren.


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

