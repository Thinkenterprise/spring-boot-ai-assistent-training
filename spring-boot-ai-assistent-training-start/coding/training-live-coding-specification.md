# Spezifikation — Claude-Code-gestützte Code-Einspielung für die Spring-AI-Schulung

**Version:** 1.1 · **Status:** Entwurf · **Adressat:** Claude Code

---

## 1 Ziel

Diese Spezifikation legt fest, wie Claude Code die Trainerin oder den Trainer während
der Schulung unterstützt: Auf ein knappes Kommando hin spielt Claude Code den zu einem
Kursschritt gehörenden, **vollständigen** Quellcode aus einer fertigen
Referenzimplementierung in ein zunächst leeres Gerüstprojekt ein. Angestrebt wird ein
reproduzierbarer, fehlerarmer Ablauf, der manuelle Übertragungsarbeit ersetzt und den
didaktischen Fluss der Schulung nicht unterbricht.

## 2 Gegenstand

Gegenstand sind fünf Artefakte, die in drei klar getrennten Rollen zusammenwirken. Die
Trennung ist die tragende Idee der Spezifikation: Jede Frage — *welcher Code?*,
*welcher Umfang?*, *welche Regel gilt immer?* — hat genau einen Ort, an dem sie
beantwortet wird.

| Rolle | Artefakt | Zuständig für |
|---|---|---|
| Quelle der Wahrheit (Code) | Finale Implementierung | Den vollständigen, lauffähigen Quellcode. Schreibgeschützt. |
| Vertrag je Schritt | Session-Dokument (.md), Abschnitt „Steps" | Welche Artefakte zu diesem Schritt gehören und wie sie einzuspielen sind. |
| Standregeln (immer) | CLAUDE.md | Die dauerhaft geltenden Arbeitsregeln für Claude Code. |
| Zielprojekt | Skeleton | Das Projekt, in das eingespielt wird. Einziger Schreibort. |
| Schnittstelle | Slash-Commands `/step`, `/sessionSteps` | Kommandos für den Kursbetrieb (`/step`) und die einmalige Einrichtung (`/sessionSteps`). |

## 3 Geltungsbereich

Diese Abgrenzung bezieht sich auf den **Kursbetrieb** mit `/step`. **Enthalten** ist das
Einspielen von Library-, Konfigurations- und Implementierungscode sowie das Bereitstellen
der Testanweisung eines Schritts. **Nicht enthalten** sind im Kursbetrieb jede Änderung an
der Dokumentation und an der finalen Implementierung sowie das eigenständige Erfinden von
Code, der nicht in der finalen Implementierung steht. Die Testanweisung wird bereitgestellt
und nur auf ausdrückliche Aufforderung ausgeführt; ein fehlschlagender Test führt nicht zu
eigenmächtigen Änderungen an der Referenz. **Ausgenommen** ist die einmalige Einrichtung:
Setup-Commands (z. B. `/sessionSteps`) dürfen die „Steps"-Sektion einer Session generieren
und schreiben dazu gezielt in das Session-Dokument (siehe O-4).

## 4 Restriktionen und Rahmenbedingungen

| Nr. | Rahmenbedingung |
|---|---|
| R-1 | Technischer Kontext: Java, Spring Boot / Spring AI, Maven (`pom.xml`). |
| R-2 | Die finale Implementierung ist schreibgeschützt und alleinige Quelle der Wahrheit für Code. |
| R-3 | Pfade sind in finaler Implementierung und Skeleton identisch und relativ zur Projektwurzel. |
| R-4 | Code-Blöcke im Fließtext der Doku dürfen gekürzt sein und sind für die Einspielung nicht maßgeblich. |
| R-5 | Jede Session besitzt eine eindeutige ID und eine einheitlich aufgebaute „Steps"-Sektion. |

## 5 Konventionen

Die Konventionen sind das Herzstück: Sie machen die Zuordnung von einem Kommando zu
konkreten Dateien eindeutig und maschinell verarbeitbar.

### 5.1 Koordinaten-Schema

Die Schulung besteht aus *n* Topics mit je *m* Sessions. Jede Session trägt eine stabile
ID der Form `Topic.Session` (z. B. `2.3`) und gliedert sich in vier feste, stets gleich
benannte Sektionen: Implementierung, Konfiguration, Library und Test.

### 5.2 Zuordnung Sektion → Dateityp

| Sektion | Dateityp / Ziel |
|---|---|
| Library | Änderung an `pom.xml` (Dependencies). |
| Konfiguration | `application.yml` / `.properties` oder eine `@Configuration`-Klasse. |
| Implementierung | `.java`-Dateien. |
| Test | Ausführbare Prüfung (z. B. curl) oder manuelle Prüfschritte. |

### 5.3 Zuordnungsmechanismus: die Steps-Sektion

Jede Session führt in ihrem Abschnitt `## Steps` die zum Schritt gehörenden Artefakte auf
— gegliedert nach denselben vier Sektionsnamen, damit gezielt gefiltert werden kann. Jeder
Eintrag nennt ausschließlich **Pfad**, **Operation** und optional einen **Scope**, niemals
den Code selbst:

