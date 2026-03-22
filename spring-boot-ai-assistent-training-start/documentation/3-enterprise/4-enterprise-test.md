# Enterprise Test

## Ziel

Ziel dieser Einheit ist es, die Qualität des AI-Assistenten durch **Tests** sicherzustellen. Da Modelle nicht deterministisch sind und halluzinieren können, müssen die Ausgaben systematisch überprüft werden.


## Motivation

Die Antworten eines Modells sind möglicherweise nicht immer relevant, angemessen oder sachlich richtig. Klassische Testansätze, die auf exakter Übereinstimmung basieren, funktionieren für AI-Assistenten nicht, da dieselbe Anfrage leicht unterschiedliche Antworten erzeugen kann.

Spring AI kombiniert daher zwei Ansätze:

- **Tests** – systematische Überprüfung des Verhaltens und Erkennung von Regressionen
- **Evaluatoren** – automatische Bewertung der Antwortqualität anhand definierter Kriterien wie Faktentreue oder Relevanz

> **Wichtig:** Evaluatoren nutzen wiederum ein Modell – ein sogenanntes **Judge Model** – um die Qualität der Antwort zu bewerten. Sie prüfen also nicht auf Wortgleichheit, sondern auf semantische Korrektheit.


## Libraries

```xml
<!-- Testcontainers für Spring Boot -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-testcontainers</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>

<!-- Spring AI Testcontainers-Support -->
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-spring-boot-testcontainers</artifactId>
    <scope>test</scope>
</dependency>
```


## Implementation

### Testcontainers

Für das Testen eines AI-Assistenten werden in der Regel Infrastrukturkomponenten benötigt (Modell, Vektordatenbank). Spring AI unterstützt **Testcontainers**, um diese automatisch für Tests bereitzustellen:

```java
@SpringBootTest
@Testcontainers
class InsuranceApplicationTests {

    @Container
    @ServiceConnection
    static QdrantContainer qdrant = new QdrantContainer("qdrant/qdrant");

    @Container
    @ServiceConnection
    static OllamaContainer ollamaContainer = new OllamaContainer("ollama/ollama");

    @Autowired
    private InsuranceChatService chatService;

    @Test
    void shouldAnswerInsuranceQuestion() {
        String response = chatService.chatService(
            "Welche Lebensversicherungen bieten Sie an?"
        );
        assertThat(response).isNotBlank();
    }
}
```

Die Annotation `@ServiceConnection` bewirkt, dass die Verbindungsinformationen automatisch in die Spring AI Konfiguration übernommen werden. Die Container werden vor dem Test gestartet und nach dem Test automatisch gestoppt.

### Evaluator

Spring AI stellt **Evaluatoren** bereit, die die Qualität einer Antwort automatisch anhand von Kriterien wie Faktentreue (`RelevancyEvaluator`) oder Vollständigkeit bewerten:

```java
@Test
void shouldProvideRelevantAnswer() {
    String question = "Welche Lebensversicherungen bieten Sie an?";
    ChatResponse response = chatService.chatServiceWithResponse(question);

    EvaluationRequest evaluationRequest = new EvaluationRequest(
        question,
        List.of(), // Kontextdokumente aus RAG
        response
    );

    EvaluationResponse evaluationResponse = relevancyEvaluator.evaluate(evaluationRequest);
    assertThat(evaluationResponse.isPass()).isTrue();
}
```


## Test

1. Tests ausführen: `mvn test`
2. Testcontainers starten automatisch die notwendigen Infrastrukturkomponenten
3. Prüfen, ob die AI-Antworten die definierten Qualitätskriterien erfüllen
4. Tests in eine CI/CD-Pipeline integrieren, um Qualitätsregressionen frühzeitig zu erkennen
