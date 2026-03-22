# Foundation Model

## Ziel

Ziel dieser Einheit ist es, das Spring AI **Model API** zu verstehen. Spring AI definiert ein dreistufiges Model API, das einen flexiblen und provider-unabhängigen Zugriff auf verschiedene AI-Modelle ermöglicht.


## Architecture

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

**Content Model API** erweitert das Generic Model API um ein konkretes Chat Model API mit allen für die Chat-Interaktion notwendigen Komponenten.

**Provider Model API** enthält die konkreten Implementierungen für spezifische Anbieter wie Ollama, OpenAI oder Anthropic.

> **Wichtig:** Der große Vorteil dieser Architektur ist die Flexibilität: Die Anwendung kann gegen das Content Model API programmiert werden, ohne sich an einen konkreten Modell-Anbieter zu binden. Ein Anbieterwechsel erfordert nur eine Konfigurationsänderung.


## Libraries

Die Einbindung eines Modell-Providers erfolgt über einen Spring AI Starter. Für Ollama:

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-model-ollama</artifactId>
</dependency>
```

Anbieter und Modelle im Überblick:

| Anbieter | Modell | Typ |
|---|---|---|
| **Ollama** | LLaMA 3.3 | Lokal, Open Source |
| **OpenAI** | GPT-4o | Cloud, kommerziell |
| **Anthropic** | Claude | Cloud, kommerziell |
| **Meta** | LLaMA | Open Source |


## Configuration

Die Modellkonfiguration erfolgt über `application.yaml`. Über **ChatOptions** werden Parameter wie `temperature` und `max-tokens` gesteuert:

| Parameter | Beschreibung |
|---|---|
| `temperature` | Zufälligkeit der Antwort (0.0 = deterministisch, 1.0 = kreativ) |
| `max-tokens` | Maximale Länge der Antwort |
| `num-ctx` | Kontextfenstergröße |

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
3. Prüfen, ob das Modell korrekt konfiguriert ist (z. B. über die Chat-UI oder einen curl-Aufruf)
4. Temperature auf `0.0` setzen und mehrfach dieselbe Frage stellen – die Antworten sollten nahezu identisch sein
