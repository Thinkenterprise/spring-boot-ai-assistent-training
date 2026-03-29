# Spring Boot AI Foundation Model Training

## Ziel

Ziel dieser Einheit ist es, den grundsätzlichen Aufbau des Spring AI **Model API** zu verstehen, das für den eigenen Use Case passende Modell auszuwählen und es in die Anwendung einzubinden. 

## Architecture

Die typische Schicht-Architektur einer Anwendung mit Spring AI:

- **User Interface**: Nimmt Benutzereingaben entgegen und zeigt Antworten an.
- **Controller**: Stellt REST-Endpunkte bereit und leitet Requests an den Service weiter.
- **Service**: Kapselt die Business-Logik und orchestriert den AI-Aufruf.
- **Chat Client**: Bietet das Fluent API und vereinfacht die Kommunikation mit dem Modell.
- **Chat Model**: Führt den eigentlichen Modellaufruf beim gewählten Provider aus.

```text
       User Interface
       (Web UI)
            ↓
       Controller
       (REST Endpoints)
            ↓
       Service
       (Business Logic)
            ↓
       Chat Client
       (Abstraction über Content Model API)
            ↓
       **Chat Model**
       **(Provider-spezifische Implementierung)**
```

**Fokus dieser Einheit:** Wir beschäftigen uns zunächst mit dem **Chat Model**, der untersten Schicht. In späteren Lektionen erweitern wir das Verständnis auf die höheren Schichten. 


Spring AI definiert ein dreistufiges Model API, das einen flexiblen und provider-unabhängigen Zugriff auf verschiedene AI-Modelle ermöglicht.


Das Spring AI Model API ist in drei Ebenen aufgebaut:

```text
+------------------------------------------+
|       Provider Model API                 |
|  OllamaChatModel | OpenAiChatModel | ...  |
+------------------------------------------+
|       Content Model API                  |
|  ChatModel | ChatOptions | Prompt        |
|  Message | ChatResponse | Generation     |
+------------------------------------------+
|       Generic Model API                  |
|  Model | ModelOptions | ModelRequest     |
|  ModelResponse | ModelResult             |
+------------------------------------------+
```



**Generic Model API** bietet einen generischen Zugriff auf ein generatives AI-Modell, unabhängig von der Modalität (Text, Bild, Audio, Video).

**Content Model API** bildet eine konkrete Modalität ab. Für textbasierte LLMs wird z.B. das Chat Model als konkrete Implementierung bereitgestellt, das alle notwendigen Komponenten für die Chat-Interaktion enthält.


**Provider Model API** enthält die konkreten Implementierungen für spezifische Anbieter wie Ollama, OpenAI oder Anthropic.

> **Wichtig:** Der große Vorteil dieser Architektur ist die Flexibilität: Die Anwendung kann gegen das Content Model API programmiert werden, ohne sich an einen konkreten Modell-Anbieter zu binden. Ein Anbieterwechsel erfordert nur eine Konfigurationsänderung.



## Libraries

**Warum Ollama für dieses Training?**

Für dieses Training verwenden wir Ollama, da wir damit Open Source Modelle lokal auf unserem Rechner ausführen können, ohne API-Kosten zu verursachen. Dies ermöglicht ein schnelles Experimentieren und ist für Lernzwecke vollkommen ausreichend.

> **Hinweis zu produktiven Anwendungen:** Für viele produktive Anwendungen reicht eine lokale Lösung nicht aus. Hier werden häufig Cloud-basierte Modelle von Anbietern wie OpenAI, Anthropic oder Google verwendet, die bessere Skalierbarkeit, höhere Verfügbarkeit und spezialisierte Modelle bieten.

Die Einbindung eines Modell-Providers erfolgt über einen Spring AI Starter. Für Ollama:

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-model-ollama</artifactId>
</dependency>
```

> **Autoconfiguration:** Spring Boot erstellt durch die Autoconfiguration automatisch die richtige Provider-spezifische Implementierung. Im Fall von Ollama wird das `OllamaChatModel` als Bean bereitgestellt, das direkt in der Anwendung verwendet werden kann. 

Anbieter und Modelle im Überblick (Auszug):

| Anbieter | Modell | Typ |
|---|---|---|
| **Ollama** | LLaMA 3.3 | Lokal, Open Source |
| **OpenAI** | GPT-4o | Cloud, kommerziell |
| **Anthropic** | Claude | Cloud, kommerziell |
| **Google** | Gemini | Cloud, kommerziell |
| **Mistral** | Mistral | Cloud, kommerziell |

> **Hinweis:** Dies ist ein kleiner Auszug der von Spring AI unterstützten Modelle. Spring AI unterstützt noch viele weitere Provider (Amazon Bedrock, Azure OpenAI, OCI GenAI u.a.). Die umfassende Unterstützung verschiedener Modelle ist einer der großen Vorteile von Spring AI und ermöglicht einen einfachen Anbieterwechsel.

## Implementierung 

In dieser Lektion konzentrieren wir uns auf die Verwendung des **Chat Model** — die unterste Schicht der Spring AI Architektur. Die Implementierung ist erstaunlich einfach:

1. Das `ChatModel` wird durch die Spring Boot Autoconfiguration automatisch als Bean bereitgestellt
2. Die Bean wird via `@Autowired` injiziert
3. Der Aufruf erfolgt über die Methode `call()` mit einem String als Eingabe

Das folgende Beispiel zeigt diese Grundstruktur:

```java
@SpringBootApplication
public class ChatApplication implements ApplicationRunner {

	@Autowired
	ChatModel model;  // Automatisch durch Autoconfiguration bereitgestellt
	
	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args); 
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String answer = model.call("Hello");
		System.out.println(answer);
	}
}
```

Diese einfache Aufrufstruktur ist eines der Stärken von Spring AI: Der Entwickler muss sich nicht um Provider-Details kümmern, sondern kann sich direkt auf die Geschäftslogik konzentrieren.


## Configuration

Die Modellkonfiguration erfolgt über `application.yaml`. Über **ChatOptions** werden Parameter wie `temperature` und `max-tokens` gesteuert:

| Parameter | Beschreibung |
|---|---|
| `temperature` | Zufälligkeit der Antwort (0.0 = deterministisch, 1.0 = kreativ) |
| `max-tokens` | Maximale Länge der generierten Antwort (Output) |
| `num-ctx` | Größe des Kontextfensters (maximale Token für Input + Output zusammen) |

> **Wichtig:** `max-tokens` sollte immer kleiner oder gleich `num-ctx` sein, da `max-tokens` nur die Antwort begrenzt, während `num-ctx` das gesamte Kontextfenster (Eingabe + Ausgabe) definiert. Im Beispiel (max-tokens=512, num-ctx=2048) kann die Antwort maximal 512 Token lang sein, während das Modell insgesamt bis zu 2048 Token im Kontext verarbeiten kann. 


```yaml
spring:
  ai:
    ollama:
      chat:
        model: llama3.3
        options:
          temperature: 0.7
          num-ctx: 2048
          max-tokens: 512
```


## Test

1. Ollama starten und das Modell laden: `ollama run llama3.3`
2. Anwendung starten: `mvn spring-boot:run`
3. Es wird auf der Konsole die Antwort auf "Hello" ausgegeben.  
