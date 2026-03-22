# Spring Boot AI Introduction Training

Diese Schulung vermittelt die Grundlagen, um mit **Spring AI** unter **Spring Boot** eigene AI-Assistenten zu entwickeln. An einem praxisnahen Use Case aus dem Insurance-Umfeld erarbeiten wir Schritt für Schritt alle notwendigen Bausteine – von der einfachen Chat-Integration bis hin zum produktionsreifen Enterprise-Assistenten.

## Inhalte

- [Vorstellung](#vorstellung)
- [Ziel](#ziel)
- [Agenda](#agenda)
- [Setup](#setup)
- [Use Case](#use-case)


# Vorstellung

Mein Name ist **Michael Schäfer**. Ich bin Autor, Trainer und Java-Entwickler mit über 20 Jahren Spring- und 10 Jahren Spring Boot-Erfahrung – mein Herz schlägt für das Spring-Ökosystem. Meine erste Begegnung mit **GenAI** hatte ich im Dezember 2022, als mich ein Schulungsteilnehmer mit einer besseren Lösung überraschte, die er von ChatGPT erhalten hatte – kurz nach dessen Veröffentlichung im November 2022. Das hat mich nachhaltig beeindruckt und grundsätzliche Fragen aufgeworfen. 2024 habe ich das Thema wissenschaftlich aufgegriffen und Vorträge zu GenAI im Software Engineering gehalten. 2025 habe ich mein Engagement erhöht und mich der Entwicklung von Anwendungen mit GenAI gewidmet. Seit Januar 2025 beschäftige ich mich intensiv mit **Spring AI** und habe meine Erfahrungen in dem Buch *AI-Assistenten mit Spring Boot entwickeln* zusammengefasst, das gleichzeitig das Begleitmaterial zu dieser Schulung ist.

---

Jetzt sind Sie dran! Bitte stellen Sie sich kurz vor. Für uns wichtig sind:

- **Ihre Rolle:** In welcher Position arbeiten Sie?
- **Ihre Motivation:** Was hat Sie zu dieser Schulung geführt?
- **Ihre Ziele:** Was möchten Sie konkret mitnehmen?
- **Besondere Fragen:** Gibt es spezifische Themen oder Herausforderungen, die Sie beschäftigen?


# Ziel

Ziel dieser Schulung ist es, die Grundlagen zu vermitteln, die Sie benötigen, um mit **Spring AI** unter **Spring Boot** eigene AI-Assistenten zu entwickeln. Am Ende sind Sie in der Lage, einen praxistauglichen AI-Assistenten selbstständig zu implementieren.

## AI-Assistenten und AI-Agenten

Für das gemeinsame Verständnis ist eine Unterscheidung wichtig: **AI-Assistenten** unterstützen Menschen dabei, ihre Ziele zu erreichen, indem sie Teilaufgaben übernehmen. Sie arbeiten **reaktiv** – sie warten auf konkrete Aufträge des Nutzers und führen diese aus. Der Mensch behält dabei stets die Kontrolle und trifft die wichtigen Entscheidungen. Ein **AI-Agent** hingegen erhält ein übergeordnetes Ziel und handelt autonom: Er analysiert das Ziel, entwickelt selbstständig einen Plan und führt die notwendigen Schritte durch – die Kontrolle liegt beim Agenten, nicht beim Menschen.

> **Wichtig:** In dieser Schulung entwickeln wir **AI-Assistenten** – der AI-Assistent bildet dabei die Grundlage, auf der ein AI-Agent aufbaut.

```
+---------------------------+
|        AI Agent           |
|  (autonom, zielorientiert)|
+---------------------------+
|      AI Assistent         |
| (reaktiv, nutzergesteuert)|
+---------------------------+
```

> **Hinweis:** Die weiterführende Schulung **Spring AI – Entwicklung von AI Agenten unter Spring** baut auf den hier vermittelten Grundlagen auf und behandelt die Implementierung autonomer AI-Agenten.


# Agenda

Die Schulung gliedert sich in vier Kapitel:

- **Introduction** – Einstieg und Orientierung
- **Foundation** – Grundlegende Bausteine eines AI-Assistenten
- **Enterprise** – Erweiterungen für den Unternehmenseinsatz
- **Advanced** – Fortgeschrittene Themen

---

**Introduction** ist das aktuelle Kapitel. Es dient der gegenseitigen Vorstellung, der Klärung der Schulungsziele und der Einführung in die Agenda. Außerdem wird das Setup der Entwicklungsumgebung durchgeführt und der Use Case vorgestellt, der uns durch die gesamte Schulung begleitet.

**Foundation** vermittelt die grundlegenden Bausteine, die für die Entwicklung eines AI-Assistenten mit Spring AI benötigt werden. Im Mittelpunkt stehen das **Chat Model**, der **Chat Client** sowie die Integration in eine Spring Boot Web-Anwendung. Am Ende dieses Kapitels steht ein lauffähiger AI-Assistent.

**Enterprise** erweitert den AI-Assistenten um Bausteine, die im Enterprise-Umfeld in der Regel benötigt werden. Behandelt werden **Prompts**, **Tools** (Funktionsaufrufe), **RAG** (Retrieval Augmented Generation) sowie die Qualitätssicherung durch **Tests**. Diese Themen sind entscheidend für den produktiven und qualitativ hochwertigen Einsatz eines AI-Assistenten.

**Advanced** zeigt weiterführende Bausteine wie **Compliance**, **Monitoring** und das **Model Context Protocol (MCP)**. Diese Themen gehen über den Kern eines AI-Assistenten hinaus, bieten jedoch wichtige Einstiegspunkte für komplexere Szenarien und zukünftige Entwicklungen.


# Setup

Für die Schulung wird folgender Technologie-Stack verwendet:

| Technologie | Version | Beschreibung |
|---|---|---|
| **Java** | 21 (Adoptium) | Laufzeitumgebung für alle Beispiele |
| **Maven** | 3.9.9 | Build- und Dependency-Management |
| **Spring Boot** | 3.5.x | Basis-Framework für die Anwendungsentwicklung |
| **Spring AI** | 1.1.4 | Spring-Integration für AI-Modelle und -Komponenten |
| **Ollama** | 0.17.0 | Lokale Bereitstellung von Open Source AI-Modellen |
| **LLaMA** | 3.3 | Open Source Sprachmodell von Meta (via Ollama) |
| **VS Code** | 1.x.x | Empfohlene IDE |

Spring AI bringt erst Ende Mai die Version 2.0.0 heraus, deren Grundlage Spring 4.x.x ist. Daher wird aktuell noch mit den oben aufgeführten Versionen gearbeitet. 

Für **Visual Studio Code** werden folgende Extensions empfohlen:

- **Extension Pack for Java** von Microsoft (Java, Debug, Test, Code Assistant)
- **Spring Boot Extension Pack** von VMware (Initializer, Dashboard, Beans)

> **Hinweis:** Die Beispiele wurden auf macOS entwickelt und getestet, sind jedoch auf allen gängigen Betriebssystemen (Windows, Linux, macOS) lauffähig.


# Use Case

```
  +---------------------+       +----------------------------------------------+
  |                     |       |  << System >>  Insurance AI-Assistent         |
  |          o          |       |                                                |
  |         /|\         |------>|   (  Frage stellen                        )   |
  |          |          |       |                                                |
  |         / \         |------>|   (  Produktinformation abrufen           )   |
  |                     |       |                                                |
  |  Versicherungs-     |------>|   (  Kundeninformation abrufen            )   |
  |  makler             |       |                                                |
  |                     |<------|   (  Antwort erhalten                     )   |
  +---------------------+       +----------------------------------------------+
```

Der Use Case, der uns durch diese Schulung begleitet, kommt aus dem Bereich **Insurance**. Im europäischen Versicherungsmarkt gibt es zunehmend unabhängige **Versicherungsmakler**, die nicht an eine einzelne Versicherung gebunden sind und ihre Kunden zu Produkten verschiedener Anbieter beraten. Da Makler häufig spezifisches Produktwissen benötigen, das sie nicht immer sofort verfügbar haben, müssen sie bisher direkt bei der Versicherung nachfragen – das kostet Zeit und Geld.

Ziel ist es daher, einen **Insurance AI-Assistenten** zu entwickeln, der Maklern auf Knopfdruck präzise Antworten zu Versicherungsprodukten und Kundeninformationen liefert. Dieser AI-Assistent ist das durchgehende Praxisbeispiel der Schulung und wird Kapitel für Kapitel erweitert, bis ein vollständiger, produktionsnaher AI-Assistent entstanden ist.
