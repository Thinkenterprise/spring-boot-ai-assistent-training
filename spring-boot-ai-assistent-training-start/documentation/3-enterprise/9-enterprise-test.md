# Enterprise Test

## Ziel

In dieser Übung wird ein Integrationstest für den Insurance AI-Assistenten implementiert, der mit Testcontainern die notwendige Infrastruktur (Ollama-Modell, Qdrant-Vektordatenbank) automatisch bereitstellt. Der Fokus liegt auf dem **Evaluation Testing**: Mit dem `RelevancyEvaluator` von Spring AI wird geprüft, ob die RAG-generierten Antworten des Assistenten inhaltlich relevant und qualitativ hochwertig sind. So kann die Qualität des AI-Assistenten automatisiert und reproduzierbar gemessen werden.

## Motivation

Modellausgaben müssen überprüft werden, da **Modelle nicht deterministisch** sind und halluzinieren können. Die Antworten sind möglicherweise nicht immer relevant, angemessen oder sachlich richtig. Dies macht eine systematische Qualitätssicherung notwendig.

**Tests** ermöglichen es, das Verhalten des AI-Assistenten systematisch zu überprüfen und Regressionen zu erkennen. **Evaluatoren** bewerten die Qualität der Modellantworten automatisch anhand definierter Kriterien wie Faktentreue oder Relevanz. Da Modellantworten jedoch nicht auf exakte Übereinstimmungen, sondern auf semantische Ähnlichkeit geprüft werden müssen, kommen spezialisierte Evaluation-Klassen zum Einsatz. Beide Ansätze ergänzen sich und bilden zusammen die Grundlage für einen qualitativ hochwertigen AI-Assistenten.

> **Hinweis:** Da Modelle nicht deterministisch sind, können die Antworten bei gleicher Eingabe variieren. Tests müssen daher robuste Kriterien verwenden, die semantische Ähnlichkeit und inhaltliche Korrektheit prüfen – keine exakten String-Vergleiche.

## Architektur

Spring AI stellt für das Testen der Qualität des AI-Assistenten ein dediziertes Evaluation-API zur Verfügung:

| Klasse / Interface | Beschreibung |
|--------------------|--------------|
| `Evaluator` | Interface mit der Methode `evaluate()`, die von konkreten Implementierungen überschrieben wird |
| `EvaluationRequest` | Parameter für den Evaluator: enthält das Prompt (Eingabe), die Modellausgabe und optional den RAG-Kontext |
| `EvaluationResponse` | Ergebnis der Evaluierung: `isPass()` liefert true/false, `score()` einen numerischen Wert (0 oder 1) |
| `RelevancyEvaluator` | Bewertet, ob die Antwort des AI-Modells im Hinblick auf den abgerufenen RAG-Kontext relevant ist |
| `FactCheckingEvaluator` | Bewertet die sachliche Richtigkeit der Antwort anhand des bereitgestellten Kontexts |

Evaluatoren basieren auf einem sogenannten **Judge Model**: Ein zweites Modell bewertet die Antwort des ersten. Für die Infrastruktur im Test verwendet Spring AI das **Testcontainers**-Framework. Die Annotation `@Testcontainers` aktiviert die automatische Verwaltung der Container, `@Container` definiert einen spezifischen Container, der vor dem Test gestartet und danach automatisch gestoppt wird. Über `@ServiceConnection` werden die Verbindungsparameter automatisch konfiguriert.

Der Testfluss für die RAG-Evaluation sieht folgendermaßen aus:

1. Test ruft `InsuranceChatService.chatServiceWithResponse()` mit einer Frage auf
2. Der `ChatClient` sendet die Anfrage an Ollama; der `RetrievalAugmentationAdvisor` reichert sie mit Dokumenten aus Qdrant an
3. Die `ChatResponse` enthält die Antwort **und** die abgerufenen Dokumente im Metadata-Feld `DOCUMENT_CONTEXT`
4. Ein `EvaluationRequest` wird aus Frage, Dokumenten und Antwort zusammengestellt
5. Der `RelevancyEvaluator` bewertet mittels Judge Model die Relevanz
6. `Assertions.assertTrue(evaluationResponse.isPass())` prüft das Ergebnis

## Libraries

Folgende Dependencies müssen in der `pom.xml` eingebunden sein:

**Spring Boot Test-Unterstützung** – Basis für alle Spring Boot Integrationstests:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

**Spring Boot Testcontainers** – Integration von Testcontainern in den Spring Boot Application Context:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-testcontainers</artifactId>
    <scope>test</scope>
