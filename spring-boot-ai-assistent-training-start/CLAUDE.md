# Projekt: Spring-AI-Schulung — Code-Einspielung durch Claude Code

Diese Datei enthält die **dauerhaft geltenden Regeln**. Sie werden zu Beginn jeder
Session automatisch geladen.

**Commands:**
- `/step <SessionID> [Sektion]` — Kursbetrieb: spielt den Code eines Schrittes aus der
  finalen Implementierung ins Skeleton ein (siehe `.claude/commands/step.md`).
  Verändert die Doku **nicht**.
- `/sessionSteps <SessionID | all>` — einmalige Einrichtung: erzeugt bzw. aktualisiert
  die `## Steps`-Sektion einer Session aus Doku und finaler Implementierung
  (siehe `.claude/commands/sessionSteps.md`). Zeigt das Ergebnis vor dem Schreiben.

## Verzeichnisse — EINZIGE STELLE FÜR DIE ECHTEN NAMEN
Trage hier die tatsächlichen Ordnernamen ein. Überall sonst (Regeln, Commands) wird
nur über die **Rolle** gesprochen — finale Implementierung, Skeleton, Dokumentation.
Ein Namenswechsel erfordert daher nur hier eine Änderung.

- **Skeleton** (Arbeitswurzel, einziger Schreibort): das in VS Code geöffnete Projekt,
  also die aktuelle Arbeitswurzel `.`
- **Finale Implementierung** (READ-ONLY, Referenz): `../spring-boot-ai-assistent-training-final`
- **Dokumentation** (Sessions als .md): `documentation/`  (liegt im Skeleton)

> Diese Angaben liest Claude Code als Definitionen — es sind keine Shell-Variablen mit
> automatischer Ersetzung. Der Name der finalen Implementierung muss nur hier stimmen.
>
> Die finale Implementierung liegt **außerhalb** der Arbeitswurzel und muss der Session
> zugänglich gemacht werden — dauerhaft über `.claude/settings.local.json`
> (`permissions.additionalDirectories`) oder pro Session über
> `/add-dir ../spring-boot-ai-assistent-training-final`. Skeleton und Dokumentation liegen
> innerhalb der Arbeitswurzel und brauchen das nicht.

## Pfadauflösung in den Steps
Ein Step-Pfad wie `src/main/java/.../ChatService.java` ist relativ zur Projektwurzel und
in finaler Implementierung und Skeleton **identisch**:
- Lesen aus der finalen Implementierung: `<Finale Implementierung>/<Step-Pfad>`
- Schreiben ins Skeleton: `<Step-Pfad>` (relativ zur Arbeitswurzel)

## Rollen der Artefakte
- **Finale Implementierung** — der vollständige, lauffähige Code. Alleinige Quelle
  der Wahrheit. Wird **nie** verändert.
- **Session-Dokument (.md), Abschnitt `## Steps`** — der Vertrag für den jeweiligen
  Schritt: welche Artefakte, welche Operation, welcher Scope.
- **Skeleton** — das Zielprojekt. **Einziger** Schreibort.

## Grundregeln (gelten immer)
1. **Vollständigkeit:** Code wird IMMER in voller Länge aus der finalen
   Implementierung übernommen. Code-Snippets im Fließtext der Doku sind rein
   didaktisch, evtl. gekürzt und **nicht maßgeblich** — ignorieren.
2. **Referenz ist tabu:** Die finale Implementierung wird nie verändert.
3. **Nur ins Skeleton schreiben**, und zwar am **selben relativen Pfad** wie in der
   finalen Implementierung.
4. **Maßgeblich** für Umfang und Ziel eines Schrittes ist die `## Steps`-Sektion der
   jeweiligen Session — nicht der umgebende Fließtext.
5. **Operationen** aus festem Vokabular:
   - `neu anlegen` — Datei neu erzeugen.
   - `ergänzen` — Inhalt hinzufügen, Bestehendes erhalten.
   - `ersetzen` — Inhalt austauschen.
6. **Scope beachten:** Ist ein Scope (z. B. `Methode: chat(...)`) angegeben, nur
   diesen Teil übertragen. So wächst eine Klasse über mehrere Sessions korrekt,
   ohne Marker im Code.
7. **Idempotenz:** Ein erneuter Aufruf darf keinen doppelten oder inkonsistenten
   Zustand erzeugen (vor `ergänzen` prüfen, ob der Inhalt bereits vorhanden ist).
8. **Rückmeldung:** Am Ende knapp auflisten, welche Dateien mit welcher Operation
   geändert wurden — keine langen Erklärungen.

## Sektionen — Namen, Kürzel und Dateityp (EINZIGE STELLE)
Eine Sektion kann im Kommando über den **vollen Namen ODER das Kürzel** angesprochen
werden. Diese Tabelle ist die einzige maßgebliche Definition; die Commands beziehen sich
darauf.

| Sektion | Kürzel | Dateityp / Ziel |
|---|---|---|
| Library | l | Änderung an `pom.xml` (Dependencies). |
| Konfiguration | c | `application.yml` / `.properties` oder `@Configuration`-Klasse. |
| Implementierung | i | `.java`-Dateien. |
| Skript | s | Skriptdateien — **HIER FESTLEGEN** (z. B. `.sh`, `.sql`, `scripts/`). |
| Test | t | Ausführbare Prüfung (z. B. curl) oder manuelle Prüfschritte; nur auf ausdrückliche Aufforderung ausführen. |

Wird keine Sektion genannt, werden alle in dieser Reihenfolge verarbeitet:
**Library → Konfiguration → Implementierung → Skript → Test.**

## Format der Steps-Sektion (so ist jede Session aufgebaut)
Pro Eintrag: `- <Pfad> — <Operation> — <Scope optional>` (Trenner: Leerzeichen–Gedankenstrich–Leerzeichen).

```markdown
## Steps

### Library
- pom.xml — ergänzen — Dependency: spring-ai-openai-...-starter

### Konfiguration
- src/main/resources/application.yml — ergänzen — Keys: spring.ai.openai.api-key

### Implementierung
- src/main/java/.../ChatService.java — neu anlegen
- src/main/java/.../ChatController.java — ergänzen — Methode: chat(...)

### Skript
- scripts/init-db.sh — neu anlegen

### Test
- Kommando: curl -X POST localhost:8080/chat -d '{"message":"Hallo"}'
- Erwartung: JSON mit Feld "answer"
```
