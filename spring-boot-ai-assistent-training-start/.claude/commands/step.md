---
description: Spielt den Code eines Kursschritts vollständig aus der finalen Implementierung ins Skeleton ein.
argument-hint: <SessionID> [Sektion: voller Name oder Kürzel l|c|i|s|t]
allowed-tools: Read, Edit, Write, Glob, Grep, Bash(mvn *), Bash(curl *)
---

Argumente: $ARGUMENTS
(Erstes Token = Session-ID, z. B. `2.3`. Optionales zweites Token = Sektion — voller
Name oder Kürzel gemäß der Sektions-Tabelle in CLAUDE.md, z. B. `Konfiguration` oder `c`.)

Führe den Kursschritt gemäß den Grundregeln in CLAUDE.md aus:

1. **Session finden.** Öffne in der Dokumentation das Session-Dokument mit der
   angegebenen ID und lies dessen Abschnitt `## Steps`.
2. **Sektion wählen.** Ist ein zweites Token angegeben, löse es über die Sektions-Tabelle
   in CLAUDE.md auf (voller Name oder Kürzel) und verarbeite ausschließlich die passende
   Untersektion. Sonst alle in der in CLAUDE.md definierten Reihenfolge.
3. **Je Artefakt einspielen.** Für jeden Eintrag der Form
   `- <Pfad> — <Operation> — <Scope optional>`:
   - Lies die **vollständige** Fassung aus der **finalen Implementierung** am
     angegebenen Pfad. Ignoriere Code-Snippets aus dem Fließtext der Doku.
   - Wende sie im **Skeleton** am **selben relativen Pfad** an, gemäß Operation
     (`neu anlegen` | `ergänzen` | `ersetzen`) und ggf. `Scope`.
   - Schreibe niemals in die finale Implementierung.
4. **Idempotenz.** Vor `ergänzen` prüfen, ob der Inhalt bereits vorhanden ist, um
   Dopplungen zu vermeiden.
5. **Test.** In der Sektion Test die Testanweisung ausgeben. Nur ausführen, wenn ich
   ausdrücklich darum bitte.
6. **Rückmeldung.** Abschließend knapp auflisten: geänderte Dateien + Operation.
   Keine langen Erklärungen.