</dependency>
```

**Spring AI Testcontainers** – Stellt `@ServiceConnection` für Spring AI Infrastrukturkomponenten wie `OllamaConnectionDetails` und `QdrantConnectionDetails` bereit:
```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-spring-boot-testcontainers</artifactId>
    <scope>test</scope>
</dependency>
```

**Testcontainers JUnit Jupiter** – Liefert die Annotationen `@Testcontainers` und `@Container` für JUnit 5:
```xml
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>${testcontainers.version}</version>
    <scope>test</scope>
</dependency>
```

**Testcontainers Ollama** – Container-Implementierung für das Ollama-Modell:
```xml
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>ollama</artifactId>
    <version>${testcontainers.version}</version>
    <scope>test</scope>
</dependency>
```

**Testcontainers Qdrant** – Container-Implementierung für die Qdrant-Vektordatenbank:
```xml
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>qdrant</artifactId>
    <version>${testcontainers.version}</version>
    <scope>test</scope>
</dependency>
```

## Implementation

### Testklasse

Die Testklasse verwendet `@SpringBootTest` für einen vollständigen Integrationstest. `@Testcontainers` aktiviert das automatische Container-Management. Die beiden statischen Container-Felder werden mit `@Container` und `@ServiceConnection` annotiert – Spring AI konfiguriert dadurch die Verbindungsparameter automatisch:

```java
@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class InsuranceApplicationTests {

    @Container
    @ServiceConnection
    static OllamaContainer ollamaContainer = new OllamaContainer("ollama/ollama");

    @Container
    @ServiceConnection
    static QdrantContainer qdrantContainer = new QdrantContainer("qdrant/qdrant");

    @Test
    public void testRagLifeSecurityConditions(
            @Autowired InsuranceChatService insuranceChatService,
            @Autowired ChatModel chatModel) {

        // Evaluator to check the relevancy of the answer
        RelevancyEvaluator relevancyEvaluator = 
            new RelevancyEvaluator(ChatClient.builder(chatModel));

        // The test query
        String query = "Kann die vereinbarte Versicherungsdauer verlängert werden?";

        // Get response from our Chat Model with RAG Context information
        ChatResponse response = insuranceChatService.chatServiceWithResponse(query);
        String responseContent = response.getResult().getOutput().getText();

        // Get RAG Data
        @SuppressWarnings("unchecked")
        List<Document> responseDocuments = (List<Document>) response.getMetadata()
                .get(RetrievalAugmentationAdvisor.DOCUMENT_CONTEXT);

        // Create Evaluation Request
        EvaluationRequest evaluationRequest = 
            new EvaluationRequest(query, responseDocuments, responseContent);

        // Create relevancy Evaluation
        EvaluationResponse evaluationResponse = relevancyEvaluator.evaluate(evaluationRequest);

        // Execute relevancy Evaluation and Assertion
        Assertions.assertTrue(evaluationResponse.isPass());
    }
}
```

> **Hinweis:** Der `RelevancyEvaluator` erhält einen eigenen `ChatClient.Builder`, der direkt mit dem `ChatModel` initialisiert wird – nicht den vorkonfigurierten `ChatClient`-Bean. So wird das Judge Model unabhängig vom Produktiv-Assistenten betrieben.

## Test

1. **Maven-Projekt aktualisieren** – `pom.xml` nach der Dependency-Ergänzung in der IDE neu laden (Maven Reimport)
2. **InsuranceChatService erstellen** – Neue Klasse `InsuranceChatService` im Package `com.thinkenterprise.service` anlegen
3. **Testklasse anlegen** – `InsuranceApplicationTests` im Package `com.thinkenterprise.ai` unter `src/test/java` erstellen
4. **Container-Felder definieren** – `OllamaContainer` und `QdrantContainer` als statische Felder mit `@Container` und `@ServiceConnection` annotieren
5. **Testmethode implementieren** – `testRagLifeSecurityConditions()` mit `@Autowired`-Parametern für `InsuranceChatService` und `ChatModel` schreiben
6. **EvaluationRequest aufbauen** – Frage, RAG-Dokumente aus `response.getMetadata()` und Antworttext zu einem `EvaluationRequest` zusammenstellen
7. **RelevancyEvaluator ausführen** – `evaluate()` aufrufen und mit `Assertions.assertTrue(evaluationResponse.isPass())` das Ergebnis prüfen
8. **Test ausführen** – Integrationstest starten (dauert länger, da Docker-Container hochgefahren werden)
