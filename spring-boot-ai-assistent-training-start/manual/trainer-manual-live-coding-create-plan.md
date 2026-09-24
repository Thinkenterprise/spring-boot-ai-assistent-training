# Anleitung: Die Steps-Sektion erstellen und prüfen

Diese Anleitung richtet sich an die Autorin/den Autor der Schulung (den Trainer beim
Aufsetzen). Sie beschreibt, wie die `## Steps`-Sektion einer Session entsteht und wie Du
prüfst, dass sie korrekt ist.

> **Warum das wichtig ist:** Die Steps-Sektion ist der *Vertrag*, den der Command `/step`
> im Kurs ausführt. Sie ist maßgeblich — nicht der Fließtext oder die (evtl. gekürzten)
> Code-Snippets der Doku. Der tatsächliche Code kommt immer vollständig aus der **finalen
> Implementierung**.

---

## 1 Was in der Steps-Sektion steht — und was nicht

Die Steps-Sektion listet pro Artefakt nur **Pfad**, **Operation** und optional einen
**Scope**. Sie enthält **niemals** Code. Der Code bleibt vollständig in der finalen
Implementierung; `/step` holt ihn von dort.

## 2 Das verbindliche Format

Jeder Eintrag hat die Form:

```text
- <Pfad> — <Operation> — <Scope optional>
```

Trenner ist immer **Leerzeichen–Gedankenstrich–Leerzeichen** (` — `). Die Einträge werden
nach den vier Sektionsnamen gruppiert. Vollständige Vorlage:

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

**Pfad:** relativ zur Projektwurzel und **identisch** in finaler Implementierung und
Skeleton. `/step` liest ihn unter der finalen Implementierung und schreibt ihn unter dem
Skeleton.

**Operation** (festes Vokabular):

| Operation | Wann | Bedeutung |
|---|---|---|
| `neu anlegen` | Die Datei taucht in diesem Schritt **zum ersten Mal** auf. | Datei neu erzeugen. |
| `ergänzen` | Die Datei kam in einer **früheren** Session schon vor. | Inhalt hinzufügen, Bestehendes erhalten. |
| `ersetzen` | Bestehendes wird bewusst **ausgetauscht**. | Inhalt überschreiben. |

**Scope** (optional, aber wichtig beim inkrementellen Fall): grenzt ein, *welcher Teil*
übertragen wird — z. B. `Methode: chat(...)` bei `.java`, `Keys: ...` bei Konfiguration,
`Dependency: ...` bei Library. Ohne Scope wird die Datei als Ganzes übernommen. Der Scope
ist der Hebel, mit dem eine über mehrere Sessions **wachsende** Klasse korrekt entsteht,
ohne dass Marker im Code nötig sind.

> **Hinweis zur Reihenfolge:** Im Steps-Block ist die Gruppenreihenfolge Library →
> Konfiguration → Implementierung → Test (Build-Reihenfolge: erst Abhängigkeiten, dann
> Konfiguration, dann Code, dann Test). Das ist unabhängig davon, in welcher Reihenfolge
> Du die Abschnitte im Fließtext der Session didaktisch darstellst.

## 3 Erstellen

### 3.1 Automatisch mit `/sessionSteps` (empfohlen)

- **Erstanlage der ganzen Schulung:** `/sessionSteps all` — verarbeitet alle Sessions in
  aufsteigender ID-Reihenfolge. Die Reihenfolge ist wichtig, damit `neu anlegen` vs.
  `ergänzen` und der Scope korrekt hergeleitet werden.
- **Einzelne Session:** `/sessionSteps 2.3`.
- Das Ergebnis wird **erst angezeigt**, dann auf Deine Bestätigung geschrieben.
- Arbeite alle `TODO (manuell prüfen)`-Hinweise ab — das sind Stellen, die sich nicht
  sicher der finalen Implementierung zuordnen ließen und **nicht** geraten wurden.

### 3.2 Manuell

Sinnvoll für Korrekturen oder Sonderfälle. Halte Dich an das Format aus Abschnitt 2 und
verwende die **echten** Pfade aus der finalen Implementierung.

## 4 Prüfen — Checkliste

Gehe jede Steps-Sektion einmal durch:

- [ ] **Pfade existieren** so in der finalen Implementierung.
- [ ] **Operation stimmt:** Erstauftritt → `neu anlegen`; spätere Session → `ergänzen`;
      bewusster Austausch → `ersetzen`.
- [ ] **Scope gesetzt**, wo eine Datei in dieser Session nur teilweise entsteht
      (wachsende Klasse).
- [ ] **Kein Code** in der Steps-Sektion — nur Pfad/Operation/Scope.
- [ ] **Format eingehalten:** Trenner ` — `, Gruppierung unter den vier `###`-Überschriften.
- [ ] **Test-Sektion** ist konkret ausführbar (Kommando + erwartete Ausgabe) oder als
      manuelle Prüfung klar beschrieben.
- [ ] Keine offenen `TODO`-Marker mehr.

## 5 Praktische Verifikation (der sichere Weg)

Die Checkliste prüft die Form. Ob der Vertrag *inhaltlich* stimmt, zeigt ein Probelauf:

1. Sorge für einen **sauberen Ausgangsstand** des Skeleton (bis zur Vorsession).
2. Führe `/step <ID>` für die Session aus.
3. Vergleiche das Ergebnis im Skeleton mit der finalen Implementierung für diesen Schritt
   — es sollte (im definierten Scope) übereinstimmen. Das ist Abnahmekriterium **A-1** der
   Spezifikation.
4. Führe die **Test-Sektion** aus (A-2).
5. **Idempotenz prüfen:** `/step <ID>` ein zweites Mal ausführen — es darf kein doppelter
   oder widersprüchlicher Inhalt entstehen (Q-2).

## 6 Häufige Fehler

- **Doku-Snippet als Quelle genommen:** Der gekürzte Code im Fließtext ist nie maßgeblich —
  Quelle ist immer die finale Implementierung.
- **Operation falsch:** Eine bereits existierende Datei mit `neu anlegen` würde Bestehendes
  überschreiben. Im Zweifel die früheren Sessions prüfen (oder `/sessionSteps all` in
  Reihenfolge laufen lassen).
- **Scope vergessen:** Ohne Scope wird die ganze (fertige) Datei eingespielt, obwohl der
  Schritt nur einen Teil einführen sollte.
- **Formatdrift:** Anderer Trenner oder abweichende Überschriften erschweren das
  maschinelle Lesen. Bleib exakt beim Format aus Abschnitt 2.
