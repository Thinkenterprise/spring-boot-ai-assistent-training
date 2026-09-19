# Trainer-Ablauf: Schritt für Schritt

Dieses Runbook beschreibt, wie Du als Trainer vorgehst — einmal beim **Einrichten** der
Schulung und dann im laufenden **Kursbetrieb**. Details zur Steps-Sektion stehen in
`Anleitung-Steps-Sektion.md`.

---

## Teil A — Einmalige Einrichtung

**1. Verzeichnisstruktur herstellen.** Die finale Implementierung liegt als Geschwister
neben dem Skeleton; die Dokumentation liegt im Skeleton:

```text
Schulung/
├── spring-boot-ai-training-final/     ← Referenz (read-only), NICHT in VSC geöffnet
└── <dein-skeleton-projekt>/           ← DIESES Verzeichnis in VS Code öffnen
    ├── CLAUDE.md
    ├── .claude/
    │   ├── commands/
    │   │   ├── step.md
    │   │   └── sessionSteps.md
    │   └── settings.local.json        ← macht final lesbar
    ├── dokumentation/                 ← Sessions als .md
    ├── src/ …
    └── pom.xml
```

**2. Dateien ablegen.** `CLAUDE.md` in die Skeleton-Wurzel; `step.md` und `sessionSteps.md`
nach `.claude/commands/` (ggf. `mkdir -p .claude/commands`); `settings.local.json` nach
`.claude/`.

**3. Namen eintragen (nur an zwei Stellen).**
- In `CLAUDE.md` im Block „Verzeichnisse" den echten Namen der finalen Implementierung.
- In `.claude/settings.local.json` unter `additionalDirectories` denselben Pfad.

**4. Skeleton in VS Code öffnen** und Claude Code darin starten.

**5. Steps-Sektionen erzeugen.** `/sessionSteps all` ausführen, das Ergebnis prüfen und
alle `TODO`-Hinweise abarbeiten (siehe `Anleitung-Steps-Sektion.md`).

**6. Rauchtest.** Für die erste Session `/step <ID>` gegen einen sauberen Skeleton-Stand
laufen lassen und mit der finalen Implementierung vergleichen. Kann Claude Code die finale
Implementierung nicht lesen, einmalig `/add-dir ../spring-boot-ai-training-final` ausführen
(oder den Pfad in `settings.local.json` prüfen).

## Teil B — Während der Schulung (je Session)

**1. Session besprechen.** Geh die Session-Doku mit den Teilnehmern durch (Implementierung,
Konfiguration, Library, Test).

**2. Code einspielen.** Wenn es an den Code geht, gib Claude Code das Kommando:
- ganze Session: `/step 2.3`
- gezielt eine Sektion: `/step 2.3 Konfiguration` (oder `Library`, `Implementierung`, `Test`)

**3. Ergebnis zeigen.** Claude Code meldet knapp, welche Dateien mit welcher Operation
geändert wurden — kurz im Editor zeigen.

**4. Testen.** Die Testanweisung der Session ausführen (auf Deine Aufforderung) und das
Ergebnis mit den Teilnehmern ansehen.

**5. Weiter** zur nächsten Session.

## Teil C — Bei Änderungen an Doku oder Code

Hast Du eine Session-Doku oder die finale Implementierung angepasst, die Steps-Sektion
neu ableiten: `/sessionSteps <ID>`. Claude Code zeigt einen **Diff** gegen die bestehende
Fassung; nach Deiner Bestätigung wird geschrieben. Manuelle Anpassungen werden dabei nicht
stillschweigend überschrieben.

## Kommando-Spickzettel

| Kommando | Zweck |
|---|---|
| `/sessionSteps all` | Einmalig: Steps-Sektionen aller Sessions erzeugen (in Reihenfolge). |
| `/sessionSteps 2.3` | Steps-Sektion einer Session erzeugen oder als Diff aktualisieren. |
| `/step 2.3` | Kursbetrieb: ganze Session einspielen. |
| `/step 2.3 Konfiguration` | Kursbetrieb: nur eine Sektion einspielen. |
| `/add-dir ../spring-boot-ai-training-final` | Finale Implementierung für die Session lesbar machen (falls nötig). |

## Wenn etwas klemmt

- **`/step` oder `/sessionSteps` taucht nicht auf:** Liegen die Dateien in
  `.claude/commands/` **im Skeleton**? Ggf. Claude Code neu starten. Test: `/` tippen —
  die Commands sollten in der Liste erscheinen.
- **Claude Code kommt nicht an die finale Implementierung:** Pfad in `settings.local.json`
  prüfen; falls der relative Pfad nicht greift, absoluten Pfad eintragen oder einmalig
  `/add-dir` nutzen. Beim ersten Zugriff kann eine Bestätigung nötig sein.
- **Falscher Code oder falscher Umfang eingespielt:** meist eine falsche Operation oder ein
  fehlender Scope in der Steps-Sektion — in `Anleitung-Steps-Sektion.md`, Abschnitt 4–6,
  nachsehen und die Steps-Sektion korrigieren.
- **Doppelter Inhalt nach zweimaligem `/step`:** Idempotenz-Problem; Operation/Scope prüfen.
- **Regeln greifen nicht wie erwartet:** `CLAUDE.md` muss in der Skeleton-Wurzel liegen
  (nur von dort wird sie automatisch geladen).
