# Enterprise RAG

## Ziel

Ziel dieser Einheit ist es, **RAG (Retrieval Augmented Generation)** zu verstehen und im Insurance Use Case anzuwenden. RAG ist eine Methode, um einem User Prompt gezielt relevante Kontextinformationen bereitzustellen und dadurch präzisere, domänenspezifische Antworten vom Modell zu erhalten.


## Motivation

Ohne RAG antwortet das Modell ausschließlich auf Basis seines Trainingswissens. Unternehmensspezifische Daten wie aktuelle Versicherungspolicies, interne Richtlinien oder Kundendaten sind dem Modell nicht bekannt.

**Prompt Stuffing** – das direkte Einfügen von Unternehmensdaten in den Prompt – funktioniert für kleine Datenmengen, stößt aber im Enterprise-Umfeld schnell an Grenzen:

- **Performance-Problem:** Hunderte Dokumente können nicht bei jeder Anfrage vollständig geladen werden
- **Ressourcen-Problem:** Große Datenmengen überschreiten die maximale Tokengröße des Modells
- **Relevanz-Problem:** Nicht alle Daten sind für jede Anfrage relevant

RAG löst diese Probleme durch **vorheriges Indexieren** und **semantische Suche**: Abhängig vom Inhalt der Anfrage werden gezielt nur die relevanten Dokumente gesucht und in den Prompt eingefügt.


## Architecture

Die RAG-Architektur besteht aus zwei Phasen:

**Indexierungsphase (einmalig):**
```text
+----------------+    ETL    +-----------------+    Embedding    +-------------------+
| Enterprise     | -------> | ETL-Modul        | ------------> | Vektordatenbank    |
| Quellen        |          | (Extraktion,     |               | (Qdrant, PGVector) |
| (PDF, DB, ...) |          |  Transformation, |               |                    |
+----------------+          |  Laden)          |               +-------------------+
                            +-----------------+
```

**Anfragephase (pro Request):**
```text
+------------+    Prompt    +----------+    Suche    +-------------------+
| Benutzer   | ----------> | Advisor  | ----------> | Vektordatenbank   |
|            |             |          | <---------- | (semantische      |
|            |             |          |  Dokumente  |  Ähnlichkeit)     |
|            |             |          |             +-------------------+
|            |             |          |
|            |             | Prompt + Kontext
|            |             +----------+
|            |                  |
|            |                  v
|            |             +----------+
|            | <---------- | Modell   |
|            |  Antwort    |          |
+------------+             +----------+
```

**Zentrale Komponenten:**

| Komponente | Beschreibung |
|---|---|
| **Advisor** | Interceptor im Chat Client – reichert den Prompt mit Kontextdaten aus der Vektordatenbank an |
| **Vektordatenbank** | Speichert Embedding-Vektoren für semantische Suchen (z.B. Qdrant, PGVector) |
| **Embedding Model** | Erzeugt numerische Vektoren aus Texten |
| **ETL-Modul** | Lädt Unternehmensdaten (PDFs, Datenbanken) in die Vektordatenbank |

> **Hinweis:** **Embedding-Vektoren** sind numerische Repräsentationen von Texten. Ähnliche Texte ergeben ähnliche Vektoren – das ermöglicht die semantische Suche: Nicht nach Schlüsselwörtern, sondern nach inhaltlicher Bedeutung.


## Libraries

```xml
<!-- Vektordatenbank Qdrant -->
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-vector-store-qdrant</artifactId>
</dependency>

<!-- Embedding Model (via Ollama) -->
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-model-ollama</artifactId>
</dependency>
```


## Configuration

```yaml
spring:
  ai:
    ollama:
      embedding:
        model: nomic-embed-text
    vectorstore:
      qdrant:
        host: localhost
        port: 6334
        collection-name: insurance-policies
```


## Test

1. Qdrant starten: `docker run -p 6333:6333 -p 6334:6334 qdrant/qdrant`
2. Versicherungsdokumente in die Vektordatenbank laden (ETL-Schritt)
3. Anwendung starten: `mvn spring-boot:run`
4. Frage zu einer konkreten Versicherungspolice stellen, z. B.: *„Welche Leistungen sind in der Police ABC-123 enthalten?"*
5. Prüfen, ob die Antwort auf den indexierten Dokumenteninhalten basiert
