# Die Spezifikation

*Begriff, Aufbau, Eigenschaften und Anforderungen*

---

## 1 Etymologie

Der Begriff *Spezifikation* leitet sich vom spätlateinischen *specificare* ab, das sich
aus *species* (Art, Gestalt, Erscheinung) und *facere* (machen, tun) zusammensetzt und so
viel bedeutet wie „etwas seiner Art nach genau bestimmen". Das Wort *species* wiederum geht
auf *specere* (sehen, betrachten) zurück, wodurch der Begriff etymologisch mit Ausdrücken
wie Aspekt, Perspektive oder Inspektion verwandt ist. Bereits in dieser Wortwurzel ist die
heutige Bedeutung angelegt: Eine Spezifikation macht die besonderen Eigenschaften — die
„Art" — einer Sache sichtbar und eindeutig bestimmbar.

## 2 Motivation

Eine Spezifikation verfolgt das Ziel, ein Artefakt so genau wie möglich zu beschreiben.
Charakteristisch ist ihre Zukunftsgerichtetheit: Sie entsteht, bevor das beschriebene
Artefakt existiert, und dient als verbindliche Vorgabe für dessen spätere Erstellung. Im
Zentrum steht dabei stets das „Was" — also die geforderten Eigenschaften und das erwartete
Verhalten —, nicht hingegen das „Wie", das heißt der konkrete Weg der technischen
Umsetzung. Diese bewusste Trennung eröffnet einen Gestaltungsspielraum: Ein und dieselbe
Spezifikation kann auf unterschiedliche Arten realisiert werden, solange die geforderten
Eigenschaften erfüllt sind.

## 3 Aufbau und Inhalt

Eine Spezifikation gliedert sich sinnvollerweise in klar voneinander abgegrenzte
Bestandteile. Die folgende Tabelle führt die inhaltlichen Kernbestandteile auf; formale
Elemente wie Glossar und Metadaten (Version, Autor, Status) treten als Rahmen des Dokuments
hinzu.

| Bestandteil | Beschreibung |
|---|---|
| Ziel | Zweck der Spezifikation; welches Problem gelöst bzw. welcher Nutzen erreicht werden soll. |
| Gegenstand | Das zu beschreibende Artefakt selbst (Produkt, System oder Leistung). |
| Geltungsbereich | Abgrenzung, was Teil der Spezifikation ist und was ausdrücklich nicht. |
| Restriktionen | Rahmenbedingungen und Einschränkungen (rechtlich, technisch, organisatorisch), die als gegeben vorausgesetzt werden. |
| Anforderungen | Die eigentlichen, überprüfbaren Forderungen an das Artefakt — der Kern der Spezifikation. |
| Abnahme | Kriterien und Verfahren, anhand derer die Erfüllung objektiv festgestellt wird. |

## 4 Eigenschaften

Damit eine Spezifikation ihren Zweck erfüllt, sollte sie die folgenden sechs wesentlichen
Eigenschaften aufweisen.

| Eigenschaft | Beschreibung |
|---|---|
| Eindeutigkeit | Jede Aussage lässt nur eine Interpretation zu; vage Begriffe werden vermieden oder definiert. |
| Vollständigkeit | Alle relevanten Anforderungen sind erfasst; es bestehen keine offenen Lücken oder ungeklärten Randfälle. |
| Überprüfbarkeit | Für jede Anforderung lässt sich objektiv — in der Regel messbar — feststellen, ob sie erfüllt ist. |
| Widerspruchsfreiheit | Die Anforderungen stehen nicht im Konflikt zueinander und schließen sich nicht gegenseitig aus. |
| Nachvollziehbarkeit | Anforderungen sind eindeutig identifizierbar und auf Quelle bzw. Ziel zurückführbar (Traceability). |
| Umsetzungsneutralität | Beschrieben wird das „Was", nicht das „Wie"; verschiedene Lösungswege bleiben möglich. |

## 5 Anforderungen

Anforderungen bilden das Herzstück der Spezifikation. Sie lassen sich nach ihrer Natur in
verschiedene Arten unterteilen.

| Art | Beschreibung | Beispiel |
|---|---|---|
| Organisatorisch | Vorgaben zu Prozess, Rollen, Terminen, Budget, Dokumentation sowie rechtlicher und normativer Konformität. | Einhaltung von Meilensteinen; DSGVO-Konformität. |
| Funktional | Was das Artefakt leisten bzw. tun soll (Funktionen, Verhalten, Ein- und Ausgaben). | „Das System muss Benutzer authentifizieren." |
| Nicht-funktional (Qualität) | Qualitätseigenschaften wie Leistung, Sicherheit, Zuverlässigkeit, Bedienbarkeit oder Wartbarkeit. | „Antwortzeit unter 200 ms bei 1000 parallelen Zugriffen." |
| Weitere (Schnittstellen, Randbedingungen) | Technische Vorgaben, Schnittstellen zu Nachbarsystemen sowie Umgebungsbedingungen. | „Datenaustausch über REST-Schnittstelle im JSON-Format." |

Unabhängig von ihrer Art muss eine einzelne Anforderung mindestens die folgenden Merkmale
besitzen, um verwendbar und prüfbar zu sein.

| Merkmal | Bedeutung |
|---|---|
| Eindeutige Kennung (ID) | Ermöglicht die Referenzierung und die Nachverfolgbarkeit der Anforderung. |
| Eindeutige Formulierung | Lässt nur eine Lesart zu und ist frei von Mehrdeutigkeiten. |
| Verbindlichkeitsgrad | Klarer Modalausdruck (muss / soll / kann) legt die Verpflichtung fest. |
| Überprüfbarkeit | Zugehöriges, objektiv prüfbares Abnahme- bzw. Akzeptanzkriterium. |
| Priorität | Relative Wichtigkeit als Grundlage für Umsetzung und Priorisierung. |
| Quelle / Begründung | Herkunft und Rechtfertigung der Anforderung (Rückverfolgbarkeit). |