```markdown
## Steps

### Library
- pom.xml — ergänzen — Dependency: spring-ai-openai-...-starter

### Konfiguration
- src/main/resources/application.yml — ergänzen — Keys: spring.ai.openai.api-key

### Implementierung
- src/main/java/.../ChatService.java — neu anlegen
- src/main/java/.../ChatController.java — ergänzen — Methode: chat(...)

### Test
- Kommando: curl -X POST localhost:8080/chat -d '{"message":"Hallo"}'
- Erwartung: JSON mit Feld "answer"
```

Die **Operation** stammt aus einem festen Vokabular: `neu anlegen` (Datei neu erzeugen),
`ergänzen` (Inhalt hinzufügen, Bestehendes erhalten) und `ersetzen` (Inhalt austauschen).
Der optionale **Scope** (Methode oder Abschnitt) löst den inkrementellen Fall: eine über
mehrere Sessions wachsende Klasse wird schrittweise ergänzt, ohne dass Marker im Code nötig
sind.

### 5.4 Vollständigkeitsregel

Der einzuspielende Code wird stets in voller Länge aus der finalen Implementierung
übernommen. Code-Snippets aus dem Fließtext der Dokumentation sind rein didaktisch,
möglicherweise gekürzt und werden für die Einspielung ignoriert. Damit ist das Problem
unterschiedlich vollständiger Doku-Snippets strukturell ausgeschlossen.

## 6 Anforderungen

### 6.1 Funktionale Anforderungen (Verhalten von Claude Code)

| Nr. | Anforderung |
|---|---|
| F-1 | Die Session zur angegebenen ID auflösen und ihre „Steps"-Sektion lesen. |
| F-2 | Auf Wunsch nur eine benannte Untersektion (Library, Konfiguration, Implementierung, Test) verarbeiten, sonst alle in dieser Reihenfolge. |
| F-3 | Für jedes gelistete Artefakt die vollständige Fassung aus der finalen Implementierung an denselben Pfad im Skeleton übertragen. |
| F-4 | Die angegebene Operation und ggf. den Scope beachten. |
| F-5 | Die Testanweisung bereitstellen und nur auf Aufforderung ausführen. |

### 6.2 Nicht-funktionale Anforderungen (Qualität)

| Nr. | Anforderung |
|---|---|
| Q-1 | Vollständigkeit: kein gekürzter Doku-Code wird übernommen. |
| Q-2 | Idempotenz: ein erneuter Aufruf erzeugt keinen doppelten oder inkonsistenten Zustand. |
| Q-3 | Integrität der Referenz: die finale Implementierung wird nie verändert. |
| Q-4 | Strukturtreue: nur die im Schritt genannten Dateien werden geändert; Pfade bleiben erhalten. |
| Q-5 | Nachvollziehbarkeit: die vorgenommenen Datei-Operationen werden knapp zurückgemeldet. |

### 6.3 Organisatorische Anforderungen

| Nr. | Anforderung |
|---|---|
| O-1 | Die Standregeln liegen in `CLAUDE.md` in der Projektwurzel und sind versioniert. |
| O-2 | Der Command liegt als `.claude/commands/step.md` vor und ist versioniert. |
| O-3 | Jede Session enthält eine konforme „Steps"-Sektion (Autorenpflicht des Trainers; kann per Setup-Command erzeugt werden). |
| O-4 | Setup-Commands (z. B. `/sessionSteps`) dürfen die „Steps"-Sektion generieren und schreiben dazu in das Session-Dokument; das Ergebnis wird vor dem Schreiben zur Prüfung angezeigt. Der Kursbetrieb mit `/step` verändert die Doku nicht. |

## 7 Interaktionsprotokoll

Das Kommando hat die Form `/step SESSION-ID [SEKTION]`. Ohne Sektionsangabe wird die
gesamte Session verarbeitet, mit Angabe nur die genannte Sektion:

```text
/step 2.3                 # gesamte Session 2.3
/step 2.3 Konfiguration   # nur die Sektion Konfiguration
```

Ablauf: (1) Session per ID finden, (2) Steps-Sektion bzw. genannte Untersektion lesen,
(3) je Artefakt die vollständige Fassung aus der finalen Implementierung gemäß Operation
und Scope ins Skeleton einspielen, (4) Ergebnis knapp zurückmelden. Der vollständige
Anweisungstext liegt in `.claude/commands/step.md`; die dauerhaft geltenden Regeln stehen
in `CLAUDE.md`, damit das Kommando kurz bleibt.

## 8 Abnahme

| Nr. | Abnahmekriterium |
|---|---|
| A-1 | Nach `/step` entspricht der eingespielte Code im Skeleton für den betreffenden Schritt (im definierten Scope) der finalen Implementierung. |
| A-2 | Der Test der Sektion liefert die erwartete Ausgabe. |
| A-3 | Die finale Implementierung ist unverändert; im Skeleton wurden nur die im Schritt genannten Dateien geändert. |
