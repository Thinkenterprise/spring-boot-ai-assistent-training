---
description: Erzeugt oder aktualisiert die "## Steps"-Sektion einer Session aus Session-Doku und finaler Implementierung.
argument-hint: <SessionID | all>
allowed-tools: Read, Glob, Grep, Edit, Write
---

Argumente: $ARGUMENTS  (eine Session-ID wie `2.3`, oder `all` für alle Sessions)

Ziel: die `## Steps`-Sektion einer Session **herleiten** — den Vertrag, den `/step`
später ausführt. Grundlage sind die Session-Doku und die finale Implementierung
(Rollen und Format siehe CLAUDE.md).

Weil dieser Vertrag maßgeblich ist, wird das Ergebnis **immer zuerst zur Prüfung
angezeigt** und erst nach meiner Bestätigung in das Session-Dokument geschrieben.

## Vorgehen

1. **Zielsessions bestimmen.**
   - Bei `all`: alle Sessions in **aufsteigender ID-Reihenfolge** verarbeiten
     (die Reihenfolge ist entscheidend für die Operations- und Scope-Herleitung).
   - Bei einer einzelnen ID: zuerst die vorhandenen `## Steps` der **früheren**
     Sessions lesen, um den bis dahin bestehenden Dateizustand zu kennen. Fehlen
     diese noch, weise darauf hin, dass die Herleitung dann unsicherer ist, und
     empfiehl für die Erstanlage einen Lauf mit `all`.

2. **Doku der Session lesen.** Aus den in CLAUDE.md definierten Sektionen (Library,
   Konfiguration, Implementierung, Skript, Test) alle referenzierten Artefakte sammeln:
   Dateipfade, Dependencies, Konfigurationsschlüssel, Skriptdateien und die gezeigten
   (evtl. gekürzten) Code-Ausschnitte.

3. **Gegen die finale Implementierung abgleichen.** Für jedes Artefakt die reale
   Datei in der finalen Implementierung lokalisieren (über Pfad oder Klassen-/
   Dateinamen).
   - **Nichts erfinden:** Nur aufnehmen, was in der finalen Implementierung
     tatsächlich auffindbar ist.
   - Lässt sich ein Doku-Bezug nicht sicher zuordnen, NICHT raten, sondern unter
     `> TODO (manuell prüfen): ...` vermerken.

4. **Operation herleiten** (pro Datei):
   - `neu anlegen` — der Pfad taucht in keiner früheren Session auf (Erstauftritt).
   - `ergänzen` — der Pfad kam bereits vor und diese Session fügt hinzu.
   - `ersetzen` — nur, wenn die Doku dieser Session Bestehendes ausdrücklich austauscht.

5. **Scope herleiten** (bei `ergänzen`):
   - `.java`: die in dieser Session neu hinzukommenden Methoden/Member — als Differenz
     aus dem, was frühere Sessions bereits abgedeckt haben, und dem Inhalt der finalen
     Datei.
   - Library: die betroffenen Dependency-Koordinaten.
   - Konfiguration: die betroffenen Schlüssel.

6. **Steps-Sektion im Hausformat zusammenstellen.** Einträge in der Form
   `- <Pfad> — <Operation> — <Scope optional>`, gruppiert unter den `###`-Überschriften
   der Sektionen aus CLAUDE.md (Library, Konfiguration, Implementierung, Skript, Test) in
   der dort definierten Reihenfolge; leere Sektionen weglassen. Pfade relativ zur
   Projektwurzel, identisch in finaler Implementierung und Skeleton.

7. **Anzeigen und bestätigen lassen.**
   - Existiert noch **keine** `## Steps`-Sektion: die vorgeschlagene Sektion zeigen.
   - Existiert **bereits** eine: einen **Diff** gegen die aktuelle Fassung zeigen und
     manuelle Anpassungen nicht stillschweigend überschreiben.
   - Erst **nach meiner Bestätigung** die `## Steps`-Sektion in das Session-Dokument
     schreiben (am Ende des Dokuments einfügen bzw. eine vorhandene ersetzen).
   - Die finale Implementierung wird dabei nie verändert.

8. **Zusammenfassen:** je Session — erzeugt / geändert / unverändert, plus alle
   offenen `TODO`-Hinweise, die Du manuell prüfen solltest.
