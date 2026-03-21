Michael Schäfer

  ------------------------------------------------------------------------------
  Autor:         Michael Schäfer,
                 [book@spring-for-graphql.com](mailto:book@spring-graphql.com)
  -------------- ---------------------------------------------------------------
  Lektorat:      xxx

  Copyright:     © 2025 Michael Schäfer

  Lizenz:        CC BY-ND 4.0
                 [https://creativecommons.org](https://creativecommons.org/)

  Verlag:        Michael Schäfer, Im Wiesenhof 16, 35108 Allendorf (Eder)

                 

  Version:       xxx

                 

                 
  ------------------------------------------------------------------------------

# Inhaltsverzeichnis {#inhaltsverzeichnis .TOC-Heading}

[1 Allgemeines [9](#allgemeines)](#allgemeines)

[1.1 Inhalt [9](#inhalt)](#inhalt)

[1.2 Beispiel [10](#beispiel)](#beispiel)

[1.3 Source Code [10](#source-code)](#source-code)

[1.4 Entwicklungsumgebung
[11](#entwicklungsumgebung)](#entwicklungsumgebung)

[2 AI Grundlagen [12](#ai-grundlagen)](#ai-grundlagen)

[2.1 Artificial Intelligence
[12](#artificial-intelligence)](#artificial-intelligence)

[2.1.1 Symbolische Intelligenz
[12](#symbolische-intelligenz)](#symbolische-intelligenz)

[2.1.2 Konnektionistische Intelligenz
[12](#konnektionistische-intelligenz)](#konnektionistische-intelligenz)

[2.2 Modelle [13](#modelle)](#modelle)

[2.2.1 Modalität [13](#modalität)](#modalität)

[2.2.2 Ressourcen [14](#ressourcen)](#ressourcen)

[2.2.3 Betrieb [14](#betrieb)](#betrieb)

[2.2.4 Compliance [15](#compliance)](#compliance)

[2.2.5 Qualität [15](#qualität)](#qualität)

[2.2.6 Open vs. Closed [15](#open-vs.-closed)](#open-vs.-closed)

[2.3 Entwicklung [16](#entwicklung)](#entwicklung)

[2.4 Anbieter [17](#anbieter)](#anbieter)

[2.4.1 1. Commercial Provider API
[17](#commercial-provider-api)](#commercial-provider-api)

[2.4.2 2. Commercial Cloud Provider API
[17](#commercial-cloud-provider-api)](#commercial-cloud-provider-api)

[2.4.3 3. Commercial Open Source Provider API
[18](#commercial-open-source-provider-api)](#commercial-open-source-provider-api)

[2.4.4 4. Local Open Source APIs
[18](#local-open-source-apis)](#local-open-source-apis)

[2.4.5 Auswahl des richtigen Betriebsmodells
[18](#auswahl-des-richtigen-betriebsmodells)](#auswahl-des-richtigen-betriebsmodells)

[2.5 Anpassung [19](#anpassung)](#anpassung)

[2.5.1 1. Prompt Engineering
[19](#prompt-engineering)](#prompt-engineering)

[2.5.2 2. Retrieval Augmented Generation (RAG)
[20](#retrieval-augmented-generation-rag)](#retrieval-augmented-generation-rag)

[2.5.3 3. Fine-Tuning [20](#fine-tuning)](#fine-tuning)

[2.5.4 Auswahl der richtigen Methode
[21](#auswahl-der-richtigen-methode)](#auswahl-der-richtigen-methode)

[2.5.5 Tools [21](#tools)](#tools)

[2.5.6 Model Context Protocol
[22](#model-context-protocol)](#model-context-protocol)

[2.6 Anwendungen [22](#anwendungen)](#anwendungen)

[2.6.1 AI-Assistenten [23](#ai-assistenten)](#ai-assistenten)

[2.6.2 AI-Agents [23](#ai-agents)](#ai-agents)

[3 AI Spring [25](#ai-spring)](#ai-spring)

[3.1 Spring Boot [25](#spring-boot)](#spring-boot)

[3.1.1 Spring Boot Starter
[25](#spring-boot-starter)](#spring-boot-starter)

[3.2 Spring AI [26](#spring-ai)](#spring-ai)

[3.2.1 Spring AI Features
[26](#spring-ai-features)](#spring-ai-features)

[3.2.2 Spring AI Maven-Konfiguration
[27](#spring-ai-maven-konfiguration)](#spring-ai-maven-konfiguration)

[3.2.3 Konfiguration [27](#konfiguration)](#konfiguration)

[4 AI-Assistent [28](#ai-assistent)](#ai-assistent)

[4.1 Model [28](#model)](#model)

[4.1.1 1. Generic Model API
[28](#generic-model-api)](#generic-model-api)

[4.1.2 2. Content Model API
[28](#content-model-api)](#content-model-api)

[4.1.3 3. Provider Model API
[29](#provider-model-api)](#provider-model-api)

[4.2 Provider [29](#provider)](#provider)

[4.2.1 Feature-Unterstützung [30](#_Toc218936579)](#_Toc218936579)

[4.3 Chat Model [31](#chat-model)](#chat-model)

[4.3.1 1. API [31](#api)](#api)

[4.3.2 2. Options [31](#options)](#options)

[4.3.3 3. Request [32](#request)](#request)

[4.3.4 4. Response [34](#response)](#response)

[4.4 Chat Client [37](#chat-client)](#chat-client)

[4.4.1 Builder [37](#builder)](#builder)

[4.5 Chat Client - API Kapitel [39](#chat-client-1)](#chat-client-1)

[4.5.1 API [39](#api-1)](#api-1)

[4.5.2 Synchrone Aufrufe [39](#synchrone-aufrufe)](#synchrone-aufrufe)

[4.5.3 Asynchrone Aufrufe
[41](#asynchrone-aufrufe)](#asynchrone-aufrufe)

[4.5.4 Aufrufspezifische Parameter
[41](#aufrufspezifische-parameter)](#aufrufspezifische-parameter)

[4.5.5 Fehlerbehandlung [43](#fehlerbehandlung)](#fehlerbehandlung)

[5 AI Enterprise Assistent
[44](#ai-enterprise-assistent)](#ai-enterprise-assistent)

[5.1 RAG (Retrieval Augmented Generation)
[44](#rag-retrieval-augmented-generation)](#rag-retrieval-augmented-generation)

[5.1.1 Motivation [44](#motivation)](#motivation)

[5.1.2 Das Problem ohne RAG
[44](#das-problem-ohne-rag)](#das-problem-ohne-rag)

[5.1.3 Die Herausforderung im Enterprise-Umfeld
[45](#die-herausforderung-im-enterprise-umfeld)](#die-herausforderung-im-enterprise-umfeld)

[5.1.4 Die Lösung: RAG [45](#die-lösung-rag)](#die-lösung-rag)

[5.1.5 Architektur [46](#architektur)](#architektur)

[5.1.6 Advisor [46](#advisor)](#advisor)

[5.1.7 Quellen [46](#quellen)](#quellen)

[5.1.8 Module [57](#module)](#module)

[5.2 Tools [67](#tools-1)](#tools-1)

[5.2.1 Motivation [67](#motivation-1)](#motivation-1)

[5.2.2 Implementierung [68](#implementierung)](#implementierung)

[5.2.3 Tool Context [73](#tool-context)](#tool-context)

[5.2.4 Tool Exception Handling
[74](#tool-exception-handling)](#tool-exception-handling)

[5.2.5 Tool Execution [76](#tool-execution)](#tool-execution)

[5.3 Qualität [76](#qualität-1)](#qualität-1)

[5.3.1 Test [77](#test)](#test)

[5.3.2 Evaluator [78](#evaluator)](#evaluator)

[5.4 Compliance [81](#compliance-1)](#compliance-1)

[5.5 Monitoring [81](#monitoring)](#monitoring)

[5.5.1 Logging [82](#logging)](#logging)

[5.5.2 Metriken [83](#metriken)](#metriken)

[5.5.3 Tracing [85](#tracing)](#tracing)

[6 AI Advanced Assistent
[87](#ai-advanced-assistent)](#ai-advanced-assistent)

[6.1 MCP [87](#mcp)](#mcp)

[6.1.1 Server [88](#server)](#server)

[6.1.2 Client [90](#client)](#client)

[6.2 Models [91](#models)](#models)

[6.2.1 Image Model [92](#image-model)](#image-model)

[6.2.2 Voice Model [92](#voice-model)](#voice-model)

[7 Literaturverzeichnis
[94](#literaturverzeichnis-1)](#literaturverzeichnis-1)

# 

Anliegen

Für mein Buch habe ich bewusst den Titel **AI-Assistent** gewählt. Ich
hätte mein Buch auch \"AI Agents mit Spring AI\" nennen können. Das wäre
allerdings falsch gewesen, denn weder entspricht mein Use Case einem AI
Agent, noch ist Spring AI ein AI Agent Framework.

In den aktuellen Medien wird das allerdings gerne so dargestellt: Alles
wird als AI Agent und als AI Agentic bezeichnet, obwohl es den
Eigenschaften eines AI Agent nicht entspricht. Oft handelt es sich dabei
um AI-Assistenten.

**AI-Assistenten** unterstützen uns dabei, Ziele zu erreichen, indem sie
kleinere Aufgaben übernehmen -- wie das Erstellen von Bildern, das
Zusammenfassen von Texten oder das Versenden einer E-Mail. Allerdings
sind wir Menschen, also die Anwender, noch diejenigen, die ein Ziel
verfolgen und die Kontrolle haben.

Bei einem **AI Agent** ist es anders: Der AI Agent erhält das Ziel vom
Menschen und bestimmt selbst, welche Teilaufgaben er zu erledigen hat.
Der AI Agent hat die Kontrolle, nicht der Mensch. Der Mensch kann
gegebenenfalls noch eingreifen, unterbrechen oder bestätigen, sofern
notwendig. Ziel ist es, dass der AI Agent sein Ziel vollständig alleine
umsetzt.

Da wir in diesem Buch keine AI Agents implementieren, sondern
AI-Assistenten, habe ich das Buch auch so genannt -- fein unterschieden,
auch wenn ich dadurch das eine oder andere Buch weniger verkaufe. Aber
diese Unterscheidung ist mir wichtig.

Vorwort

Generative AI, im Folgenden **GenAI** genannt, ist ein neuer AI-Trend.
Meine erste Erfahrung mit GenAI habe ich bei einer Schulung gemacht, die
ich selbst gehalten habe. Dort sprach mich ein Teilnehmer an, nachdem
ich für Spring Boot eine Lösung für ein Problem erklärt hatte. Er
meinte, dass er eine bessere Lösung hätte. Ich fragte ihn, woher er
diese Lösung habe. Er sagte mir, dass es einen neuen Chat gäbe -- es war
die erste Beta-Version von ChatGPT. Das war im Dezember 2022, kurz
nachdem ChatGPT im November 2022 veröffentlicht wurde.

Das hat mich damals sehr beeindruckt und bei mir einige grundsätzliche
Fragen aufgeworfen: Brauchen wir in Zukunft überhaupt noch Trainer? Ich
habe mich dem Thema GenAI zunächst nicht verschrieben, da ich zu diesem
Zeitpunkt andere Themen hatte. Als allerdings 2023, ein halbes Jahr nach
der Veröffentlichung von ChatGPT, das neue Projekt **Spring AI** im
Spring-Ökosystem aufgesetzt wurde und das Thema GenAI Einzug in meine
tägliche Arbeit nahm, habe ich mich 2024 entschlossen, das Thema GenAI
anzugehen.

2024 habe ich grundsätzlich und wissenschaftlich an dem Thema gearbeitet
und einige Vorträge zu GenAI gehalten -- mit einem starken Fokus auf den
Einsatz von GenAI im Software Engineering. 2025 habe ich mich
entschlossen, mein Engagement zu erhöhen und mich vor allem der
Entwicklung von Anwendungen mit GenAI zu widmen. Da ich in Java
unterwegs bin und schon 20 Jahre Spring- und 10 Jahre Spring
Boot-Erfahrung habe und mein Herz für das Spring-Ökosystem schlägt, habe
ich mir als Ziel gesetzt, mich in Spring AI einzuarbeiten. Seit Januar
2025 beschäftige ich mich intensiv mit Spring AI.

Meine Erfahrungen, die ich beim Studium und Einsatz von Spring AI
gesammelt habe, habe ich in diesem Buch zusammengefasst. Das Buch ist
Begleitmaterial zu meiner Schulung \"AI-Assistenten mit Spring Boot
entwickeln\".

Die Dokumentation von Spring AI ist umfangreich, allerdings gerade für
Einsteiger nicht immer ganz so einfach zu verstehen. Vor allem ist es
schwierig zu bewerten, was man wirklich in welcher Form davon für die
Entwicklung eines AI-Assistenten braucht.

Das Buch zeigt an einem konkreten Praxisbeispiel aus dem
Insurance-Umfeld, wie ein AI-Assistent mit Spring AI entwickelt werden
kann. Zudem wird Source Code bereitgestellt, in dem Schritt für Schritt
erklärt ist, wie ein AI-Assistent für Insurance entwickelt wird. Dem
Beispiel liegen die dafür benötigten Code-Snippets bei. Das ist aus
meiner Sicht ein durchaus lohnender Mehrwert, um das Buch zu kaufen.

Ich wünsche allen Lesern viel Spaß beim Lesen und hoffe, Euch damit
helfen zu können.

Danksagung

Vielen Dank an meine Kollegen Markus Schnappinger und Lukas, die mir bei
der Entwicklung eines passenden Use Cases für mein Buch mit Fachwissen
und Daten aus dem Insurance-Bereich zur Seite gestanden haben.

Vielen Dank auch an meine Spring Boot Training-Kollegen Michael Kagel
und weitere Reviewer, die mein Buch auf Fehler und Verständlichkeit
geprüft haben.

# Allgemeines

## Inhalt

Dieses Buch ist in fünf Teile gegliedert, die Sie schrittweise durch die
Entwicklung eines AI-Assistenten mit Spring AI führen.

**Teil 1: AI Grundlagen** versucht, einige grundsätzliche Themen rund um
das Thema GenAI zu klären, aber immer mit dem Fokus auf das, was in
diesem Buch, also der Arbeit mit Spring AI, benötigt wird. Hier werden
die fundamentalen Konzepte wie Transformer-Architektur, Modelle und
deren Anpassung behandelt.

**Teil 2: AI Spring** stellt das Spring AI Projekt aus dem
Spring-Ökosystem im Kontext von Spring Boot grundsätzlich vor. Sie
lernen die Architektur und die wichtigsten Komponenten kennen, die für
die Entwicklung von AI-Anwendungen mit Spring Boot notwendig sind.

**Teil 3: AI-Assistenten** zeigt die grundlegenden Bausteine, die für
einen AI-Assistenten benötigt werden und wie diese mit Spring AI unter
Spring Boot implementiert werden. Dazu gehören das Chat Model, der Chat
Client und die Integration in eine Web-Anwendung.

**Teil 4: AI Enterprise AI-Assistent** erweitert den AI-Assistenten um
weitere Bausteine, die im Umfeld von Enterprise-Anwendungen in der Regel
immer benötigt werden. Hier stehen Themen wie RAG (Retrieval Augmented
Generation), Tools, Qualitätssicherung durch Tests, Compliance und
Monitoring im Vordergrund.

**Teil 5: Advanced AI-Assistent** zeigt dann, welche Bausteine eher für
erweiterte AI-Assistenten benötigt werden. Diese Inhalte werden nur
oberflächlich betrachtet und sind nicht Gegenstand der darauf
aufbauenden Schulung. Zudem werden diese Bausteine auch von Spring noch
nicht so gut unterstützt wie die in den vorausgegangenen Teilen
behandelten Komponenten.

## Beispiel

Ich habe ein Beispiel aus dem Bereich **Insurance** gewählt, da ich dort
Kollegen habe, die sich fachlich auskennen und gemeinsam mit mir einen
sinnvollen Use Case entwickeln können.

Es gibt an eine Versicherung gebundene Vertreter, sogenannte
**Versicherungsvertreter**, und an eine Versicherung ungebundene
Vertreter, sogenannte **Versicherungsmakler**. Die EU möchte in Zukunft
Versicherungsmakler stärken. Das wird dazu führen, dass wir immer mehr
Versicherungsmakler im Markt haben werden.

Versicherungsmakler sind nicht an eine Versicherung gebunden. Das
bedeutet, dass sie ihre Kunden zu vielen verschiedenen Versicherungen
beraten müssen. Das erfordert viel spezielles Wissen, das der Makler
nicht immer sofort zur Hand hat. In der Regel muss er dann bei der
Versicherung nachfragen. Das kostet den Makler und vor allem die
Versicherung Zeit und Geld.

Daher wäre es gut, wenn eine Versicherung einen AI-Assistenten
bereitstellen könnte, der auf die Fragen der Makler Antworten geben
kann. Das spart Zeit und Geld und macht den Kunden zufrieden. Wir haben
daher einen **Insurance AI-Assistenten** entwickelt, der genau diese
Aufgabe übernimmt.

Dieser Use Case wird uns durch das Buch begleiten und Ihnen zeigen, wie
Sie Schritt für Schritt einen praxistauglichen AI-Assistenten mit Spring
AI entwickeln können.

## Source Code

Der vollständige Source Code zu diesem Buch ist auf GitHub unter dem
Projekt \[1\] verfügbar.

Mit dem Startpaket können Sie direkt beginnen und die Beispiele selbst
nachvollziehen. Die Source Code Snippets aus der begleitenden Schulung
liegen unter der Datei spring-boot-ai-assistent-training.json. Das
finale Paket beinhaltet die Musterlösungen zu allen Übungen, sodass Sie
Ihre eigene Implementierung jederzeit mit der Lösung vergleichen können.

## Entwicklungsumgebung

Für die Entwicklung und Ausführung der Beispiele in diesem Buch wird
folgende Umgebung verwendet:

- **Betriebssystem:** Die Beispiele wurden auf einem Apple MacBook mit
  macOS Ventura Version 13.7.3 entwickelt und getestet. Die Beispiele
  sollten aber auf allen gängigen Betriebssystemen (Windows, Linux,
  macOS) lauffähig sein.

- **Java:** Wir verwenden Java 21 von Adoptium. Die Installation erfolgt
  über das .pkg-Installationspaket von der Adoptium-Website. Nach der
  Installation können Sie die Java-Version mit /usr/libexec/java_home -v
  21 ermitteln und die Umgebungsvariable JAVA_HOME entsprechend in Ihrer
  .bash_profile oder .zshrc setzen.

- **Build-Management:** Als Build-Tool kommt Maven 3.9.9 zum Einsatz,
  das alle notwendigen Dependencies verwaltet.

- **IDE:** Für die Entwicklung wird Visual Studio Code 1.95 empfohlen
  mit folgenden Extensions:

  - Extension Pack for Java von Microsoft (Java, Debug, Test, Code
    Assistant)

  - Spring Boot Extension Pack von VMware (Initializer, Dashboard,
    Beans)

- **AI Models:** Für die Bereitstellung der AI Models wird Ollama
  verwendet, das es ermöglicht, Open Source Modelle lokal auf dem
  Rechner bereitzustellen.

# AI Grundlagen

## Artificial Intelligence

Künstliche Intelligenz bezeichnet das Teilgebiet der Informatik, das
sich mit der Entwicklung von Systemen befasst, die Aufgaben ausführen
können, die man beim Menschen als intelligent bezeichnen würde. In einem
frühen und einflussreichen Gründungsdokument definierte John McCarthy
(1927--2011) KI 1955 als das Vorhaben, „Maschinen zu konstruieren, die
sich auf eine Art und Weise verhalten, die man bei Menschen als
intelligent bezeichnen würde.\"

Technisch stützt sich KI auf die Leistungsfähigkeit moderner
Computertechnologie. Das grundlegende theoretische Modell des
Digitalcomputers ist die von Alan Turing (1912--1954) entwickelte
Turingmaschine (1936). Sie bildet bis heute die konzeptionelle Basis für
die Ausführung von Berechnungen und Algorithmen, auf denen auch
KI-Systeme aufbauen.

### Symbolische Intelligenz

Die **symbolische Intelligenz** ist ein Ansatz der künstlichen
Intelligenz, der auf expliziten Regeln, formalen Logiken und Algorithmen
basiert. Systeme dieser Art arbeiten deterministisch -- das bedeutet:
Bei gleichen Eingaben liefern sie stets die gleichen Ausgaben. Das
Wissen wird in Form von Symbolen, Regeln oder logischen Sätzen
dargestellt und verarbeitet. Typische Anwendungen der symbolischen KI
sind Expertensysteme, die auf umfangreichen Wissensbasen und
festgelegten Schlussregeln operieren, um Probleme zu lösen oder
Entscheidungen abzuleiten.

### Konnektionistische Intelligenz

Der **konnektionistische Ansatz** hingegen orientiert sich an der
Funktionsweise des menschlichen Gehirns. KI-Systeme werden hier als
künstliche neuronale Netze modelliert, bestehend aus künstlichen
„Neuronen\" und den Verbindungen („Synapsen\") zwischen ihnen. Diese
Systeme sind nicht deterministisch, da sie auf statistischen Verfahren
beruhen und durch Anpassung von Gewichten lernen. Das Lernen erfolgt
anhand von Beispielen, sodass das System Muster, Zusammenhänge oder
Strukturen in Daten selbstständig erkennt. Konnektionistische Modelle
finden ihre wichtigsten Anwendungen in der Mustererkennung, etwa bei
Sprach-, Bild- oder Objekterkennung.

Innerhalb des Konnektivismus lassen sich zwei unterschiedliche Ansätze
künstlicher Intelligenz unterscheiden: **diskriminative** und
**generative** Modelle.

- **Diskriminative AI** wird darauf trainiert, Dinge voneinander zu
  unterscheiden oder zu erkennen. Ein klassisches Beispiel ist die
  Bildklassifikation, bei der das Modell lernt, ob auf einem Bild ein
  Hund oder eine Katze zu sehen ist.

- **Generative AI** ist darauf ausgelegt, neue Inhalte zu erschaffen.
  Sie kann Texte formulieren, Bilder malen oder sogar Videos erzeugen.
  Besonders in den letzten drei Jahren hat die generative KI einen
  enormen Aufschwung erlebt, ausgelöst durch die Veröffentlichung von
  ChatGPT Ende 2022.

## Modelle

Wir sprechen hier von generativen AI-Modellen, die auf der
Transformer-Architektur basieren. Im Folgenden werden die wichtigsten
Aspekte von Modellen angesprochen, die grundsätzlich wichtig sind und
bei der Auswahl von geeigneten Modellen für die eigene AI-Anwendung eine
wichtige Rolle spielen.

### Modalität

Generative AI-Modelle existieren nicht nur für Text, sondern auch für
Sprache, Bild oder Video. Diese Unterscheidung nennt man **Modalität**.
Es gibt multimodale Modelle, die zwei oder mehr Modalitäten abbilden
können. Das Ziel von omnimodalen Modellen ist es, alle Modalitäten
abzubilden.

- **Modelle für Text** sind die am weitesten verbreiteten generativen
  Modelle. Typische Modelle sind GPT von OpenAI, LLaMA von Meta, Gemini
  von Google, Nova von Amazon und Mistral.

- **Modelle für Sprache** ermöglichen die Generierung und Verarbeitung
  von Audioinhalten. Beispiele sind Whisper von OpenAI für
  Transkription.

- **Modelle für Bilder** können aus Textbeschreibungen neue Bilder
  generieren. Bekannte Beispiele sind DALL-E von OpenAI, Stable
  Diffusion und Midjourney.

- **Modelle für Videos** sind die neueste Entwicklung und können bewegte
  Bilder aus Textbeschreibungen erzeugen. Beispiele sind Sora von OpenAI
  und Gen-2 von Runway.

### Ressourcen

Der Verbrauch von Ressourcen wie CPU und Memory ist bei der Auswahl
eines Modells entscheidend. Die Größe der Modelle wird in der Anzahl der
Parameter angegeben. Die Anzahl liegt bei großen Modellen bei einer
zweistelligen Milliardenzahl. Beispielsweise hat LLaMA 3 in seiner
größten Variante über 70 Milliarden Parameter.

Bei Open Source Modellen gibt es allerdings den Trend, die Modelle
möglichst klein zu halten. Der Grund dafür ist der Einsatz in lokalen
Betriebsumgebungen, beispielsweise auf Embedded Devices oder
Smartphones. Es gibt verschiedene Möglichkeiten, Modelle zu verkleinern,
wie Quantisierung oder andere Kompressionstechniken.

Die Wahl der Modellgröße ist immer ein Kompromiss zwischen Qualität und
Ressourcenverbrauch. Kleinere Modelle sind schneller und benötigen
weniger Speicher, liefern aber oft schlechtere Ergebnisse. Größere
Modelle sind qualitativ besser, benötigen aber erheblich mehr
Ressourcen.

### Betrieb

Bei der Auswahl eines Modells stellen sich wichtige Fragen zum Betrieb:

- Wie kann ein Modell betrieben werden?

- Muss das Modell von einem Dritten betrieben werden oder kann ich das
  Modell selbst betreiben?

- Welche SLAs (Service Level Agreements) kann ich für den Betrieb
  sicherstellen?

- Welche Compliance kann ich für den Betrieb einhalten?

- Wie teuer ist der Betrieb des Modells?

Diese Fragen hängen stark vom gewählten Betriebsmodell ab.
Cloud-basierte Modelle wie ChatGPT bieten hohe Verfügbarkeit, aber
weniger Kontrolle und laufende Kosten pro API-Aufruf. Selbst betriebene
Modelle bieten volle Kontrolle und keine laufenden Kosten pro Aufruf,
erfordern aber eigene Infrastruktur und Know-how.

### Compliance

Das Modell muss verschiedene Compliance-Anforderungen einhalten. Auf
europäischer Ebene gelten die Regelungen aus dem **EU AI Act**, darunter
fällt beispielsweise die Transparenzpflicht, die fordert, dass die
Trainingsdaten offengelegt werden müssen. Zudem stellen Unternehmen und
Branchen eigene Regeln auf, die von AI-Modellen berücksichtigt werden
müssen.

Die Einhaltung von Compliance-Vorgaben ist nicht nur eine rechtliche
Notwendigkeit, sondern auch ein wichtiger Vertrauensfaktor für Nutzer
und Kunden.

### Qualität

Die Qualität eines Modells drückt sich in verschiedenen Metriken oder
Scores aus. Beispielsweise gibt es spezifische Metriken für die Qualität
von Übersetzungen wie BLEU-Score oder für die Textgenerierung wie
Perplexity. Für generative AI-Modelle werden kontinuierlich neue
Bewertungsmetriken entwickelt.

Die Qualität des Modells konkurriert oft mit der Größe des Modells. Je
weniger Parameter ein Modell hat, desto schlechter ist in der Regel die
Qualität. Allerdings gibt es durch verbesserte Trainingsmethoden und
Architekturen auch kleinere Modelle, die erstaunlich gute Ergebnisse
liefern.

### Open vs. Closed

Eine fundamentale Entscheidung ist die Wahl zwischen Open Source und
Closed Source Modellen.

- **Open Source Modelle** wie LLaMA, Mistral oder Falcon werden auf
  Plattformen wie HuggingFace veröffentlicht. Diese Modelle können
  angepasst, lokal betrieben und in die eigene Infrastruktur integriert
  werden. Sie bieten maximale Kontrolle und Transparenz.

- **Closed Source Modelle** wie GPT von OpenAI oder Gemini von Google
  sind proprietär. Sie können nur über APIs genutzt werden, und die
  internen Mechanismen bleiben verborgen. Der Vorteil liegt oft in der
  höheren Qualität und dem umfangreichen Support, der Nachteil in der
  Abhängigkeit vom Anbieter und den laufenden Kosten.

## Entwicklung

Die Entwicklung eines Modells ist ein komplexer Prozess. Die
Implementierung der Modelle geschieht in aller Regel in **Python**. Die
Gründe dafür sind vielfältig:

- Python ist leicht erlernbar

- Es gibt viele Bibliotheken im AI-Umfeld

- Große Community im AI-Bereich

- Starker Einsatz in Forschung und Entwicklung

- Umfangreiche Unterstützung von großen Cloud-Anbietern

Für die Implementierung von Modellen werden Deep-Learning-Frameworks
verwendet. Die wichtigsten sind:

- **TensorFlow** von Google

- **PyTorch** von Meta (Facebook)

- **JAX** von Google

Diese Frameworks bilden quasi den Standard für Open Source Modelle. Die
entwickelten Modelle können über Plattformen wie **HuggingFace**
weltweit zur Verfügung gestellt werden. HuggingFace stellt eine
Python-Bibliothek namens **Transformers** zur Verfügung, über die
Modelle hoch- und heruntergeladen werden können.

Über Python und PyTorch können die Modelle auch lokal auf einem Desktop
ausgeführt werden. Dafür ist eine handelsübliche CPU und 16 GB
Arbeitsspeicher bereits ausreichend, um kleinere Modelle zu betreiben.

## Anbieter

Modelle werden über ein API in eine Anwendung eingebunden. Die **Model
Provider (MP)** stellen das API bereit und die **Model Consumer (MC)**
nutzen das API. [Abbildung 1](#_Ref219045630) zeigt verschiedene
Betriebsmodelle für die Bereitstellung der APIs.

![[]{#_Ref219045630 .anchor}Abbildung 1:
Betriebsmodelle](media/image1.png){alt="Ein Bild, das Schwarz, Dunkelheit enthält. KI-generierte Inhalte können fehlerhaft sein."
width="5.402777777777778in" height="3.4166666666666665in"}

### 1. Commercial Provider API

Das erste Betriebsmodell ist die Nutzung kommerzieller Provider APIs.
Dabei werden Modelle von den Model Providern über das Internet als SaaS
(Software as a Service) bereitgestellt. Die Nutzung wird in Rechnung
gestellt, typischerweise pro API-Aufruf oder nach verbrauchten Tokens.

**Beispiele:**

- ChatGPT OpenAI API

- Claude API von Anthropic

- Gemini API von Google

Diese Dienste eignen sich hervorragend für Demos, Prototypen und
kleinere Anwendungen.

### 2. Commercial Cloud Provider API

Das zweite Betriebsmodell nutzt kommerzielle Cloud Provider. Hier werden
Modelle von den Model Providern als Managed Service bereitgestellt. Die
Modelle werden vom Cloud Provider wie AWS, Azure oder Google Cloud
Platform betrieben, dabei kann die Region ausgewählt werden.

**Beispiele:**

- AWS Bedrock

- Azure OpenAI Service

- Google Cloud Vertex AI

Diese Betriebsform eignet sich für den Einsatz in realen Projekten und
Produktionsumgebungen, bei denen Enterprise-Anforderungen erfüllt werden
müssen.

### 3. Commercial Open Source Provider API

Das dritte Betriebsmodell kombiniert Open Source Modelle mit
kommerzieller Bereitstellung. Die Modelle werden als Open Source
Software bereitgestellt. Es wird nur der Betrieb der Open Source Modelle
in Rechnung gestellt, nicht die Modelle selbst.

**Beispiele:**

- HuggingFace Inference API

- NVIDIA NIM

- Perplexity

Diese Dienste eignen sich gut für Tests, Forschung und Prototypen.

### 4. Local Open Source APIs

Das vierte Betriebsmodell ist der lokale Betrieb von Open Source
Modellen. Die Modelle werden als Open Source Software bereitgestellt.
Die Nutzung wird nicht in Rechnung gestellt, da die Modelle auf eigener
Hardware betrieben werden.

**Beispiele:**

- Ollama

- Docker Model Runner

- Python + PyTorch + HuggingFace

Diese Betriebsform eignet sich hervorragend für die Entwicklung auf
lokalen Rechnern, das Testen von Software und für Szenarien, in denen
Daten aus Compliance-Gründen nicht extern verarbeitet werden dürfen.

### Auswahl des richtigen Betriebsmodells

In der Regel wird das Betriebsmodell je nach Phase des Projekts gewählt:

- **Entwicklung:** Local Open Source APIs oder Commercial Open Source
  Provider APIs

- **Produktion:** Commercial Cloud Provider APIs oder Commercial
  Provider APIs

Die Entscheidung hängt von verschiedenen Faktoren ab: Budget,
Compliance-Anforderungen, benötigte SLAs, Datenhoheit, Modellqualität
und technische Expertise im Team.

## Anpassung

Die Modelle besitzen ein sogenanntes **Weltwissen**. Sie werden als
**Foundation Models** bezeichnet, da sie auf sehr großen, allgemeinen
Datensätzen trainiert wurden. Sie besitzen jedoch kein Wissen aus einem
speziellen Kontext, beispielsweise aus einem Unternehmen. Sie besitzen
auch kein aktuelles Wissen, wie etwa das aktuelle Wetter oder die
heutige Uhrzeit.

Diese Formen von Wissen können dem Foundation Model auf drei
verschiedene Arten hinzugefügt werden:

### Prompt Engineering

**Prompt Engineering** bezeichnet die Kunst, Eingaben (Prompts) so zu
formulieren, dass das Modell die gewünschten Ausgaben liefert. Dabei
wird das Prompt direkt um zusätzliche Textinformationen angereichert.

**Vorteile:**

- Keine Programmierung notwendig

- Keine Modelländerung erforderlich

**Techniken:**

- Klare Anweisungen

- Rollenbasierte Prompts

- Few-Shot Learning

- Chain-of-Thought

**Nachteile:**

- Oft schlechtere Qualität

- Begrenzt durch Context Window

### Retrieval Augmented Generation (RAG)

**RAG** ist eine Methode, bei der das Prompt indirekt um zusätzliche
Textinformationen aus Dokumenten oder anderen Datenbeständen
angereichert wird. Das Besondere an RAG ist, dass zunächst relevante
Informationen aus einer Datenquelle abgerufen werden, bevor das Modell
eine Antwort generiert.

**Konzepte:**

- **Embeddings:** Numerische Repräsentationen von Text

- **Vector Databases:** Spezialisierte Datenbanken für Embeddings

- **Ähnlichkeitssuche:** Semantische Suche nach relevanten Dokumenten

**Vorteile:**

- Gute Qualität der Antworten

- Zugriff auf große Dokumentensammlungen

- Modell bleibt unverändert

### Fine-Tuning

**Fine-Tuning** ist die aufwändigste, aber auch potenziell
wirkungsvollste Methode. Dabei wird das Modell selbst verändert, indem
es auf spezifischen Daten nachtrainiert wird.

**Ansätze:**

- **Full Fine-Tuning:** Trainiert alle Parameter neu

- **Feature-Based Fine-Tuning:** Friert einen Teil der Parameter ein

- **Parameter-Efficient Fine-Tuning (PEFT):** Nutzt Techniken wie LoRA

**Vorteile:**

- Höchste Qualität

- Perfekte Anpassung an Domäne

**Nachteile:**

- Hoher Aufwand

- Benötigt qualitativ hochwertige Trainingsdaten

- Ressourcenintensiv

### Auswahl der richtigen Methode

- **Prompt Engineering:** Klein und statische Informationen

- **RAG:** Große Datenmengen, häufige Änderungen

- **Fine-Tuning:** Höchste Qualität erforderlich, ausreichend
  Trainingsdaten

In der Praxis werden oft mehrere Ansätze kombiniert.

### Tools

**Tools** sind Funktionen, die dem Modell zusätzliche Fähigkeiten
verleihen. Sie erweitern das statische Weltwissen des Modells um
dynamische und aktuelle Informationen. Ein Tool kann beispielsweise
aktuelle Wetterdaten, Aktienkurse, Kundendaten aus einer Datenbank oder
den aktuellen Zeitpunkt bereitstellen.

Tools können von der AI-Anwendung bereitgestellt werden und werden an
das Modell übergeben. Das Besondere dabei ist, dass das Modell selbst
entscheidet, wann ein Tool aufgerufen werden soll. Tools werden vom
Modell dann aufgerufen, wenn eine semantische Verbindung zwischen der
Anfrage des Nutzers und dem Zweck des Tools besteht.

### Model Context Protocol

Das **Model Context Protocol (MCP)** ist ein standardisiertes Protokoll,
das von Anthropic entwickelt wurde, um die Interaktion zwischen
AI-Modellen und externen Datenquellen zu vereinheitlichen. MCP definiert
eine klare Schnittstelle für den Austausch von Kontextinformationen,
Funktionen und Ressourcen.

**Architektur:**

- **MCP Server:** Stellt Funktionen, Ressourcen und Prompts zur
  Verfügung

- **MCP Client:** Kann auf diese Daten zugreifen

**Vorteile:**

- Standardisierung

- Zentrale Bereitstellung von Funktionen

- Wiederverwendbarkeit

**Beispiele für MCP Server:**

- GitHub (Repository-Informationen)

- Slack (Kommunikationsdaten)

- Google Drive (Dokumente)

- PostgreSQL (Datenbankzugriff)

## Anwendungen

Generative AI hat in den letzten Jahren zu einer Vielzahl von
Anwendungen geführt:

- **Banken und Finanzen:** Automatisierte Kreditprüfung,
  Betrugserkennung

- **Versicherungen:** Schadenbearbeitung, Beratungsunterstützung für
  Makler

- **Gesundheitswesen:** Diagnoseunterstützung, Medikamentenentwicklung

### AI-Assistenten

In diesem Buch stehen **AI-Assistenten** im Fokus. AI-Assistenten
unterstützen Menschen dabei, ihre Ziele zu erreichen, indem sie
Teilaufgaben übernehmen. Vergleichbar mit einem menschlichen Assistenten
erhält der AI-Assistent konkrete Aufträge vom Nutzer und führt diese
aus.

Der Manager hat dabei das Ziel im Kopf und überträgt dem Assistenten
spezifische Teilaufgaben. Der Mensch behält die Kontrolle und trifft die
wichtigen Entscheidungen.

**Beispiele:**

- Bankwesen: Zusammenfassung von Kontoinformationen

- Versicherungen: Schnelle Information zu Versicherungsprodukten

- Gesundheitswesen: Recherche zu Behandlungsmethoden

Das zentrale Merkmal von AI-Assistenten ist, dass sie **reaktiv**
arbeiten: Sie warten auf Anweisungen des Nutzers und führen diese aus.

### AI-Agents

Im Gegensatz zu AI-Assistenten erhalten **AI-Agents** ein übergeordnetes
Ziel und können dieses autonom durchführen. AI-Agents planen und führen
die notwendigen Teilaufgaben selbstständig durch.

Ein AI-Agent:

- Analysiert das übergebene Ziel

- Entwickelt einen Plan zur Zielerreichung

- Identifiziert die notwendigen Schritte

- Führt diese aus

**Beispiele:**

- Bankwesen: \"Erstelle einen Überblick über die Portfolio-Performance
  der letzten 12 Monate\"

- Versicherungen: \"Prüfe alle offenen Schadensfälle eines Kunden\"

- Gesundheitswesen: \"Recherchiere alle relevanten Studien zu einer
  Behandlungsmethode\"

Die Implementierung von AI-Agents ist wesentlich aufwändiger als die
Implementierung von AI-Assistenten. Daher konzentriert sich dieses Buch
auf AI-Assistenten.

# AI Spring

**Spring AI** ist ein neues Projekt innerhalb des Spring-Ökosystems und
erweitert dieses um Funktionen zur Entwicklung KI-gestützter
Anwendungen. Es baut konzeptionell auf den bewährten Mechanismen von
*Startern*, *Autokonfiguration* und *Properties* auf. Diese
grundlegenden Konzepte werden durch **Spring Boot** bereitgestellt und
bilden die Basis für eine konsistente und standardisierte
Anwendungsentwicklung.

Eine KI-Assistent-Anwendung nutzt Spring Boot für allgemeine
Anforderungen wie die Persistenz von Daten oder die Bereitstellung von
Web-APIs. Für AI-spezifische Funktionalitäten, beispielsweise die
Integration von Sprachmodellen oder Prompt-Verarbeitung, kommt **Spring
AI** zum Einsatz. Dadurch wird eine klare Trennung zwischen allgemeinen
Anwendungsbelangen und AI-spezifischer Logik erreicht.

[Abbildung 1](#_Ref219045630) veranschaulicht den Zusammenhang der
einzelnen Frameworks innerhalb des **Spring Framework**-Ökosystems.

![Abbildung 2: Spring AI im Kontext des
AI-Assistenten](media/image2.png){alt="Ein Bild, das Schwarz, Screenshot, Text enthält. KI-generierte Inhalte können fehlerhaft sein."
width="4.166666666666667in" height="1.9027777777777777in"}

## Spring Boot

**Spring Boot** ist wohl das bekannteste Spring-Projekt aus dem
Spring-Ökosystem. Spring Boot ist heute die Grundlage für die
Entwicklung von modernen Spring-Anwendungen.

## Spring Boot Starter

Spring Boot stellt das Konzept der **Spring Boot Starter** zur
Verfügung. Ein Starter ist im Wesentlichen eine vordefinierte Sammlung
von Dependencies und Konfigurationen für ein bestimmtes technisches
Thema.

**Ein Starter besteht aus:**

1.  Definition der benötigten Bibliotheken mit kompatiblen Versionen

2.  Autoconfiguration, die automatisch Spring Beans erzeugt

3.  Properties, um die erzeugten Spring Beans zu konfigurieren

**Beispiel Maven-Konfiguration:**

    <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>3.5.0</version>
    </parent>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

Das Starter-Konzept von Spring Boot ist so erfolgreich, dass auch Spring
AI dieses Konzept übernommen hat.

## Spring AI

2022 kam ChatGPT heraus und löste einen riesigen Hype aus. 2023 hat das
Spring-Ökosystem darauf reagiert und ein neues Projekt mit dem Namen
**Spring AI** aufgesetzt. Lead des Projekts sind Dr. Mark Pollack und
Christian Tzolov.

Das Projekt lässt sich von bekannten Python-Projekten wie LangChain und
LlamaIndex inspirieren, aber Spring AI ist keine direkte Portierung
dieser Projekte. Stattdessen wurde Spring AI von Grund auf für das Java-
und Spring-Ökosystem entwickelt.

**Alternative:** LangChain4j ist eine Open Source Software, die als
direkte Konkurrenz zu Spring AI gesehen werden kann.

Im Mai 2024 wurde **Spring AI 1.0.0** veröffentlicht und offiziell von
Spring Boot 3.5 unterstützt. Für Q1 2026 ist Spring AI 2.0.0 für Spring
Boot 4.0 geplant.

### Spring AI Features

  -----------------------------------------------------------------------
  Feature             Beschreibung
  ------------------- ---------------------------------------------------
  Models              Unterstützung für verschiedene Modalitäten: Text,
                      Image, Video, Speech und Embedding

  Chat Client         Synchrone (Web) und asynchrone (WebFlux)
                      Schnittstellen

  User and System     Strukturierte Kommunikation mit Modellen
  Prompts             

  Tools               Erweiterung von Modellen mit zusätzlichen
                      Funktionen

  Tool Server &       Implementierung des Model Context Protocol (MCP)
  Client              

  RAG, ETL and Vector Umfassende Unterstützung für RAG mit Vector
  Stores              Databases

  Evaluator           Werkzeuge zur Bewertung und zum Testen der Qualität

  Test                Spezielle Test-Utilities und
                      Testcontainer-Unterstützung

  Observability       Integration in Spring Boot Actuator für Monitoring
  -----------------------------------------------------------------------

### Spring AI Maven-Konfiguration

    <dependencyManagement>
      <dependencies>
        <dependency>
          <groupId>org.springframework.ai</groupId>
          <artifactId>spring-ai-bom</artifactId>
          <version>1.0.1</version>
          <type>pom</type>
          <scope>import</scope>
        </dependency>
      </dependencies>
    </dependencyManagement>

    <dependencies>
      <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-starter-model-ollama</artifactId>
      </dependency>
    </dependencies>

### Konfiguration

    spring:
      ai:
        ollama:
          base-url: http://localhost:11434
          chat:
            model: llama3.2
            options:
              temperature: 0.7

# AI-Assistent

## Model

Spring AI definiert ein allgemeines **Model API**, das für Text-,
Image-, Audio- oder Video-Modelle verwendet werden kann wie [Abbildung
3](#_Ref219047481) zeigt.\
\
![Ein Bild, das Text, Screenshot, Schwarz, Schrift enthält.
KI-generierte Inhalte können fehlerhaft
sein.](media/image3.png){width="5.597222222222222in"
height="2.611111111111111in"}

[]{#_Ref219047481 .anchor}Abbildung 3: Spring AI Model API

### Generic Model API

Die erste Modellebene, das Generic Model API, repräsentiert einen
generischen Zugriff auf ein generatives AI-Model, unabhängig von der
Modalität siehe [Abbildung 4](#_Ref219047803).

**Zentrale Komponenten:**

- Model: Interface für den Zugriff auf ein AI-Model

- ModelOptions: Parameter wie Temperature

- ModelRequest: Informationen für das Modell

- ModelResponse: Ergebnis der Verarbeitung

- ModelResult: Einzelne Ergebnisse

- ModelResponseMetadata und ModelResultMetadata: Metainformationen

![[]{#_Ref219047803 .anchor}Abbildung 4: Generic Model
API](media/image4.png){alt="Ein Bild, das Schwarz, Dunkelheit enthält. KI-generierte Inhalte können fehlerhaft sein."
width="4.055555555555555in" height="1.625in"}

### Content Model API

Auf der zweiten Modellebene wird das Generic Model um ein konkretes
Content Model API erweitert sie [Abbildung 5](#_Ref219048386). Für Chat
Models gibt es:

**Zentrale Komponenten:**

- ChatModel: Interface für Chat Models

- ChatOptions: Chat-spezifische Parameter

- Prompt: Strukturierte Anfrage

- Message: Basisklasse für Nachrichten

- MessageType: USER, SYSTEM, ASSISTANT, TOOL

- ChatResponse: Ergebnis des Chat Models

- Generation: Einzelnes Ergebnis mit AssistantMessage

![[]{#_Ref219048386 .anchor}Abbildung 5: Content Model
API](media/image5.png){alt="Ein Bild, das Screenshot, Schrift, Design enthält. KI-generierte Inhalte können fehlerhaft sein."
width="5.819444444444445in" height="3.0in"}

### Provider Model API

Auf der dritten Ebene gibt es konkrete Implementierungen für spezifische
Model Provider:

- OllamaChatModel für Ollama

- OpenAiChatModel für OpenAI

- AnthropicChatModel für Anthropic

- etc.

Der große Vorteil dieser Architektur ist die Flexibilität: Die Anwendung
kann gegen das Content Model API programmiert werden, ohne sich um
Provider-Details kümmern zu müssen.

## Provider

Spring AI unterstützt eine umfangreiche Liste von Chat Model Providern:

  -------------------------------------------------------------------------
  Provider      Company       Betriebsmodell    Beschreibung
  ------------- ------------- ----------------- ---------------------------
  ChatGPT       OpenAI        Commercial        Führendes Modell,
                              Provider          Cloud-basiert

  Claude        Anthropic     Commercial        Fokus auf Sicherheit
                              Provider          

  Mistral       Mistral AI    Commercial        Europäischer Anbieter
                              Provider          

  Azure OpenAI  Microsoft     Cloud Provider    ChatGPT über Azure

  Vertex AI     Google        Cloud Provider    Gemini über Google Cloud

  Bedrock       Amazon AWS    Cloud Provider    Verschiedene Modelle über
                                                AWS

  LLaMA         Meta          Open Source       Leistungsstarkes Open
                                                Source Modell

  Ollama        Ollama        Local Open Source Lokaler Betrieb

  HuggingFace   HuggingFace   Open Source       Tausende Open Source
                              Provider          Modelle
  -------------------------------------------------------------------------

[]{#_Toc218936579 .anchor}Feature-Unterstützung

  -----------------------------------------------------------------
  Feature                Beschreibung
  ---------------------- ------------------------------------------
  Multimodality          Verarbeitung von Text und anderen
                         Content-Typen

  Tools                  Einbindung von Tools

  Streaming              Asynchroner Zugriff über WebFlux

  Retry                  Neuaufbau der Verbindung bei Ausfall

  Observability          Monitoring-Unterstützung

  JSON Output            Strukturierte Ausgabe

  OpenAI API             Kompatibilität mit OpenAI API
  Compatibility          
  -----------------------------------------------------------------

Für den Insurance AI-Assistenten wurde **LLaMA 3.2** über **Ollama**
gewählt. Dies bietet Vorteile für die Entwicklung:

- Keine API-Kosten

- Volle Kontrolle über Daten

- Offline-Fähigkeit

- Schnelles Experimentieren

## Chat Model

Das **Chat Model** ist ein konkretes Content Model für textbasierte
Modelle (LLMs). Die meisten LLMs wurden um Fähigkeiten zur Interaktion
mit Menschen erweitert durch:

- **Reinforcement Learning from Human Feedback (RLHF)**

- **Supervised Fine-Tuning**

Wenn wir von Chat Models sprechen, meinen wir immer diese erweiterten
Modelle, die für die Interaktion optimiert wurden.

### API

Das Chat Model API besteht aus folgenden Hauptkomponenten:

**ChatModel**: Zentrale Schnittstelle zum Chat Model **ChatOptions**:
Parameter wie Temperature, MaxTokens, TopK **Prompt**: Strukturierte
Anfrage mit Messages **Message**: Basisklasse mit MessageType und
Content **MessageTypes**: USER, SYSTEM, ASSISTANT, TOOL
**ChatResponse**: Ergebnis mit Generation-Objekten **Generation**:
Einzelnes Ergebnis mit AssistantMessage **Metadaten**:
ChatResponseMetadata und ChatGenerationMetadata

### Options

Chat Options repräsentieren die Parameter zur Konfiguration eines
Modells.

**Typische Parameter:**

- **Temperature**: Steuert Zufälligkeit (0.0-1.0)

  - Niedrig (0.2): Deterministisch, vorhersehbar

  - Hoch (0.9): Kreativ, variabel

- **MaxTokens**: Maximale Länge der Antwort

- **TopK**: Anzahl der Token-Kandidaten

**Konfiguration über Properties:**

    spring:
      ai:
        ollama:
          chat:
            model: llama3.2
            options:
              temperature: 0.7
              num-ctx: 2048
              max-tokens: 512

### Request

#### Prompt

Die Kommunikation erfolgt über **Prompts**. Je besser der Prompt, desto
besser die Antwort. Den Aufbau eines effektiven Prompts nennt man
**Prompt Engineering**.

**Einfaches Prompt:**

    Prompt prompt = new Prompt("Welche Lebensversicherungen bietet Ihr Unternehmen an?");

#### User Prompt

User Prompts enthalten die konkreten Anfragen des Benutzers:

    UserMessage userMessage = new UserMessage("Kann die Versicherungsdauer verlängert werden?");
    Prompt prompt = new Prompt(userMessage);

**Mehrere Messages:**

    List<Message> messages = List.of(
      new UserMessage("Mein Kunde ist 45 Jahre alt."),
      new UserMessage("Welche Lebensversicherung empfehlen Sie?")
    );
    Prompt prompt = new Prompt(messages);

#### System Prompt

System Prompts definieren das grundlegende Verhalten des AI-Assistenten:

    @Value("classpath:/systemPrompt.st")
    private Resource systemPromptResource;

    public Prompt createSystemPrompt() {
      String systemText = new String(systemPromptResource.getInputStream().readAllBytes());
      SystemMessage systemMessage = new SystemMessage(systemText);
      return new Prompt(systemMessage);
    }

**Kombination von System und User Prompts:**

    List<Message> messages = List.of(
      new SystemMessage("Du bist ein Experte für Lebensversicherungen."),
      new UserMessage("Welche Vorteile hat eine fondsgebundene Lebensversicherung?")
    );
    Prompt prompt = new Prompt(messages);

#### Assistant Prompt

Assistant Prompts enthalten Antworten des Modells. Sie werden
hauptsächlich für **Conversation History** verwendet:

    List<Message> conversationHistory = List.of(
      new UserMessage("Welche Lebensversicherungen bieten Sie an?"),
      new AssistantMessage("Wir bieten kapitalbildende und fondsgebundene Lebensversicherungen an."),
      new UserMessage("Was sind die Unterschiede zwischen den beiden?")
    );
    Prompt prompt = new Prompt(conversationHistory);

#### Templates

Spring AI verwendet **String Templates** mit der Template Engine
StringTemplate 4 (ST4).

**Platzhalter-Syntax:**

    Guten Tag, {customerName}. Ihre Police {policyNumber} läuft noch {daysRemaining} Tage.

**Drei Template-Typen:**

- PromptTemplate: Erzeugt UserMessage

- SystemPromptTemplate: Erzeugt SystemMessage

- AssistantPromptTemplate: Erzeugt AssistantMessage

**PromptTemplate Beispiel:**

    String templateText = """
      Sie haben noch {remainingQuestions} von 10 Fragen übrig.
      Meine Frage: {question}
      """;

    Map<String, Object> parameters = Map.of(
      "remainingQuestions", 7,
      "question", "Welche Lebensversicherungen bieten Sie für Kunden ab 50 an?"
    );

    PromptTemplate promptTemplate = new PromptTemplate(templateText);
    Prompt prompt = promptTemplate.create(parameters);

**SystemPromptTemplate Beispiel:**

    @Value("classpath:/systemPrompt.st")
    private Resource systemPromptResource;

    public Prompt createSystemPrompt() {
      SystemPromptTemplate systemPromptTemplate = 
        new SystemPromptTemplate(systemPromptResource);
      
      Map<String, Object> parameters = Map.of(
        "maxQuestions", 10,
        "toolCustomerDetails", "get_CustomerDetails",
        "toolProductDetails", "get_ProductDetailsByCustomer"
      );
      
      return systemPromptTemplate.create(parameters);
    }

### Response

#### ChatResponse

Die ChatResponse repräsentiert das Ergebnis eines Chat Model Aufrufs:

**Zugriff auf die Antwort:**

    public String chatService(String input) {
      ChatResponse response = chatClient
        .prompt(new Prompt(new UserMessage(input)))
        .call()
        .chatResponse();
      
      // Zugriff auf die erste Generation
      Generation generation = response.getResult();
      
      // Zugriff auf die AssistantMessage
      AssistantMessage message = generation.getOutput();
      
      // Zugriff auf den Text
      return message.getText();
    }

**Verkürzte Variante:**

    public String chatService(String input) {
      return chatClient
        .prompt(new Prompt(new UserMessage(input)))
        .call()
        .content();
    }

**Metadaten für RAG-Tests:**

    @Test
    public void testRagLifeSecurityConditions() {
      String query = "Kann die vereinbarte Versicherungsdauer verlängert werden?";
      
      ChatResponse response = insuranceChatService.chatServiceWithResponse(query);
      String responseContent = response.getResult().getOutput().getText();
      
      // RAG-Dokumente aus den Metadaten abrufen
      List<Document> responseDocuments = (List<Document>)
        response.getMetadata().get(RetrievalAugmentationAdvisor.DOCUMENT_CONTEXT);
      
      assertNotNull(responseDocuments);
      assertFalse(responseDocuments.isEmpty());
    }

#### Converter

Für strukturierte Ausgaben steht die **Structured Output Converter API**
zur Verfügung:

**Converter-Typen:**

- BeanOutputConverter: Konvertierung in Java Beans

- ListOutputConverter: Konvertierung in Listen

- MapOutputConverter: Konvertierung in Maps

**Beispiel mit entity()-Methode:**

    public record InsuranceProduct(
      String name,
      String type,
      int minAge,
      int maxAge,
      String description
    ) {}

    public InsuranceProduct getProductRecommendation(int customerAge) {
      String prompt = String.format(
        "Empfehle eine passende Lebensversicherung für einen %d-jährigen Kunden. " +
        "Gib die Antwort im JSON-Format zurück.",
        customerAge
      );
      
      return chatClient
        .prompt(prompt)
        .call()
        .entity(InsuranceProduct.class);
    }

**Manuelle Konvertierung:**

    BeanOutputConverter<InsuranceProduct> converter = 
      new BeanOutputConverter<>(InsuranceProduct.class);
    String format = converter.getFormat();

    String promptText = String.format(
      "Empfehle eine passende Lebensversicherung für einen %d-jährigen Kunden. %s",
      customerAge,
      format
    );

    ChatResponse response = chatModel.call(new Prompt(promptText));
    String content = response.getResult().getOutput().getText();
    InsuranceProduct product = converter.convert(content);

## Chat Client

Der **Chat Client** kapselt den Zugriff auf ein Chat Model und stellt
ein modernes Fluent API zur Verfügung.

### Builder

Der Chat Client wird über ChatClient.Builder erstellt:

**Konfigurationsmethoden:**

  ---------------------------------------------------------
  Methode             Beschreibung
  ------------------- -------------------------------------
  defaultSystem()     System Prompt für alle Aufrufe

  defaultUser()       User Prompt als Basis

  defaultOptions()    Chat Options für alle Aufrufe

  defaultAdvisors()   Advisors für
                      Request/Response-Verarbeitung

  defaultTools()      Registrierung von Tools
  ---------------------------------------------------------

**Beispiel-Konfiguration:**

    @Configuration
    public class ChatClientConfiguration {
      
      @Value("classpath:/systemPrompt.st")
      private Resource systemPromptResource;
      
      @Autowired
      private InsuranceCustomerDetailsTool customerDetailsTool;
      
      @Bean
      public ChatClient createInsuranceChatClient(ChatClient.Builder builder) {
        SystemPromptTemplate systemPromptTemplate = 
          new SystemPromptTemplate(systemPromptResource);
        
        Map<String, Object> parameters = Map.of(
          "maxQuestions", 10,
          "toolCustomerDetails", "get_CustomerDetails"
        );
        
        Prompt systemPrompt = systemPromptTemplate.create(parameters);
        
        return builder
          .defaultSystem(systemPrompt.getContents())
          .defaultTools(customerDetailsTool)
          .build();
      }
    }

**Verwendung im Service:**

    @Service
    public class InsuranceChatService {
      
      private final ChatClient chatClient;
      
      public InsuranceChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
      }
      
      public String chatService(String input) {
        return chatClient
          .prompt(new Prompt(new UserMessage(input)))
          .call()
          .content();
      }
    }

[Literaturverzeichnis]{.mark}

[\[1\] GitHub Repository: \[URL wird ergänzt\]]{.mark}

[\[2\] OpenAI - Prompt Engineering Guide:
<https://platform.openai.com/docs/guides/prompt-engineering>]{.mark}

[\[3\] DeepLearning.AI - ChatGPT Prompt Engineering for Developers:
<https://www.deeplearning.ai/short-courses/chatgpt-prompt-engineering-for-developers/>]{.mark}

[\[4\] The Art of System Prompts \[Referenz wird ergänzt\]]{.mark}

[\[5\] GitHub System Prompts Examples \[URL wird ergänzt\]]{.mark}

[\[6\] StringTemplate 4 (ST4) \[Referenz wird ergänzt\]]{.mark}

*Hinweis: Dieses Dokument enthält die ersten drei Teile des Buches. Teil
4 (AI Enterprise AI-Assistent) und Teil 5 (Advanced AI-Assistent) sind
im bereitgestellten PDF nicht vollständig enthalten.*

## Chat Client 

### API

Der Chat Client stellt ein Fluent API zur Verfügung, das eine intuitive
und flexible Interaktion mit dem Chat Model ermöglicht. Das API ist so
gestaltet, dass Methodenaufrufe verkettet werden können, was zu lesbarem
und wartbarem Code führt.

### Synchrone Aufrufe

Über die Methode prompt() kann ein Prompt an das Chat Model übergeben
werden. Ein synchroner Aufruf erfolgt über die Methode call(). Das
folgende Beispiel zeigt die verschiedenen Möglichkeiten, auf das
Ergebnis zuzugreifen:

    // Direkter Zugriff auf den Text
    String content = chatClient
        .prompt(new Prompt(new UserMessage("Welche Lebensversicherungen bieten Sie an?")))
        .call()
        .content();

    // Zugriff auf die komplette ChatResponse
    ChatResponse response = chatClient
        .prompt(new Prompt(new UserMessage("Welche Lebensversicherungen bieten Sie an?")))
        .call()
        .chatResponse();

    // Zugriff mit strukturierter Ausgabe
    InsuranceProduct product = chatClient
        .prompt("Empfehle eine Lebensversicherung für einen 45-jährigen Kunden")
        .call()
        .entity(InsuranceProduct.class);

Die Methode content() führt intern
chatResponse().getResult().getOutput().getText() aus und liefert direkt
den Textinhalt zurück. Dies ist die einfachste und häufigste Form des
Zugriffs.

Die Methode chatResponse() gibt die komplette ChatResponse zurück, die
neben dem Text auch Metadaten enthält. Dies ist nützlich für Tests,
Monitoring oder wenn Zugriff auf zusätzliche Informationen wie
TokenCounts benötigt wird.

Die Methode entity() konvertiert die Modellantwort automatisch in eine
Java Bean. Dies ist praktisch, wenn strukturierte Daten erwartet werden.

Der InsuranceChatService verwendet die content()-Methode für normale
Chat-Anfragen und die chatResponse()-Methode für Tests:

    @Service
    public class InsuranceChatService {
        
        private final ChatClient chatClient;
        
        public InsuranceChatService(ChatClient chatClient) {
            this.chatClient = chatClient;
        }
        
        public String chatService(String input) {
            return chatClient
                .prompt(new Prompt(new UserMessage(input)))
                .call()
                .content();
        }
        
        public ChatResponse chatServiceWithResponse(String input) {
            return chatClient
                .prompt(new Prompt(new UserMessage(input)))
                .call()
                .chatResponse();
        }
    }

### Asynchrone Aufrufe

Ein asynchroner Aufruf auf dem Model erfolgt über die Methode stream().
Diese Methode ist nützlich, wenn die Antwort schrittweise verarbeitet
werden soll, beispielsweise um dem Benutzer bereits während der
Generierung Feedback zu geben.

    Flux<ChatResponse> responseStream = chatClient
        .prompt(new Prompt(new UserMessage("Erkläre die Vorteile einer Lebensversicherung")))
        .stream()
        .chatResponse();

    // Verarbeitung des Streams
    responseStream.subscribe(
        response -> System.out.println(response.getResult().getOutput().getText()),
        error -> System.err.println("Fehler: " + error.getMessage()),
        () -> System.out.println("Stream abgeschlossen")
    );

Die Methode stream() gibt einen Flux\<ChatResponse\> zurück, wenn
chatResponse() aufgerufen wird, oder einen Flux\<String\>, wenn
content() verwendet wird. Ein Flux ist ein reaktiver Stream aus dem
Project Reactor, der Teil von Spring WebFlux ist.

Im Insurance Use Case wird keine asynchrone Kommunikation verwendet, da
die Performance-Anforderungen dies nicht erfordern und synchrone Aufrufe
die Implementierung vereinfachen.

### Aufrufspezifische Parameter

Neben den Default-Einstellungen, die beim Erstellen des Chat Client über
den Builder gesetzt werden, können bei jedem Aufruf zusätzliche
Parameter übergeben werden. Diese überschreiben die Default-Werte für
diesen einen Aufruf.

Die folgende Tabelle zeigt die wichtigsten Methoden für
aufrufspezifische Parameter:

  ---------------------------------------------------------
  Methode         Beschreibung
  --------------- -----------------------------------------
  user()          Fügt eine User Message für diesen Aufruf
                  hinzu

  system()        Fügt eine System Message für diesen
                  Aufruf hinzu

  options()       Setzt Chat Options nur für diesen Aufruf

  advisors()      Fügt Advisors nur für diesen Aufruf hinzu

  tools()         Registriert Tools nur für diesen Aufruf

  toolContext()   Übergibt einen ToolContext für diesen
                  Aufruf
  ---------------------------------------------------------

Das folgende Beispiel zeigt, wie die maximale Anzahl von Tokens für
einen einzelnen Aufruf überschrieben wird:

    public String getShortAnswer(String input) {
        return chatClient
            .prompt(new Prompt(new UserMessage(input)))
            .options(ChatOptions.builder()
                .maxTokens(50)
                .build())
            .call()
            .content();
    }

In diesem Fall wird unabhängig von den Default-Options die Antwort auf
maximal 50 Tokens begrenzt. Dies ist nützlich, wenn für bestimmte
Anfragen bewusst kürzere Antworten gewünscht sind.

Ein weiteres Beispiel zeigt die Kombination von System und User Message:

    public String getExpertAdvice(String topic) {
        return chatClient
            .prompt()
            .system("Du bist ein Experte für " + topic)
            .user("Erkläre die wichtigsten Aspekte in drei Sätzen")
            .call()
            .content();
    }

Hier wird die Methode prompt() ohne Parameter aufgerufen, und
anschließend werden über die Fluent-API-Methoden system() und user() die
Messages hinzugefügt.

### Fehlerbehandlung

Bei synchronen Aufrufen können verschiedene Exceptions auftreten, die
behandelt werden sollten:

    public String chatServiceWithErrorHandling(String input) {
        try {
            return chatClient
                .prompt(new Prompt(new UserMessage(input)))
                .call()
                .content();
        } catch (Exception e) {
            log.error("Fehler beim Chat-Aufruf: {}", e.getMessage());
            return "Es ist ein Fehler aufgetreten. Bitte versuchen Sie es später erneut.";
        }
    }

Typische Fehlerszenarien sind Netzwerkprobleme, Timeouts, überschrittene
Token-Limits oder Authentifizierungsfehler. Eine robuste Implementierung
sollte diese Fälle behandeln und dem Benutzer aussagekräftige
Fehlermeldungen bereitstellen.

Das Fluent API des Chat Client macht die Interaktion mit Chat Models
einfach und intuitiv. Die klare Trennung zwischen Default-Einstellungen
und aufrufspezifischen Parametern ermöglicht flexible Konfigurationen,
während die Verkettung von Methodenaufrufen zu lesbarem Code führt.

## Web Client 

# AI Enterprise Assistent

Einleitung

Der vierte Teil dieses Buches erweitert den AI-Assistenten um Bausteine,
die im Umfeld von Enterprise-Anwendungen unverzichtbar sind. Während die
vorherigen Kapitel die grundlegenden Funktionen eines AI-Assistenten
behandelt haben, konzentriert sich dieser Teil auf Aspekte, die für den
produktiven Einsatz in Unternehmen entscheidend sind.

Enterprise-Anwendungen benötigen zunächst leistungsfähige Mechanismen
zur Informationsbeschaffung: **RAG** (Retrieval Augmented Generation)
ermöglicht den Zugriff auf unternehmensspezifische Kontextdaten, während
**Tools** die Integration von Geschäftslogik und externen Systemen
erlauben. Darüber hinaus stellen Enterprise-Szenarien besondere
Qualitätsanforderungen: Die **Qualität** der AI-Ausgaben muss durch
systematisches **Testen** gewährleistet sein, **Compliance**-Vorgaben
müssen eingehalten werden, und der Betrieb muss durch **Monitoring**
kontinuierlich überwacht werden können. Dieser Teil zeigt, wie Spring AI
diese Anforderungen unterstützt und wie sie im Insurance Use Case
umgesetzt werden.

## RAG (Retrieval Augmented Generation)

### Motivation

**RAG** (Retrieval Augmented Generation) ist eine Methode, um einem User
Prompt zusätzliche Kontextinformationen bereitzustellen und dadurch
kontextabhängige Antworten vom Modell zu erhalten. Im
Versicherungskontext bedeutet dies beispielsweise, dass gezielte
Informationen aus Versicherungspolicies in das Prompt eingefügt werden,
um spezifische Fragen zu Vertragsdetails präzise beantworten zu können.

### Das Problem ohne RAG

Normalerweise wird das User Prompt direkt an das Modell übergeben, und
das Modell generiert eine Antwort basierend auf seinem Trainingswissen.
Möchte man jedoch Kontextinformationen aus der Domäne des Unternehmens
hinzufügen, stößt dieser einfache Ansatz schnell an seine Grenzen.

Grundsätzlich ist es möglich, Unternehmensdaten über die Anreicherung
des User Prompts einzubringen -- eine Methode, die **Prompt Stuffing**
genannt wird. Dabei werden Daten über ein Prompt-Template direkt in das
User Prompt eingefügt. Dieser Ansatz funktioniert gut für kleine,
überschaubare Datenmengen.

### Die Herausforderung im Enterprise-Umfeld

In Enterprise-Szenarien möchte man jedoch häufig auf hunderte von
Dokumenten, Datensätzen aus Datenbanken oder gesammelte E-Mails als
Kontextinformationen zugreifen. Hier wird Prompt Stuffing unpraktikabel:

- **Performance-Problem:** Die Daten können nicht zur Laufzeit bei jeder
  Modellanfrage vollständig in das User Prompt geladen werden. Dies
  würde viel zu lange dauern.

- **Ressourcen-Problem:** Das Laden großer Datenmengen würde enorme
  Ressourcen beanspruchen und die maximale Tokengröße des Modells
  überschreiten.

- **Relevanz-Problem:** Nicht alle Unternehmensdaten sind für jede
  Anfrage relevant.

### Die Lösung: RAG

RAG löst diese Probleme durch einen intelligenten Ansatz: Abhängig vom
Inhalt des User Prompts werden gezielt die relevanten Unternehmensdaten
gesucht und nur diese in das Prompt geladen. Dies geschieht durch:

4.  **Vorheriges Indexieren** der Unternehmensdaten in einer
    spezialisierten Datenbank

5.  **Semantische Suche** nach relevanten Informationen basierend auf
    der User-Anfrage

6.  **Selektives Anreichern** des Prompts nur mit den gefundenen,
    relevanten Daten

Dadurch erhält das Modell genau die Kontextinformationen, die es für
eine präzise Antwort benötigt -- nicht mehr und nicht weniger.

### Architektur

Eine RAG-Architektur besteht aus mehreren zusammenwirkenden Komponenten,
die den gesamten Prozess von der Datenvorbereitung bis zur
Antwortgenerierung abdecken.

**Advisors** spielen eine zentrale Rolle in der RAG-Architektur. Sie
fungieren als Interceptoren im Request-Response-Zyklus des Chat Client.
Beim Request liest der Advisor das aktuelle User Prompt ein, ermittelt
über die Vektordatenbank die dazu passenden Unternehmensdaten und
reichert das User Prompt mit diesen Kontextinformationen an. Beim
Response kann ein Advisor das Ergebnis im Assistant Prompt
nachbearbeiten, beispielsweise um Metadaten hinzuzufügen oder die
Antwort zu filtern. Es gibt also eine Reihe von Advisors, die für die
RAG-Bearbeitung notwendig sind.

**Vektordatenbanken** speichern die Unternehmensdaten in einer für
semantische Suchen optimierten Form. Die Daten werden als
Embedding-Vektoren abgelegt, die semantische Ähnlichkeiten mathematisch
repräsentieren.

**ETL-Module** sind für die Befüllung der Vektordatenbank
verantwortlich. Sie extrahieren Daten aus verschiedenen
Unternehmensquellen wie PDFs, Datenbanken oder E-Mails, transformieren
sie in geeignete Formate und laden sie in die Vektordatenbank.

Diese Architekturkomponenten arbeiten zusammen, um einen effizienten
RAG-Flow zu ermöglichen, der in den folgenden Abschnitten detailliert
beschrieben wird.

### Advisor

Advisors wurden bereits in einem früheren Kapitel ausführlich
beschrieben. Im Kontext von RAG werden Advisors eingesetzt, um den User
Prompt mit Kontextinformationen anzureichern und das Ergebnis im
Assistant Prompt nachzubearbeiten. Sie fungieren als zentrale
Orchestrierungskomponenten, die den gesamten RAG-Flow steuern und
koordinieren. In den darauffolgenden Kapiteln wird der konkrete Einsatz
der Advisors im Rahmen von RAG detailliert beschrieben.

### Quellen

Die Retrieval-Quellen, aus denen die Informationen zur Anreicherung der
Kontextinformationen bezogen werden können, sind unterschiedlich. Im
Folgenden werden die wichtigsten Quellentypen beschrieben:
Enterprise-Quellen für unternehmensinterne Daten, Web-Quellen für
aktuelle Informationen aus dem Internet und In-Memory-Quellen für
temporäre Daten.

#### Enterprise

Im Folgenden wird beschrieben, wie Kontextinformationen aus
Enterprise-Quellen gewonnen werden. Die Bereitstellung von
unternehmensspezifischen Kontextdaten für RAG erfordert mehrere
zusammenwirkende Komponenten, die einen durchgängigen Datenfluss von den
Ursprungsquellen bis zur Vektordatenbank ermöglichen.

Die Kontextinformationen werden in Form von sogenannten
**Embedding-Vektoren** abgelegt. Ein Embedding-Vektor ist eine
numerische Repräsentation von Texten, Bildern oder Videos. Diese
Vektoren haben eine Dimension, die die Anzahl der numerischen Werte im
Vektor beschreibt. Die Vektoren repräsentieren die semantische Bedeutung
der Inhalte, sodass ähnliche Inhalte durch ähnliche Vektoren dargestellt
werden.

Für die Erzeugung dieser Embeddings ist neben dem bereits beschriebenen
Chat Model ein **Embedding Model** notwendig, das von der
Vektordatenbank verwendet wird. Die Vektordatenbank speichert die
Kontextinformationen in Form von Embedding-Vektoren und ermöglicht
semantische Suchen über diese Daten. Ein **ETL-Modul** übernimmt die
Aufgabe, Daten aus Enterprise-Quellen wie PDFs, Datenbanken oder
Dokumenten-Management-Systemen zu extrahieren, zu transformieren und in
der Vektordatenbank abzulegen.

In den folgenden Abschnitten werden das Embedding Model, die
Vektordatenbank und das ETL-Modul im Detail beschrieben.

#### Embedding Model

Embeddings sind eine numerische Abbildung von Texten, Bildern oder
Videos in Form von Vektoren. Ein Vektor hat eine Dimension, die die
Anzahl der numerischen Werte im Vektor beschreibt. Diese Vektoren
repräsentieren die semantische Bedeutung der Inhalte. Beispielsweise
kann über die Distanz zwischen Vektoren bestimmt werden, wie ähnlich
sich Texte, Bilder oder Videos sind und ob sie thematisch
zusammengehören. Das gesamte Modell arbeitet mit einem großen
Vektorraum, in dem semantisch ähnliche Inhalte nahe beieinander liegen.

Wie Texte, Bilder oder Audio sind auch Embeddings eine Form von Inhalt,
die von Modellen verarbeitet werden können. Das Embedding Model API von
Spring AI ermöglicht den Zugriff auf verschiedene Embedding Model
Provider, die entsprechende APIs bereitstellen. In der Regel stellt
jeder Model Provider sein eigenes Embedding Model bereit, da die
Embeddings in den verschiedenen Modellen unterschiedlich abgebildet
werden.

Das Embedding Model ist ein Spring AI Content Model und baut auf dem
Spring AI Generic Model auf. Analog zum Chat Model gibt es auch hier
eine strukturierte API mit Klassen wie EmbeddingModel, EmbeddingOptions,
EmbeddingRequest, EmbeddingResponse und weiteren Komponenten. Diese
einheitliche Struktur ermöglicht den flexiblen Wechsel zwischen
verschiedenen Embedding Model Providern.

Spring AI unterstützt eine Vielzahl von Embedding Model Providern,
darunter OpenAI, Azure OpenAI, Mistral, Ollama, Amazon Bedrock, Google
VertexAI, OCI GenAI und PostgreML. Im Insurance Use Case wird Ollama als
Provider verwendet, da dieser eine lokale Entwicklungsumgebung
ermöglicht und keine Cloud-Anbindung erfordert.

Für Ollama wurde bereits die folgende Dependency in das Build Management
eingefügt:

    <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-starter-model-ollama</artifactId>
    </dependency>

Damit steht nicht nur das Chat Model, sondern auch das Embedding Model
zur Verfügung, das von der Autoconfiguration von Spring Boot automatisch
erzeugt wird. Dieses Embedding Model wird dann automatisch von der
Vektordatenbank verwendet, ohne dass eine manuelle Konfiguration
erforderlich ist.

Über die Spring Boot Properties ollama.embedding.\* kann das Embedding
Model konfiguriert werden. Die folgende Konfiguration zeigt die
Einstellungen für das Embedding Model von Ollama. Dabei wird der Name
des Embedding Models benötigt. Im Fall des Insurance AI Assistenten
wurde das Embedding Model mxbai-embed-large gewählt, da es eine gute
Balance zwischen Qualität und Ressourcenverbrauch bietet:

    ollama:
      embedding:
        model: mxbai-embed-large
        options:
          max-tokens: 1000

Zusätzlich gibt es weitere Spring Boot Properties unter
ai.ollama.init.\*, die für die Initialisierung relevant sind. Ollama
muss das Embedding Model neben dem bereits konfigurierten Chat Model
llama3.3 lokal installieren. Es handelt sich also um ein zusätzliches
Modell, das bereitgestellt werden muss:

    ai:
      ollama:
        init:
          pull-model-strategy: always
          embedding:
            additional-models:
              - mxbai-embed-large

**Wichtig:** Die Property pull-model-strategy muss zwingend angegeben
werden. Wird die Pull Model Strategy nicht definiert, kann die
Vektordatenbank bei der Initialisierung Probleme haben. Der genaue Grund
dafür ist derzeit nicht vollständig dokumentiert, und es wurde hierzu
ein Issue bei Spring AI eingestellt.

Die Autoconfiguration stellt sicher, dass beim Erstellen eines Chat
Model, beispielsweise Ollama, automatisch ein passendes Embedding Model
erzeugt wird. Wird anschließend eine Vektordatenbank konfiguriert,
verwendet diese automatisch das erzeugte Embedding Model. Damit ist die
Verwendung von Chat Model und Vektordatenbank weitgehend unabhängig
voneinander und erfordert nur minimale Konfiguration.

Die Wahl des richtigen Embedding Models hängt von verschiedenen Faktoren
ab. Größere Modelle liefern in der Regel präzisere Embeddings, benötigen
aber auch mehr Ressourcen bei der Verarbeitung und Speicherung. Die
Dimension der Vektoren variiert je nach Modell -- typische Werte liegen
zwischen 384 und 1536 Dimensionen. Eine höhere Dimension ermöglicht eine
feinere semantische Differenzierung, erhöht aber auch den Speicherbedarf
in der Vektordatenbank.

Das gewählte Embedding Model mxbai-embed-large bietet eine Dimension von
1024 und stellt damit einen guten Kompromiss dar. Es unterstützt
mehrsprachige Texte und ist speziell für Retrieval-Aufgaben optimiert.
Die maximale Token-Anzahl von 1000 definiert, wie viele Tokens pro
Text-Chunk in ein Embedding umgewandelt werden können. Diese Limitierung
muss bei der späteren Datenaufbereitung im ETL-Prozess berücksichtigt
werden.

Ein wichtiger Aspekt ist die Konsistenz des Embedding Models: Alle
Dokumente in der Vektordatenbank sollten mit demselben Embedding Model
verarbeitet werden. Ein Wechsel des Embedding Models während des
Betriebs würde erfordern, dass alle bereits indizierten Dokumente neu
verarbeitet werden müssen, da die Vektoren unterschiedlicher Modelle
nicht direkt vergleichbar sind.

#### Vektor Datenbank

Spring AI stellt für die Speicherung und Bearbeitung von Vektoren ein
umfassendes API zur Verfügung. Zu diesem API gehören im Wesentlichen die
folgenden Interfaces, die die Arbeit mit Vektordatenbanken abstrahieren:

Das Interface VectorStore stellt die zentralen Operationen für die
Bearbeitung von Vektoren auf einer Vektordatenbank zur Verfügung. Es
bietet Methoden zum Hinzufügen, Suchen und Löschen von Dokumenten in der
Vektordatenbank.

Der SearchRequest definiert eine Suche auf der Vektordatenbank basierend
auf einem Vektor Similarity Search. Es können zusätzliche
Filter-Ausdrücke angegeben werden, die sich allerdings auf die Metadaten
beschränken, die für jedes Embedding mit abgelegt werden. Diese
Metadaten können beispielsweise Informationen über die Dokumentquelle,
das Erstellungsdatum oder fachliche Kategorien enthalten.

Die Filter.Expression ermöglicht die Definition eines Filters über eine
dafür definierte DSL, ähnlich wie bei SQL-Abfragen. Die Abfragen
beschränken sich allerdings auf die Metadaten, die mit jedem Embedding
abgelegt werden, nicht auf den Textinhalt selbst.

Die BatchingStrategy ermöglicht, dass eine Menge von Dokumenten über
eine Operation in die Vektordatenbank geschrieben werden kann. Dies ist
besonders wichtig für die Performance bei der Erstbefüllung der
Datenbank mit großen Datenmengen.

Spring AI unterstützt eine Vielzahl von Vektordatenbanken, die sich in
verschiedene Klassen einteilen lassen:

  ------------------------------------------------------------------------
  Name          Klasse          Bemerkung
  ------------- --------------- ------------------------------------------
  Qdrant        OSS Vector      Reine Vektordatenbank, speziell für
                Database        Vektoroperationen optimiert

  PGvector      OSS SQL         Erweiterung einer existierenden
                                SQL-Datenbank (PostgreSQL)

  Azure         Cloud NoSQL     Vektordatenbank von Microsoft Azure für
  CosmosDB                      cloudbasierte Lösungen

  Typesense     SaaS            Als Software as a Service verfügbar,
                                vollständig verwaltet
  ------------------------------------------------------------------------

Für den Insurance Use Case wurde die Open Source Datenbank Qdrant
gewählt. Die Entscheidung fiel auf Qdrant, da es sich um eine
ausgereifte Open Source Lösung handelt, die für den Produktivbetrieb
geeignet ist. Zudem unterstützt diese Wahl ein europäisches Unternehmen
aus Berlin, was im Hinblick auf Datenschutz und Compliance vorteilhaft
sein kann.

Für die Verwendung der Vektordatenbank Qdrant ist der folgende Starter
in das Dependency-Management einzufügen:

    <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-starter-vector-store-qdrant</artifactId>
    </dependency>

Die Autoconfiguration stellt dann automatisch die
VectorStore-Implementierung zur Verfügung, in diesem Fall die
QdrantVectorStore. Es stehen eine Reihe von Spring Boot Properties unter
spring.ai.vectorstore.qdrant.\* zur Verfügung, um die Vektordatenbank zu
konfigurieren.

Die folgende Konfiguration wird für den Insurance Use Case gewählt:

    ai:
      vectorstore:
        qdrant:
          host: ${QDRANT_HOST:localhost}
          port: ${QDRANT_PORT:6334}
          collection-name: insurance
          initialize-schema: true

Der collection-name entspricht in relationalen Datenbanken
beispielsweise dem Datenbanknamen. Normalerweise benötigt eine
Vektordatenbank kein Schema, da sie nur aus Vektoren besteht, die eine
feste Dimension mit festen Datentypen besitzen. Moderne
Vektordatenbanken wie Qdrant unterstützen allerdings ein Schema, in dem
beispielsweise die Dimension der Vektoren oder die Felder und Datentypen
der Metadaten definiert werden können. In diesem Fall wird das
Standardschema von Qdrant initialisiert. Das Schreiben und Lesen in die
Datenbank erfolgt über das HTTP/2-basierte Streaming-Protokoll gRPC über
den Port 6334.

Im Insurance Use Case erfolgt die Bereitstellung des Qdrant Vector
Stores über einen Container qdrant/qdrant:latest, der automatisch über
das Spring Boot Docker Compose Feature gestartet wird. Die compose.yaml
liegt im Projektverzeichnis:

    services:
      qdrant:
        image: qdrant/qdrant:latest
        container_name: qdrant
        ports:
          - "6333:6333"   # REST API
          - "6334:6334"   # gRPC API
        volumes:
          - qdrant_data:/qdrant/storage

    volumes:
      qdrant_data:

Damit das Docker Compose File automatisch geladen und der Container
gestartet wird, ist der Docker Compose Starter von Spring Boot in das
POM einzufügen:

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-docker-compose</artifactId>
    </dependency>

Zudem sollten die Properties für command und arguments gesetzt werden:

    spring:
      docker:
        compose:
          enabled: true
          stop:
            command: down
            arguments:
              - --volumes

Die Vektordatenbank verwendet das Embedding Model, um für die Texte, die
in ihr gespeichert werden sollen, die zugehörigen Embedding-Vektoren zu
erzeugen. Das Embedding Model sollte idealerweise das gleiche sein wie
das Embedding Model des Chat Models, muss es aber nicht zwangsläufig
sein, da nur die Embeddings im Vektorraum der Kontextdaten betrachtet
werden und nicht des gesamten Modells.

Die Autoconfiguration erzeugt bei der Verwendung eines Chat Models wie
Ollama automatisch ein passendes Embedding Model. Wird anschließend der
Vector Store durch die Autoconfiguration erzeugt, wird automatisch das
bereits erzeugte Embedding Model verwendet. Damit ist die Verwendung von
Chat Model und Vektordatenbank weitgehend unabhängig voneinander, und
die Integration erfolgt nahtlos durch Spring AI.

Bei der Arbeit mit Vektordatenbanken sind einige wichtige Aspekte zu
beachten. Die **Similarity Search** ist die zentrale Operation, bei der
zu einem gegebenen Query-Vektor die ähnlichsten Vektoren in der
Datenbank gefunden werden. Die Ähnlichkeit wird dabei über
Distanzmetriken wie Cosinus-Ähnlichkeit oder Euklidische Distanz
berechnet. Über den Parameter topK wird festgelegt, wie viele ähnliche
Dokumente zurückgegeben werden sollen. Der Parameter similarityThreshold
definiert einen Schwellwert, unterhalb dessen Dokumente nicht mehr als
relevant betrachtet werden.

Die **Metadaten** spielen eine wichtige Rolle für die Filterung und
Organisation der Dokumente. Sie werden zusammen mit dem Vektor
gespeichert und können für Filteroperationen verwendet werden. Typische
Metadaten im Insurance Use Case sind beispielsweise die Dokumentquelle,
der Dokumenttyp oder fachliche Kategorien wie \"Lebensversicherung\"
oder \"Kfz-Versicherung\". Diese Metadaten ermöglichen es, die Suche auf
bestimmte Dokumenttypen einzuschränken, auch wenn die semantische
Ähnlichkeit ähnlich wäre.

Die **Performance** der Vektordatenbank hängt von verschiedenen Faktoren
ab. Die Größe der Vektorsammlung beeinflusst die Suchgeschwindigkeit,
ebenso wie die Dimension der Vektoren. Qdrant verwendet verschiedene
Indexierungsverfahren wie HNSW (Hierarchical Navigable Small World), um
auch bei großen Datenmengen schnelle Suchen zu ermöglichen. Die
Konfiguration dieser Parameter sollte basierend auf den spezifischen
Anforderungen des Use Cases erfolgt werden.

#### ETL

ETL steht für Extract, Transform und Load. Es gibt im Spring-Ökosystem
bereits mehrere Frameworks wie Spring Batch, Spring Integration oder
Spring Cloud Data Flow, die zentrale ETL-Funktionen abbilden.
Beispielsweise stellt Spring Batch für die Extraktion das Interface
ItemReader bereit, für die Transformation den ItemProcessor und für das
Laden den ItemWriter.

Allerdings sind die ETL-Module in diesen Frameworks in einen
spezifischen Kontext eingebettet, für den das jeweilige Framework
entwickelt wurde. Spring Batch ist beispielsweise auf die Verarbeitung
von Massendaten ausgerichtet und liefert in diesem Kontext neben dem
ETL-Modul eine Reihe von zusätzlichen Funktionen rund um das Thema
Massendatenverarbeitung. Auch das ETL-Modul selbst ist stark auf
Massendaten zugeschnitten und bringt entsprechende Komplexität mit sich.

Spring AI hat sich daher entschieden, ein eigenes ETL-Modul
bereitzustellen. Die Gründe für diese Entscheidung waren:

7.  Unabhängigkeit von anderen Frameworks wie Spring Batch

8.  Leichtgewichtiger Ansatz, da Spring Batch ein recht großes Framework
    mit einer steilen Lernkurve ist

9.  Ein Ansatz, der speziell für AI und RAG optimiert ist

Das ETL-Modul von Spring AI ist für kleinere bis mittlere ETL-Prozesse
gut geeignet und kann schnell eingesetzt werden. Allerdings ist es für
komplexere Aufgaben im Enterprise-Umfeld nicht immer ausreichend. Oft
müssen im Enterprise-Kontext verschiedene Datenquellen aus
unterschiedlichen Systemen über unterschiedliche Protokolle
zusammengetragen und aufwändig transformiert werden. In dem Buch
\"Spring AI in Action\" \[1\] ist ein Beispiel beschrieben, wo eine
Reihe von Spring Frameworks notwendig sind, um einen komplexen
ETL-Enterprise-Use-Case abzubilden. Allerdings besteht auch die
Möglichkeit, das bestehende ETL-Modul von Spring AI zu erweitern. Es ist
zu erwarten, dass in Zukunft weitere Unterstützung gerade für
Enterprise-Use-Cases entstehen wird.

Das ETL-Modul in Spring AI besteht aus den folgenden Interfaces, für die
bereits eine Reihe von Implementierungen existieren:

  ---------------------------------------------------------------------------
  Interface             Beschreibung                  Funktionen
  --------------------- ----------------------------- -----------------------
  DocumentReader        Liest Dokumente aus           JSON, Text, HTML,
                        unterschiedlichen Quellen     Markdown, PDF, DOCX,
                                                      PPTX

  DocumentTransformer   Transformiert eine Menge von  Text Splitter, Token
                        Dokumenten in kleinere        Splitter, Content
                        Einheiten (**Chunks**)        Enricher

  DocumentWriter        Schreibt die konvertierten    File System,
                        Dokumente in eine             Vektordatenbank
                        Zieldatenbank                 
  ---------------------------------------------------------------------------

Der DocumentReader ist für die Extraktion von Inhalten aus verschiedenen
Datenquellen verantwortlich. Spring AI bietet Reader für gängige Formate
wie PDF, Word-Dokumente oder Markdown-Dateien. Diese Reader wandeln die
Quelldokumente in ein einheitliches Document-Format um, das von den
nachfolgenden Verarbeitungsschritten verwendet werden kann.

Der DocumentTransformer übernimmt die Transformation der extrahierten
Dokumente. Eine zentrale Aufgabe ist das Aufteilen großer Dokumente in
kleinere **Chunks**, die in die Vektordatenbank passen. Der TextSplitter
teilt Dokumente basierend auf Zeichenanzahl, während der
TokenTextSplitter die Token-Grenzen des Embedding Models berücksichtigt.
Der ContentEnricher kann zusätzliche Metadaten zu den Dokumenten
hinzufügen, beispielsweise Informationen über die Dokumentquelle oder
das Erstellungsdatum.

Der DocumentWriter schreibt die transformierten Dokumente in die
Zieldatenbank. Die wichtigste Implementierung ist der
VectorStoreDocumentWriter, der Dokumente direkt in eine Vektordatenbank
schreibt. Dabei wird automatisch für jeden Text-Chunk ein
Embedding-Vektor über das konfigurierte Embedding Model erzeugt.

Im Insurance Use Case wird der ETL-Prozess verwendet, um PDF-Dokumente
mit Versicherungspolicies in die Qdrant-Vektordatenbank zu laden. Dabei
kommt der PagePdfDocumentReader zum Einsatz, der PDF-Seiten einliest.
Anschließend werden die Dokumente über einen ParagraphTextSplitter in
sinnvolle Absätze unterteilt. Diese **Chunks** werden dann über den
VectorStoreDocumentWriter in die Vektordatenbank geschrieben, wo sie für
spätere RAG-Anfragen zur Verfügung stehen.

Ein wichtiger Aspekt beim ETL-Prozess ist die Wahl der richtigen
Chunk-Größe. Zu kleine **Chunks** können wichtigen Kontext verlieren,
während zu große **Chunks** die Window Size des Embedding Models
überschreiten können. Die TokenCountBatchingStrategy stellt sicher, dass
die Token-Grenzen des Embedding Models eingehalten werden und teilt bei
Bedarf Dokumente weiter auf.

Der ETL-Prozess sollte als Teil der Anwendungsinitialisierung oder als
separater Batch-Job ausgeführt werden. Bei der Erstbefüllung der
Vektordatenbank ist es wichtig, dass alle Dokumente mit demselben
Embedding Model verarbeitet werden, um konsistente Vektoren zu erhalten.
Änderungen an den Quelldokumenten erfordern ein erneutes Durchlaufen des
ETL-Prozesses, um die Vektordatenbank zu aktualisieren.

Die Performance des ETL-Prozesses kann durch verschiedene Maßnahmen
optimiert werden. Die Verwendung von Batch-Operationen über die
BatchingStrategy reduziert die Anzahl der Datenbankzugriffe erheblich.
Zudem sollte die parallele Verarbeitung mehrerer Dokumente in Betracht
gezogen werden, wenn große Datenmengen zu verarbeiten sind. Die
Monitoring-Funktionen von Spring AI ermöglichen es, Engpässe im
ETL-Prozess zu identifizieren und zu optimieren.

#### Web

Oft kommt es vor, dass sich Anfragen durch den Zugriff auf aktuelle
Informationen im Web beantworten lassen. Während Enterprise-Quellen
unternehmensinterne Daten bereitstellen, ermöglicht die Integration von
Web-Quellen den Zugriff auf aktuelle Informationen aus dem Internet, um
Kontextinformationen zu beschaffen. Dies ist besonders relevant für
Anfragen, die zeitkritische oder sich schnell ändernde Informationen
erfordern.

Ein typischer Anwendungsfall für Web-Quellen ist die Beantwortung von
Fragen zu aktuellen Ereignissen, Nachrichten oder Marktinformationen,
die nicht in den statischen Enterprise-Daten verfügbar sind. Im
Versicherungskontext könnte dies beispielsweise aktuelle Informationen
zu regulatorischen Änderungen, Marktentwicklungen oder Schadensfällen
umfassen.

Für die Informationsbeschaffung aus dem Internet können eigene
Implementierungen entwickelt werden, die Web-Inhalte abrufen und
verarbeiten. Eine beispielhafte Implementierung ist unter \[2\] zu
finden. Diese Implementierung basiert auf dem Dienst Tavily \[3\], der
speziell für RAG-Anwendungen optimierte Web-Suchen anbietet.

Spring AI bietet derzeit keine native Unterstützung für Web-Quellen,
aber die modulare Architektur ermöglicht die Integration von
benutzerdefinierten DocumentReader-Implementierungen für Web-Inhalte.
Die Informationsbeschaffung aus dem Internet wird im Rahmen dieses
Buches nicht weiter vertieft, da der Fokus auf Enterprise-Quellen liegt.

#### Memory

Neben Enterprise- und Web-Quellen können auch Informationen über
In-Memory-Quellen bereitgestellt werden. Gerade bei Tests ist dies eine
interessante Möglichkeit, ähnlich wie es bei Datenbanken beispielsweise
H2 oder HSQLDB gibt. In-Memory-Quellen ermöglichen es, Testdaten ohne
externe Abhängigkeiten bereitzustellen und Tests schnell und isoliert
auszuführen.

Spring AI plant die Unterstützung von In-Memory-Vektorspeichern in
zukünftigen Versionen. Diese würden es ermöglichen, RAG-Flows in
Testumgebungen zu validieren, ohne eine vollständige
Vektordatenbank-Infrastruktur aufsetzen zu müssen. Die Details zur
In-Memory-Unterstützung werden in einer späteren Version von Spring AI
verfügbar sein.

### Module

Die Komplexität der Anforderungen und Anwendungsfälle an **RAG** hat in
den letzten Jahren zugenommen. Diese Problematik wurde von Yunfan Gao et
al. in dem Paper \"Modular RAG\" \[4\] aufgegriffen und der Vorschlag
eines **Modular RAG**-Ansatzes beschrieben. **Modular RAG** besteht aus
den fünf Modulen **Indexing**, **Pre-Retrieval**, **Retrieval**,
**Post-Retrieval** und **Generation** sowie deren Orchestrierung. Spring
AI hat dieses Konzept explizit aufgegriffen und **Operatoren** für
**Modular RAG** implementiert.

In den folgenden Kapiteln wird beschrieben, wie die **RAG-Module** in
Spring AI implementiert und verwendet werden. Dabei werden die
**RAG-Phasen** und **RAG-Operationen** motiviert und gezeigt, wie diese
konkret im Kontext des Insurance Use Cases mit Spring AI Advisors, ETL
und Vektordatenbanken umgesetzt werden.

#### Indexing

**Indexing** beschreibt die Aufgabe, eine Menge von Daten,
beispielsweise Dokumente, in eine Menge von kleineren Dokumenten, auch
**Chunks** genannt, zu zerlegen und in einer geeigneten Datenbank
abzulegen, die für die spätere Abfrage im Retrieval relevante
Informationen bereitstellt. Mit dem Indexing sind einige
Herausforderungen verbunden. Bereits die richtige Größe der **Chunks**
spielt eine große Rolle für die Qualität des RAG-Systems.

Das Indexing benötigt im Wesentlichen die beiden Komponenten ETL und
Vektordatenbanken, die bereits in den vorherigen Kapiteln beschrieben
wurden. Das ETL-Modul übernimmt das Extrahieren, Transformieren und
Laden der Dokumente, während die Vektordatenbank die transformierten
Daten als **Embeddings** speichert.

Im Kontext von Vektordatenbanken ist der **Index** der Vektor selbst,
der im mehrdimensionalen Vektorraum positioniert wird. Jeder **Chunk**
wird durch das Embedding Model in einen Vektor umgewandelt, der die
semantische Bedeutung des Textes repräsentiert. Dieser Vektor bildet den
Index, über den später bei der Suche ähnliche Dokumente gefunden werden
können. Die Position des Vektors im Vektorraum bestimmt seine
semantische Nähe zu anderen Vektoren -- je näher zwei Vektoren
beieinander liegen, desto ähnlicher ist ihre Bedeutung.

Im Insurance Use Case ist ein PDF-Dokument zu lesen, um die
Regulatoriken herauszulesen, die für die Versicherungsanfragen wichtig
sind. Um PDF-Dokumente verarbeiten zu können, ist der folgende Starter
in das Dependency-Management einzufügen:

    <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-pdf-document-reader</artifactId>
    </dependency>

Wichtig beim Aufteilen der Daten in kleinere **Chunks** sind die
folgenden Punkte:

10. Window Size des Embedding Models nicht überschreiten (Maximal
    Tokens)

11. Window Size des Chat Models nicht überschreiten (Maximal Tokens)

12. Semantische Separation der Dokumente (inhaltliche Aufteilung)

13. Metadata Enrichment (Anreicherung der Vektoren mit sinnvollen
    Metadaten)

14. Overlapping (Überlappung zwischen Chunks zur Kontexterhaltung)

Im Insurance Use Case sind die einzelnen Kapitel der PDF-Dokumente
wichtig, da dort die Regulatoriken der Versicherung abgelegt sind, die
später über den AI-Assistenten abgefragt werden. Leider kann der
ParagraphPdfDocumentReader, der für das Einlesen von Kapiteln aus
PDF-Dokumenten vorgesehen ist, in diesem Fall nicht verwendet werden, da
dieser voraussetzt, dass das PDF-Dokument ein Inhaltsverzeichnis
enthält.

Daher ist der PagePdfDocumentReader notwendig, der das PDF-Dokument in
**Chunks** unterteilt, die jeweils einer Seite entsprechen. Das reicht
allerdings nicht aus, da eine Unterteilung in Kapitel und nicht in
Seiten benötigt wird. Daher ist eine weitere Transformation notwendig.
Leider gibt es keine passende Implementierung des TextSplitter, um einen
Text aus dem PDF-Dokument in Absätze zu unterteilen. Daher wurde ein
eigener Transformator mit dem Namen ParagraphTextSplitter implementiert
und verwendet:

    PagePdfDocumentReader reader = new PagePdfDocumentReader("classpath:/test-document.pdf");
    List<Document> docs = reader.get();

    // Paragraphen-Splitter anwenden
    ParagraphTextSplitter splitter = new ParagraphTextSplitter();
    List<Document> paragraphs = splitter.split(docs);

Die so erstellten Dokumente können im Anschluss über die Methode
add(List\<Document\>) in die Vektordatenbank geschrieben werden:

    vectorStore.add(paragraphs);

Die Vektordatenbank verwendet im Hintergrund für das Schreiben der
Dokumente eine Implementierung des Typs BatchingStrategy. Über die
Autoconfiguration wird die Implementierung TokenCountBatchingStrategy
bereitgestellt, die darauf achtet, dass die maximale Anzahl der Token
des Embedding Models nicht überschritten wird. Die Größe des Textes in
den Dokumenten, der durch einen Embedding-Vektor repräsentiert werden
kann, ist beschränkt. Dabei wird ein Text in Elemente, die sogenannten
Tokens, unterteilt. Die Anzahl der Tokens ist beschränkt, wobei die
maximale Größe durch die Window Size angegeben wird.

Sofern die maximale Window Size des Dokuments überschritten wird, wird
das Dokument in zwei oder mehrere Dokumente aufgeteilt. Grundsätzlich
wäre bereits beim TextSplitter die Möglichkeit, die Window Size zu
berücksichtigen, die über den Konstruktor übergeben werden kann.
Allerdings weiß man zu diesem Zeitpunkt nicht immer, welche
Vektordatenbank verwendet wird oder um welches Embedding Model es sich
handelt. Grundsätzlich ist die weitere Unterteilung der Dokumente nicht
vorteilhaft, da Kontextinformationen über die Dokumente hinweg
verlorengehen können. Es gibt Möglichkeiten, mit diesem Problem
umzugehen, die aber hier nicht weiter beschrieben werden.

#### Retrieval

Das **Retrieval** ist dafür verantwortlich, bezogen auf den Inhalt des
User Prompts semantisch ähnliche Texte, also Dokumente, zu ermitteln.
Die lesenden Operationen auf der Vektordatenbank spielen für das
Retrieval eine zentrale Rolle, da sie die Performance und Qualität der
Dokumentensuche maßgeblich beeinflussen. Der Advisor für die
Implementierung des **RAG-Flow** spielt ebenfalls eine große Rolle, da
er das Retrieval orchestriert und in den gesamten Verarbeitungsprozess
einbettet.

Die zentrale Frage ist: Wie funktioniert das?

Eine **Query** repräsentiert eine Abfrage im Kontext des Retrieval. Die
Query beinhaltet den aktuellen Text des User Prompts, kann aber auch
historische Informationen aus dem Gesprächsverlauf (History) sowie
zusätzliche Kontextinformationen (Context) enthalten. Diese
Informationen werden aus dem Chat Client abgeleitet, beispielsweise aus
dem aktuellen User Prompt, dem Conversation Memory oder anderen
relevanten Quellen.

Die Abstraktion für das Retrieval stellt das Interface DocumentRetriever
mit der Methode retrieve(Query) bereit, die eine Liste von Dokumenten
zurückgibt. Im Folgenden wird das Retrieval auf einer Vektordatenbank
genauer beschrieben. Es können auch eigene Implementierungen entwickelt
werden, und Retrieval auf anderen Quellen wie Web oder In-Memory ist
ebenfalls möglich.

Eine zentrale Implementierung des DocumentRetriever ist der
VectorStoreDocumentRetriever. In diesem Fall wird das Retrieval auf
einer Vektordatenbank, einem VectorStore, durchgeführt. Die folgende
Implementierung zeigt die Erzeugung eines VectorStoreDocumentRetriever:

    @Bean
    public VectorStoreDocumentRetriever createDocumentRetriever(VectorStore vectorStore) {
        return VectorStoreDocumentRetriever.builder()
                                           .vectorStore(vectorStore)
                                           .topK(5)
                                           .similarityThreshold(0.5)
                                           .build();
    }

Der VectorStoreDocumentRetriever erstellt beim Aufruf der
retrieve(Query)-Methode aus den Informationen, die über den Builder
übergeben werden, ein SearchRequest-Objekt. Mit Hilfe des SearchRequest
wird dann auf der Vektordatenbank eine Similarity Search durchgeführt,
die eine Liste ähnlicher Dokumente zurückgibt, die der übergebenen Query
semantisch ähnlich sind.

Der Parameter topK definiert dabei, wie viele der ähnlichsten Dokumente
zurückgegeben werden sollen. Im Beispiel werden die fünf ähnlichsten
Dokumente ausgewählt. Der Parameter similarityThreshold legt einen
Schwellwert fest, unterhalb dessen Dokumente nicht mehr als relevant
betrachtet werden. Ein Wert von 0.5 bedeutet, dass nur Dokumente mit
einer Ähnlichkeit von mindestens 50% zurückgegeben werden.

Das Retrieval muss jedoch nicht manuell durchgeführt werden, sondern ist
eingebettet in den bereits eingangs beschriebenen sequenziellen
**RAG-Flow**. Dieser wird durch den RetrievalAugmentationAdvisor
implementiert. Damit der Advisor verwendet werden kann, ist die folgende
Dependency in das Build-Management aufzunehmen:

    <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-rag</artifactId>
    </dependency>

Die folgende Implementierung zeigt, wie ein Advisor vom Typ
RetrievalAugmentationAdvisor erstellt und der
VectorStoreDocumentRetriever an diesen übergeben werden kann. Im
Insurance Use Case wird der Advisor als Spring Bean in den Container
gelegt und bei der Erstellung des Chat Client verwendet:

    @Bean
    public RetrievalAugmentationAdvisor createRetrievalAugmentationAdvisor(
            VectorStoreDocumentRetriever vectorStoreDocumentRetriever) {
        return RetrievalAugmentationAdvisor.builder()
                                           .documentRetriever(vectorStoreDocumentRetriever)
                                           .build();
    }

Der RetrievalAugmentationAdvisor orchestriert den gesamten RAG-Flow: Er
nimmt das User Prompt entgegen, führt das Retrieval über den
VectorStoreDocumentRetriever durch, erhält die relevanten Dokumente aus
der Vektordatenbank und reichert das User Prompt mit diesen
Kontextinformationen an, bevor es an das Chat Model weitergeleitet wird.

Die Qualität des Retrieval hängt entscheidend von mehreren Faktoren ab.
Die Wahl der topK-Anzahl muss sorgfältig erfolgen: Zu wenige Dokumente
könnten wichtige Informationen auslassen, während zu viele Dokumente das
Prompt überladen und zu Rauschen führen können. Der similarityThreshold
sollte hoch genug sein, um irrelevante Dokumente auszuschließen, aber
niedrig genug, um auch leicht abweichende, aber relevante Informationen
zu berücksichtigen.

Im Insurance Use Case werden die Parameter so gewählt, dass die fünf
relevantesten Dokumente mit einer Mindestähnlichkeit von 50% abgerufen
werden. Diese Einstellung hat sich in Tests als guter Kompromiss
zwischen Vollständigkeit und Präzision erwiesen. Die tatsächlich
optimalen Werte können jedoch je nach Anwendungsfall variieren und
sollten durch systematisches Testen ermittelt werden.

In den folgenden Kapiteln wird beschrieben, wie das Pre-Retrieval und
Post-Retrieval aus dem sequenziellen **RAG-Flow** über den
RetrievalAugmentationAdvisor abgebildet werden und wie diese Module zur
Optimierung des Retrieval-Prozesses beitragen.

#### Pre-Retrieval

Das **Pre-Retrieval**-Modul ist dafür verantwortlich, die User Query zu
optimieren, bevor das Retrieval auf die Vektordatenbank durchgeführt
wird. Oft ist die User Query nicht optimal dafür geeignet, um mit ihr
eine effektive Suche auf der Vektordatenbank durchzuführen.
Beispielsweise wenn die Konversation bereits sehr lang ist und viele
Informationen vorliegen, oder wenn die Query mehrdeutig oder
unvollständig formuliert ist. Es ist daher möglich, die User Query über
die Sub-Module Query Transformation, Expansion und Construction zu
bearbeiten und erst dann das Retrieval auf der Vektordatenbank
durchzuführen.

Die folgende Tabelle zeigt die von Spring AI bereitgestellten Operatoren
für die Sub-Module Query Transformation und Query Expansion:

  -----------------------------------------------------------------------------
  Sub-Modul     Operator                      Beschreibung
  ------------- ----------------------------- ---------------------------------
  Transformer   QueryTransformer              Transformationsinterface

                CompressionQueryTransformer   Komprimiert eine User Query

                RewriteQueryTransformer       Schreibt eine User Query neu,
                                              falls schlecht formuliert

                TranslationQueryTransformer   Übersetzt eine User Query in eine
                                              andere Sprache

  Expansion     MultiQueryExpander            Expandiert eine User Query in
                                              mehrere Varianten
  -----------------------------------------------------------------------------

Für den Insurance Use Case ist keiner der genannten Operatoren sinnvoll
einsetzbar, da die Queries in der Regel präzise formuliert sind und
keine Transformation benötigen. Daher zeigt die folgende Implementierung
beispielhaft den Einsatz eines QueryTransformer über eine eigene
Implementierung InsuranceQueryTransformer, welche die Query nicht weiter
transformiert, sondern einfach durchreicht:

    @Bean
    public RetrievalAugmentationAdvisor createRetrievalAugmentationAdvisor(
            VectorStoreDocumentRetriever vectorStoreDocumentRetriever) {
        return RetrievalAugmentationAdvisor.builder()
                                           .queryTransformers(new InsuranceQueryTransformer())
                                           .documentRetriever(vectorStoreDocumentRetriever)
                                           .build();
    }

Diese Implementierung zeigt das Prinzip der Query-Transformation, auch
wenn im konkreten Fall keine Transformation stattfindet. In komplexeren
Szenarien könnten hier beispielsweise mehrdeutige Begriffe aufgelöst
oder Synonyme ergänzt werden.

#### Post-Retrieval

Das **Post-Retrieval**-Modul ist dafür verantwortlich, die Ergebnisse
des Retrieval zu optimieren, bevor diese als Kontextinformationen in das
User Prompt eingebracht werden. Beispielsweise möchte man bestimmte
Ergebnisdokumente höher priorisieren als andere oder Redundanzen
eliminieren, bevor sie als Argumente in das User Prompt eingestellt
werden.

Es ist daher möglich, die Ergebnisse über die Sub-Module **Ranking**,
**Compression** oder **Selection** zu bearbeiten und erst dann die
Ergebnisse in das User Prompt einzufügen. Das grundlegende Interface für
die Implementierung der Operatoren ist der DocumentPostProcessor. Die
folgende Tabelle zeigt die Sub-Module und ihre geplanten Funktionen:

  -----------------------------------------------------------------------------
  Sub-Modul         Operator           Beschreibung
  ----------------- ------------------ ----------------------------------------
  **Rank**          Keine              Bringt die Ergebnisdokumente in eine
                    Unterstützung      andere Reihenfolge, um wichtigen
                    durch Spring AI    Dokumenten mehr Bedeutung zu geben

  **Compression**   Keine              Komprimiert die Dokumente, um
                    Unterstützung      Redundanzen oder Rauschen zu reduzieren
                    durch Spring AI    

  **Selection**     Keine              Selektiert nur eine bestimmte Menge an
                    Unterstützung      Dokumenten aus dem Ergebnis, um z.B. die
                    durch Spring AI    Window Size nicht zu überschreiten
  -----------------------------------------------------------------------------

Wie zu sehen ist, gibt es derzeit keine Implementierungen der Operatoren
durch Spring AI. Es ist aber abzusehen, dass in Zukunft
Implementierungen zur Verfügung gestellt werden, da diese Funktionen für
produktive RAG-Systeme wichtig sind.

Für den Insurance Use Case ist keiner der genannten Operatoren aktuell
sinnvoll einsetzbar. Daher zeigt die folgende Implementierung
beispielhaft den Einsatz eines DocumentPostProcessor über eine eigene
Implementierung InsuranceDocumentPostProcessor, welcher die
Ergebnisdokumente nicht weiter bearbeitet, sondern einfach durchreicht:

    @Bean
    public RetrievalAugmentationAdvisor createRetrievalAugmentationAdvisor(
            VectorStoreDocumentRetriever vectorStoreDocumentRetriever) {
        return RetrievalAugmentationAdvisor.builder()
                                           .queryTransformers(new InsuranceQueryTransformer())
                                           .documentRetriever(vectorStoreDocumentRetriever)
                                           .documentPostProcessors(new InsuranceDocumentPostProcessor())
                                           .build();
    }

Diese Implementierung zeigt die vollständige Pipeline:
Query-Transformation im Pre-Retrieval, Dokumentenabfrage im Retrieval
und Dokumentennachbearbeitung im Post-Retrieval.

#### Generation

Das **Generation**-Modul steht am Ende des sequenziellen **RAG-Flow**
und ist dafür verantwortlich, die finale Anfrage aus der Query des User
Prompts und den Dokumenten aus dem Retrieval zu generieren. Es gibt zwei
Sub-Module **Fine-Tuning** und **Verification**, die dafür
verantwortlich sind.

Das grundlegende Interface für die Implementierung der Operatoren ist
der QueryAugmenter. Die folgende Tabelle zeigt die von Spring AI
bereitgestellten Operatoren:

  ---------------------------------------------------------------------------------
  Sub-Modul          Operator                   Beschreibung
  ------------------ -------------------------- -----------------------------------
  **Fine-Tuning**    ContextualQueryAugmenter   Ermöglicht die Query und die
                                                finalen Dokumente aus dem Retrieval
                                                zu einer Anfrage zusammenzuführen

  **Verification**   Keine Unterstützung durch  Ermöglicht die Überprüfung der
                     Spring AI                  Query und der finalen Dokumente auf
                                                Informationen, die nicht in die
                                                Anfrage aufgenommen werden sollen
  ---------------------------------------------------------------------------------

Der RetrievalAugmentationAdvisor verwendet intern standardmäßig den
ContextualQueryAugmenter, der die Query aus dem User Prompt und die
Dokumente aus dem Retrieval zu einer Anfrage zusammenfasst. Die folgende
Implementierung zeigt die vollständige Konfiguration mit expliziter
Angabe des QueryAugmenter:

    @Bean
    public RetrievalAugmentationAdvisor createRetrievalAugmentationAdvisor(
            VectorStoreDocumentRetriever vectorStoreDocumentRetriever) {
        return RetrievalAugmentationAdvisor.builder()
                                           .queryTransformers(new InsuranceQueryTransformer())
                                           .documentRetriever(vectorStoreDocumentRetriever)
                                           .documentPostProcessors(new InsuranceDocumentPostProcessor())
                                           .queryAugmenter(ContextualQueryAugmenter.builder()
                                                                                   .allowEmptyContext(true)
                                                                                   .build())
                                           .build();
    }

Die Methode queryAugmenter() hat entscheidenden Einfluss auf die
Generation. In der Konfiguration wird über allowEmptyContext(true)
eingestellt, dass auch dann eine Anfrage an das Modell gestellt wird,
wenn keine passenden Dokumente im Kontext gefunden werden. In diesem
Fall wird die Anfrage mit leerem Kontext an das Chat Model
weitergeleitet, sodass das Modell trotzdem versuchen kann, eine Antwort
basierend auf seinem Trainingswissen zu generieren.

#### Orchestration

Das **Orchestration**-Modul ist dafür verantwortlich, unterschiedliche
**RAG-Flows** zu realisieren. In den vorausgegangenen Kapiteln wurde ein
einfacher sequenzieller RAG Flow betrachtet, bei dem die Module
nacheinander durchlaufen werden. Es gibt darüber hinaus allerdings eine
Reihe von komplexeren RAG-Flows, die im Modular RAG Paper \[4\]
beschrieben sind. Das Modul Orchestration unterteilt sich in die
Sub-Module **Route** und **Scheduling**, die verschiedene
Verarbeitungsstrategien ermöglichen sollen.

Da die Orchestration aktuell nicht von Spring AI unterstützt wird, wird
an dieser Stelle nicht weiter auf das Thema eingegangen. Man darf
gespannt sein, was sich in diesem Bereich in den nächsten Versionen von
Spring AI entwickeln wird, da komplexe Orchestrierungsmuster für
fortgeschrittene RAG-Systeme wichtig sind.

## Tools

### Motivation

Die Modelle sind mit Daten aus der Vergangenheit trainiert und kennen
daher keine aktuellen Informationen wie die aktuelle Wetterlage, die
Uhrzeit oder aktuelle Börsenkurse. Zudem haben die Modelle keinen
Zugriff auf aktuelle unternehmensspezifische Daten wie den Kundenstatus,
Vertragsinformationen oder interne Geschäftsprozesse.

Mit **Tools** können wir das Modell mit aktuellen und spezifischen Daten
anreichern. Tools können Daten aus verschiedenen Quellen lesen,
beispielsweise die aktuelle Uhrzeit aus dem Betriebssystem, den
Kundenstatus aus einer Datenbank oder Wetterinformationen von einer API.
Tools können Daten aber auch ändern, beispielsweise den Vertragsstatus
eines Kunden aktualisieren oder eine Bestellung aufgeben.

Tools sind keine Erfindung von Spring AI, sondern wurden ursprünglich
vom OpenAI API eingeführt. Allerdings haben auch andere Modell-Anbieter
die Notwendigkeit erkannt und bieten die Einbindung von Tools in ihren
Modellen an. Dabei werden die Informationen über das Tool beim Aufruf
der Modell-API in Form einer JSON-Beschreibung übergeben:

    {
      "name": "get_customer_details",
      "description": "Gibt die Kundendaten eines Versicherungskunden zurück.",
      "parameters": {
        "type": "object",
        "properties": {
          "customerName": {
            "type": "string",
            "description": "Der Name des Kunden, z.B. 'Max Mustermann'."
          },
          "includeContracts": {
            "type": "boolean",
            "description": "Gibt an, ob auch Vertragsinformationen zurückgegeben werden sollen."
          }
        },
        "required": ["customerName"]
      }
    }

Das Chat Model wählt das passende Tool anhand seiner Definition und der
aktuellen Anfrage aus. Wenn ein Tool ausgewählt wurde, antwortet das
Chat Model über einen Chat Response, in dem ein oder mehrere Tools
enthalten sind, die ausgeführt werden sollen. Das Chat Model ruft dann
automatisch die in der Spring AI Anwendung dazu passenden Tools auf.

Das Tool ist beispielsweise ein in Spring Boot implementierter Service,
der über ein Repository Kundendaten aus der Kundendatenbank beschafft.
Diese Daten werden an das Chat Model zurückgegeben. Es ist möglich, dass
dieser Austausch von Informationen mehrmals ausgeführt wird, bis das
Chat Model alle benötigten Informationen hat, um die Anfrage des
Benutzers zu beantworten.

### Implementierung

Es gibt drei unterschiedliche Ansätze, Tools in Spring AI zu
implementieren. Die Basis für die Implementierung von Tools ist der
**Spring Tool Native Ansatz (STNA)**, der eine Reihe von Interfaces und
Klassen bereitstellt. In den meisten Fällen reichen jedoch die beiden
anderen Ansätze **Method as a Tool (MaaT)** oder **Function as a Tool
(FaaT)** aus, die im Hintergrund STNA verwenden und damit quasi als
Fassade wirken.

#### API

Der **Spring Tool Native Ansatz (STNA)** bildet die Basis für die
Implementierung von Tools und stellt eine Reihe von Interfaces und
Klassen bereit, die zusammen ein vollständiges Framework für die
Tool-Integration bieten.

Das ToolCallback-Interface definiert die zentrale Callback-Schnittstelle
für Tool-Aufrufe. Eng damit verbunden ist die ToolDefinition, die die
Metadaten eines Tools beschreibt, wie Name, Parameter und Beschreibung.
Zusätzliche Metainformationen zum Tool werden über ToolMetadata
bereitgestellt, während der ToolContext Kontextinformationen für die
Tool-Ausführung zur Verfügung stellt.

Für die Verarbeitung der Tool-Ergebnisse ist der ToolResultConverter
verantwortlich, der das Ergebnis eines Tools in ein für das Modell
geeignetes Format konvertiert. Die Auflösung von Tool-Callbacks
basierend auf dem Tool-Namen übernimmt der ToolCallbackResolver, und der
ToolCallbackManager verwaltet alle registrierten Tool-Callbacks zentral.
Bei Fehlern greift der ToolExceptionProcessor, der Exceptions während
der Tool-Ausführung verarbeitet. Die eigentliche Ausführung eines Tools
wird durch ToolExecution repräsentiert.

Es ist jedoch nicht zwingend notwendig, STNA direkt zu verwenden. In den
meisten Fällen reicht einer der beiden anderen Ansätze MaaT oder FaaT
aus. Wenn die Ansätze MaaT oder FaaT verwendet werden, ist ein tieferes
Verständnis der Schnittstellen und Klassen nicht notwendig. Alle
Features können über MaaT oder FaaT direkt abgebildet werden. Die
Verwendung des STNA ist nur dann notwendig, wenn der Chat Client nicht
verwendet werden kann und die Tools direkt an das Chat Model übergeben
werden müssen.

#### Method as a Tool (MaaT)

**Method as a Tool (MaaT)** ist der einfachste und am häufigsten
verwendete Ansatz zur Tool-Implementierung. Über die Annotation \@Tool
wird eine Methode als Tool definiert. Über die Methodenparameter, die
mit \@ToolParam deklariert werden, werden Parameter aus dem Modell an
die Methode übergeben, und über die Methodenrückgabe werden die
benötigten Informationen an das Chat Model zurückgegeben.

Die \@Tool-Annotation bietet folgende Attribute zur Konfiguration:

  ---------------------------------------------------------------------------
  Attribut          Typ       Beschreibung
  ----------------- --------- -----------------------------------------------
  name              String    Name des Tools (technischer Bezeichner)

  description       String    Beschreibung, welche Aufgabe das Tool löst

  returnDirect      Boolean   Der Rückgabewert des Tools wird direkt
                              zurückgegeben und nicht an das Modell
                              weitergeleitet

  resultConverter   Class     Die automatische Serialisierung des
                              Rückgabewertes kann explizit implementiert
                              werden, um ein bestimmtes Format zu erhalten
  ---------------------------------------------------------------------------

Die \@ToolParam-Annotation definiert die Parameter des Tools:

  -----------------------------------------------------------------
  Attribut      Typ       Beschreibung
  ------------- --------- -----------------------------------------
  required      Boolean   Gibt an, ob der Parameter zwingend
                          benötigt wird

  description   String    Beschreibung, welcher Parameter benötigt
                          wird
  -----------------------------------------------------------------

Im Insurance Use Case wird ein Tool implementiert, das Kundendaten aus
dem Versicherungssystem abruft. Die folgende Implementierung zeigt die
InsuranceCustomerDetailsTool-Klasse:

    @Component
    public class InsuranceCustomerDetailsTool {

        private InsuranceCustomerService insuranceCustomerService;

        public InsuranceCustomerDetailsTool(InsuranceCustomerService insuranceCustomerService) {
            this.insuranceCustomerService = insuranceCustomerService;
        }

        @Tool(name = "get_CustomerDetails", 
              description = "Stellt eine Anfrage an den Versicherungsservice, um Kundendaten zu erhalten.")
        public Customer getCustomerDetails(
                @ToolParam(required = true, description = "Name des Kunden") String name) {
            return insuranceCustomerService.getCustomerDetails(name);
        }
    }

Die Klassen, die Methoden für Tools bereitstellen, können direkt über
den Chat Client an das Modell übergeben werden. Dabei können die
Klasseninstanzen über die Methode tools() oder der Name der Methode über
toolNames() an den Chat Client übergeben werden. Analog dazu gibt es
auch die Methoden defaultTools() und defaultToolNames(), damit steht das
Tool von Anfang an für alle Aufrufe zur Verfügung.

    var chatClient = builder
        .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
        .defaultSystem(prompt.getContents())
        .defaultTools(insuranceCustomerDetailsTool)
        .defaultToolNames("get_ProductDetailsByCustomer")
        .build();

Wichtig zu beachten ist, dass Default-Tools thread-safe implementiert
werden müssen, da sie von mehreren Aufrufen gleichzeitig verwendet
werden können. Im Insurance Use Case ist dies durch die Verwendung von
Spring-managed Services gewährleistet.

#### Function as a Tool (FaaT)

**Function as a Tool (FaaT)** bietet einen alternativen Ansatz zur
Tool-Implementierung basierend auf funktionaler Programmierung. Tools
können als Function übergeben werden, indem eines der bekannten
Functional Interfaces implementiert wird: Function, Supplier, Consumer
oder BiFunction.

Die Bereitstellung erfolgt über die Spring Configuration. Es wird eine
Konfigurationsklasse mit \@Configuration definiert, und innerhalb dieser
Klasse wird eine Factory-Methode über \@Bean bereitgestellt. Der Name
des Tools entspricht dem Namen der Bean, und für die Beschreibung der
Verwendung wird die Annotation \@Description verwendet.

Im Insurance Use Case wird ein Tool als Function implementiert, das
Produktdaten zu einem Kunden abruft:

    @Configuration
    public class InsuranceProductDetailsByCustomerFunctionConfiguration {

        @Bean("get_ProductDetailsByCustomer")
        @Description("Stellt eine Anfrage an den Versicherungsservice, um Produktdaten zu erhalten.")
        public Function<ProductRequest, Product> createProductDetailsByCustomerFunction(
                InsuranceProductService insuranceProductService) {
            return (productRequest) -> insuranceProductService.getProductDetailsByCustomer(productRequest.getName());
        }
    }

Der Rückgabetyp ist eines der genannten Functional Interfaces mit dem
Typ des Rückgabewerts und dem Parametertyp, sofern diese existieren.
Wichtig ist, dass der Parametertyp kein primitiver Typ wie String sein
kann, sondern die Parameter müssen in eine Klasse eingebettet werden und
mit der Annotation \@ToolParam annotiert werden.

Die folgende Klasse zeigt die ProductRequest-Klasse, die als
Parametertyp für die Function verwendet wird:

    public class ProductRequest {

        @ToolParam(description = "Name des Kunden", required = true)
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

Die Übergabe des Tools an das Modell erfolgt über die Methode
toolNames() bei jedem Aufruf oder über defaultToolNames() für alle
Aufrufe. Dabei wird der Name der Bean an die Methode übergeben, nicht
ihre Instanz:

    var chatClient = builder
        .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
        .defaultSystem(prompt.getContents())
        .defaultTools(insuranceCustomerDetailsTool)
        .defaultToolNames("get_ProductDetailsByCustomer")
        .build();

Der Name des Tools kann auch über die Konfiguration übergeben werden:

    ai:
      openai:
        api-key: ${OPENAI_API_KEY}
        chat:
          options:
            tool-names:
              - get_ProductDetailsByCustomer

Analog zu MaaT kann FaaT auch über die native Implementierung realisiert
werden. Dies ist hilfreich, wenn ein tieferes Verständnis der
zugrundeliegenden Mechanismen benötigt wird oder wenn das Tool direkt an
ein Chat Model übergeben werden soll, ohne den Chat Client zu verwenden.
Über den Builder des FunctionToolCallback kann eine Instanz vom Typ
ToolCallback erstellt werden:

    public ToolCallback createFunctionBasedToolCallback(InsuranceProductService insuranceProductService) {
        return FunctionToolCallback.<ProductRequest, Product>builder(
            "get_ProductDetailsByCustomer",
            (ProductRequest productRequest, ToolContext context) ->
                insuranceProductService.getProductDetailsByCustomer(productRequest.getName())
        )
        .description("Stellt eine Anfrage an den Versicherungsservice, um Produktdaten zu erhalten.")
        .inputType(ProductRequest.class)
        .build();
    }

Grundsätzlich ist darauf zu achten, dass es Einschränkungen bei der
Verwendung von Function Tools gibt, die der aktuellen Spring AI
Dokumentation zu entnehmen sind.

### Tool Context

Die Tools haben keine direkte Abhängigkeit zur Anwendung. Sollen
allerdings zwischen Anwendung und Tools Informationen ausgetauscht
werden, kann dies über den **ToolContext** realisiert werden. Die
ToolContext-Klasse besteht aus einer einfachen Map\<String, Object\>.
Sollen Anwendungsdaten an die Tools übergeben werden, kann über die
Methode toolContext() des Chat Client eine Map\<String, Object\> an die
Tools übergeben werden. Der Chat Client erstellt daraus einen
ToolContext, der dann über die Parameterliste der Tool-Methoden oder
Tool-Functions übergeben wird.

Das folgende Beispiel zeigt die Initialisierung und Übergabe eines
ToolContext. In dem Beispiel wird die ID des aktuell angemeldeten
Maklers an alle Tools übergeben:

    var chatClient = builder
        .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
        .defaultSystem(prompt.getContents())
        .defaultTools(insuranceCustomerDetailsTool)
        .defaultToolContext(new HashMap<String, Object>(Map.of("brokerId", currentBroker.getId())))
        .build();

Das folgende Beispiel zeigt, wie der ToolContext in einem MaaT verwendet
wird:

    @Tool(name = "get_CustomerDetails", 
          description = "Stellt eine Anfrage an den Versicherungsservice, um Kundendaten zu erhalten.")
    public Customer getCustomerDetails(
            @ToolParam(required = true, description = "Name des Kunden") String name, 
            ToolContext context) {
        String brokerId = context.getContext().get("brokerId").toString();
        logger.info("Broker " + brokerId + " ruft Kundendaten ab");
        return insuranceCustomerService.getCustomerDetails(name, brokerId);
    }

Der ToolContext sollte sich auf technische Informationen beschränken,
die innerhalb einer Session notwendig sind. Typische Anwendungsfälle
sind User-Informationen, Feature Toggles,
Datenbank-Verbindungsinformationen oder Session-spezifische
Konfigurationsparameter.

Im Insurance Use Case wird der ToolContext verwendet, um Informationen
über den aktuell angemeldeten Makler an alle Tools zu übergeben. Dies
ermöglicht es, dass alle Tool-Aufrufe im Kontext des jeweiligen Maklers
ausgeführt werden, ohne die Makler-ID explizit in jeder Tool-Signatur
definieren zu müssen. Die Makler-ID wird bei der Initialisierung des
Chat Client aus der Session oder dem Security Context extrahiert und
über defaultToolContext() bereitgestellt. Dies ermöglicht eine saubere
Trennung zwischen fachlichen Parametern (die vom Chat Model bestimmt
werden) und technischen Kontextinformationen (die von der Anwendung
bereitgestellt werden).

### Tool Exception Handling

Es ist möglich, dass bei der Ausführung von einem oder mehreren Tools
ein Fehler auftritt und eine Exception geworfen wird. Die Fehler sollten
abgefangen und an den Chat Client durchgereicht werden, damit sie dort
bearbeitet werden können, um dem Anwender eine aussagekräftige
Fehlermeldung zu übergeben.

Wird in einem Tool eine Exception geworfen, wird diese standardmäßig
abgefangen und über eine ToolExecutionException weitergeworfen. Diese
wird von einem ToolExecutionExceptionProcessor abgefangen, in einen
String konvertiert und an das Chat Model zurückgegeben. Dieses Verhalten
ist für viele Anforderungen nicht geeignet, da der Chat Client in diesem
Fall keinen direkten Zugriff auf die Exception erhält und somit keine
aussagekräftige Fehlermeldung an den Anwender liefern kann.

Möchte man, dass die Exception weitergeworfen wird, um sie am Chat
Client zu bearbeiten, kann das Spring Boot Configuration Property
konfiguriert werden:

    spring:
      ai:
        chat:
          tool:
            default-tool-execution-exception-processor:
              always-throw: true

Alternativ kann eine Instanz von ToolExecutionExceptionProcessor als
Bean in den Application Context gelegt werden, um das Standardverhalten
zu überschreiben:

    @Bean
    ToolExecutionExceptionProcessor toolExecutionExceptionProcessor() {
        return new DefaultToolExecutionExceptionProcessor(true);
    }

Im Insurance Use Case wird die Exception weitergeleitet, sodass sie im
Controller über einen \@ExceptionHandler verarbeitet werden kann. Die
folgende Implementierung zeigt, wie eine InsuranceException abgefangen
und in eine strukturierte Fehlermeldung nach RFC 7807 (Problem Details)
umgewandelt wird:

    @ExceptionHandler(value = InsuranceException.class)
    ResponseEntity<ProblemDetail> handleException(InsuranceException exception) {
        
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("http://thinkenterprise.com/InsuranceException"));
        problemDetail.setTitle("Tool Execution Problem");
        problemDetail.setDetail(exception.getMessage());

        return new ResponseEntity<ProblemDetail>(problemDetail, HttpStatus.BAD_REQUEST);
    }

Dieser Ansatz ermöglicht es, fachliche Exceptions aus den Tools
strukturiert an den Client zurückzugeben. Der Client erhält eine
maschinenlesbare Fehlermeldung im Problem Details Format, die sowohl den
Fehlertyp, einen Titel als auch eine detaillierte Beschreibung enthält.
Dies ist besonders wichtig für Frontend-Anwendungen, die dem Benutzer
verständliche Fehlermeldungen anzeigen müssen.

### Tool Execution

Für die Ausführung der Tools ist der ToolCallingManager verantwortlich,
der von der Autoconfiguration erstellt wird. Das Property
internal-tool-execution-enabled ist standardmäßig auf true gesetzt.
Enthält die ChatResponse eine Tool Definition, die das auszuführende
Tool beschreibt, wird das Tool automatisch ausgeführt.

Möchte man die automatische Tool-Ausführung deaktivieren, kann das
folgende Property gesetzt werden:

    spring:
      ai:
        chat:
          client:
            internal-tool-execution-enabled: false

Wird das Property auf false gesetzt, ist der Anwender für die Ausführung
der Tools selbst verantwortlich. Dies macht Sinn, wenn die
Tool-Ausführung aus Sicherheitsgründen explizit kontrolliert werden
soll, wenn zusätzliche Validierungen vor der Tool-Ausführung
durchgeführt werden müssen, oder wenn die Tool-Ausführung in einen
eigenen Workflow mit Logging und Monitoring integriert werden soll. Im
Insurance Use Case wird die automatische Ausführung verwendet, da die
Tools bereits durch die Spring Security Konfiguration abgesichert sind.

## Qualität

Die Modellausgaben müssen überprüft werden, da Modelle nicht
deterministisch sind und halluzinieren können. Die Antworten der Modelle
sind möglicherweise nicht immer relevant, angemessen oder sachlich
richtig. Wir müssen die Qualität unseres AI-Assistenten, der die Modelle
verwendet, sicherstellen.

Die Qualität wird durch verschiedene Maßnahmen sichergestellt. **Tests**
ermöglichen es, das Verhalten des AI-Assistenten systematisch zu
überprüfen und Regressionen zu erkennen. **Evaluatoren** bewerten die
Qualität der Modellantworten automatisch anhand definierter Kriterien
wie Faktentreue oder Relevanz. Beide Ansätze ergänzen sich und bilden
zusammen die Grundlage für einen qualitativ hochwertigen AI-Assistenten.

### Test

Damit unter Spring Boot Tests durchgeführt werden können, sind die
folgenden Dependencies in das Build Management aufzunehmen:

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-testcontainers</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.testcontainers</groupId>
        <artifactId>junit-jupiter</artifactId>
        <scope>test</scope>
    </dependency>

Für das Testen von AI-Assistenten sind in aller Regel einige
Infrastrukturkomponenten wie ein Modell oder eine Vektordatenbank
notwendig, die oft über Container bereitgestellt werden. Daher
unterstützt Spring AI das bestehende Konzept der Testcontainer. Dazu ist
die folgende Dependency dem Build Management hinzuzufügen:

    <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-spring-boot-testcontainers</artifactId>
        <scope>test</scope>
    </dependency>

Damit werden eine Reihe von Verbindungskonfigurationen für spezielle
Infrastrukturkomponenten wie Qdrant über QdrantConnectionDetails oder
Ollama über OllamaConnectionDetails unterstützt. Diese
Verbindungsinformationen werden automatisch verwendet, wenn die
Annotation \@ServiceConnection gesetzt ist. Die folgende Implementierung
zeigt, wie die Vektordatenbank Qdrant und das Ollama Model für einen
Integrationstest bereitgestellt werden können. Die Annotation
\@Testcontainers aktiviert die automatische Verwaltung von Containern
für den Test, während \@Container einen spezifischen Container
definiert, der vor dem Test gestartet und nach dem Test automatisch
gestoppt wird:

    @SpringBootTest
    @Testcontainers
    class InsuranceApplicationTests {
        
        @Container
        @ServiceConnection
        static QdrantContainer qdrant = new QdrantContainer("qdrant/qdrant");
        
        @Container
        @ServiceConnection
        static OllamaContainer ollamaContainer = new OllamaContainer("ollama/ollama");

Die Testcontainer sorgen dafür, dass alle notwendigen
Infrastrukturkomponenten für die Tests zur Verfügung stehen, ohne dass
eine manuelle Konfiguration oder eine laufende Produktivumgebung
erforderlich ist. Dies ermöglicht es, Tests automatisiert in
CI/CD-Pipelines auszuführen und die Qualität kontinuierlich zu
überwachen.

Ein wichtiger Aspekt beim Testen von AI-Assistenten ist die
Reproduzierbarkeit der Tests. Da Modelle nicht deterministisch sind,
können die Antworten bei gleicher Eingabe variieren. Daher ist es
wichtig, Tests so zu gestalten, dass sie robuste Kriterien verwenden,
die nicht auf exakte Übereinstimmungen, sondern auf semantische
Ähnlichkeit und inhaltliche Korrektheit prüfen. Hier kommen
**Evaluatoren** ins Spiel, die automatisch bewerten, ob eine generierte
Antwort die gewünschten Qualitätskriterien erfüllt, auch wenn sie nicht
Wort für Wort mit einer erwarteten Antwort übereinstimmt.

### Evaluator

Für die Tests stellt Spring AI Evaluatoren zur Verfügung, um zu prüfen,
ob ein Test bestanden wurde oder nicht. Es gibt unterschiedliche
Ansätze, wie die Qualität einer Antwort überprüft werden kann.
Evaluatoren basieren darauf, wiederum ein Modell, ein sogenanntes
**Judge Model**, für die Überprüfung der Antwort zu verwenden. Weitere
Ansätze sind die Vorgabe von Musterausgaben, sogenannten **Golden
Answers**, gegen die geprüft wird, oder das Prüfen von Prompts,
beispielsweise System-Prompts, die zu einem bestimmten Verhalten des
Modells führen sollen.

Auch das LangChain Framework, das Vorlage für Spring AI ist, verwendet
für den Test von Modellen Evaluatoren. Mittlerweile gibt es sogar
spezialisierte Frameworks, die sich ausschließlich mit der Evaluierung
von AI-Modellen beschäftigen und erweiterte Evaluierungsmechanismen
bereitstellen.

Spring AI stellt für das Testen der Qualität des AI-Assistenten das
folgende API zur Verfügung. Das Interface Evaluator definiert die
Methode evaluate(), die von einer konkreten Evaluierung zu überschreiben
ist. Die EvaluationRequest-Klasse wird als Parameter an den Evaluator
übergeben und beinhaltet die Modelleingabe (das Prompt), die
Modellausgabe und optional zusätzliche Informationen wie den Kontext,
der zu dem Ergebnis geführt hat. Die EvaluationResponse-Klasse gibt das
Überprüfungsergebnis zurück. Die Methode isPass() gibt einen logischen
Wert zurück, und die Methode score() einen numerischen Wert zur
Bewertung des Ergebnisses, abhängig vom Anwendungsfall.

Spring AI stellt zwei Implementierungen des Evaluator-Interface zur
Verfügung, mit denen die Qualität des AI-Assistenten konkret überprüft
werden kann. Darüber hinaus können eigene Implementierungen des
Evaluators entwickelt werden.

Der FactCheckingEvaluator dient der Bewertung der sachlichen Richtigkeit
AI-generierter Antworten im bereitgestellten Kontext. Dieser Evaluator
hilft, Trugschlüsse in AI-Ausgaben zu erkennen und zu reduzieren, indem
er überprüft, ob eine bestimmte Aussage oder Behauptung durch den
bereitgestellten Kontext logisch gestützt wird.

Der RelevancyEvaluator bewertet die Relevanz AI-generierter Antworten im
bereitgestellten Kontext. Dieser Evaluator hilft beispielsweise bei der
Beurteilung der Qualität eines RAG-Flows, indem er ermittelt, ob die
Antwort des AI-Modells im Hinblick auf den abgerufenen Kontext für die
Benutzereingabe relevant ist.

Die folgende Implementierung zeigt Auszüge aus einem JUnit-Test, in dem
der über RAG bereitgestellte Kontext zu den Insurance Security Policies
gegen eine typische Frage \"Kann die vereinbarte Versicherungsdauer
verlängert werden?\" geprüft wird:

    @Test
    public void testRagLifeSecurityConditions(
            @Autowired InsuranceChatService insuranceChatService, 
            @Autowired ChatModel chatModel) {

        // Evaluator to check the relevancy of the answer
        RelevancyEvaluator relevancyEvaluator = new RelevancyEvaluator(ChatClient.builder(chatModel));
        
        // The test query
        String query = "Kann die vereinbarte Versicherungsdauer verlängert werden?";

        // Get response from our Chat Model with RAG Context information
        ChatResponse response = insuranceChatService.chatService(query);
        String responseContent = response.getResult().getOutput().getText();

        // Get RAG Data
        @SuppressWarnings("unchecked")
        List<Document> responseDocuments = 
            (List<Document>) response.getMetadata().get(RetrievalAugmentationAdvisor.DOCUMENT_CONTEXT);

        // Create Evaluation Request
        EvaluationRequest evaluationRequest = new EvaluationRequest(query, responseDocuments, responseContent);

        // Create relevancy Evaluation
        EvaluationResponse evaluationResponse = relevancyEvaluator.evaluate(evaluationRequest);

        // Execute relevancy Evaluation and Assertion
        Assertions.assertTrue(evaluationResponse.isPass());
    }

Das Ergebnis der Prüfung kann über die Methoden isPass() und score() der
EvaluationResponse abgefragt werden. Die Bewertung selbst erfolgt dabei
über die Implementierung des Evaluators, der die EvaluationResponse bei
der Prüfung durch evaluate() erzeugt und zurückgibt. Im Fall der beiden
genannten Implementierungen wird true/false über isPass() und 0 oder 1
über score() zurückgegeben.

Eine Einschränkung der aktuellen Spring AI Evaluatoren ist, dass die
score()-Methode nur binäre Werte (0 oder 1) zurückgibt. Wie im
vorherigen Kapitel erwähnt, sind Modellantworten jedoch oft nicht
eindeutig richtig oder falsch, sondern unterliegen Unschärfen. In der
Praxis möchte man häufig differenziertere Bewertungen vornehmen,
beispielsweise auf einer Skala von 0.0 bis 1.0, um die Qualität einer
Antwort nuancierter zu bewerten. Für solche Anwendungsfälle können
eigene Evaluator-Implementierungen entwickelt werden, die feinere
Abstufungen in der Bewertung ermöglichen.

## Compliance

Im Rahmen von Generative AI müssen Compliance-Anforderungen eingehalten
werden. Das gilt auch für AI-Assistenten, die Chat Models auf Grundlage
von Large Language Models verwenden. Compliance-Anforderungen werden
durch Regelwerke wie den EU AI Act oder die DSGVO vorgegeben und
umfassen ethische, gesetzliche und sicherheitsbezogene Aspekte.

Werden diese nicht eingehalten, können rechtliche Strafen oder
Reputationsverlust zu hohen finanziellen Schäden führen. Bereits große
Unternehmen wie Google mit Gemini oder Microsoft mit Bing haben durch
Compliance-Verstöße hohe Reputationsschäden erlitten.

Im Rahmen von Chat Models sorgen **Guardrails** für die Einhaltung der
Compliance. Guardrails sind Mechanismen, die sicherstellen, dass die
Ausgaben des Modells bestimmten Regeln entsprechen und dass das Modell
vor unzulässigen Anfragen geschützt wird. Sie können beispielsweise
verhindern, dass das Modell unangemessene Inhalte generiert, sensible
Informationen preisgibt oder gegen rechtliche Vorgaben verstößt.

Spring AI unterstützt die Implementierung von Guardrails durch Advisors,
bietet derzeit jedoch noch keine konkreten Implementierungen. Spring AI
verweist auf Open Source Projekte wie NeMo Guardrails von NVIDIA \[5\]
oder Guardrails AI \[6\]. Eine Integration bestehender Lösungen oder die
Unterstützung von Guardrails-as-a-Service ist sinnvoller als eine
Neuimplementierung.

Im Insurance Use Case sind Guardrails besonders wichtig, da sensible
Kundendaten verarbeitet werden und regulatorische Anforderungen streng
sind. Beispiele sind der Schutz personenbezogener Daten in Logs, die
Vermeidung diskriminierender Inhalte oder die Verhinderung
unqualifizierter medizinischer Aussagen.

## Monitoring

Das Monitoring ist eine kontinuierliche Überwachung einer Anwendung.
Werden bestimmte Schwellwerte überschritten, werden Maßnahmen ergriffen,
entweder manuell oder automatisch. Das Monitoring ist gerade für
AI-Anwendungen besonders wichtig, da für die Sicherung der Qualität und
Compliance AI-Anwendungen überwacht werden müssen.

Das Monitoring setzt voraus, dass sich die AI-Assistenten überwachen
lassen. **Observability** ist eine nicht-funktionale Anforderung an eine
AI-Anwendung, Informationen aus dem laufenden Betrieb bereitzustellen.

Es kann leicht festgestellt werden, wenn das Modell einen Fehler
zurückgibt. Es kann von Interesse sein, wie viele Token an das Modell
gesendet werden, um die Kosten zu überwachen oder zu optimieren. Es kann
in einem RAG Flow überprüft werden, welche Dokumente aus dem Vector
Store gelesen werden und wie viel Zeit dafür benötigt wurde.

Dazu gehören **Logs**, **Metriken** und **Tracing-Informationen**. Die
Informationen werden an spezialisierte Server übermittelt, wie Logstash
für Logs, Prometheus für Metriken oder Zipkin für Tracing. Diese
Informationen können dann über ein Monitoring-System wie Grafana
ausgewertet werden.

In Spring AI ist die Observability bereits eingebaut. Dazu wird das
Observability API von Micrometer verwendet. Es werden alle dafür
notwendigen Spring Beans von der Spring AI Autoconfiguration erzeugt. Es
werden für die Spring AI Module Chat Client, Chat Model, Advisors und
Vektor Datenbanken Logs, Metriken und Tracing-Informationen
bereitgestellt.

Um über Spring AI die Logs, Metriken und Tracing-Informationen für das
Monitoring bereitzustellen, ist der Spring Boot Actuator notwendig.
Dafür ist die folgende Dependency in das Build Management einzufügen:

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

Im Insurance Use Case soll über das Tracing ein Audit bereitgestellt
werden.

### Logging

Das Logging bezieht sich auf die Informationen der User Prompts und der
Anfragen und Antworten der Vektordatenbank. Es können die User Prompt
Informationen bei einer Anfrage über den Chat Client in das Log
geschrieben werden. Es kann die Antwort auf das User Prompt in das Log
geschrieben werden. Zudem können Anfragen an die Vektordatenbank
aufgezeichnet werden, sowie die Antworten von Anfragen an die
Vektordatenbank.

Das Logging ist per Default nicht eingeschaltet, da es sich um sehr
sensible Daten handelt. Über die folgenden Properties können die
beschriebenen Informationen für das Log freigegeben werden:

    spring:
      ai:
        chat:
          observations:
            log-completion: true
            log-prompt: true
            include-error-logging: true
          client:
            observations:
              log-prompt: true
        vectorstore:
          observations:
            log-query-response: true

Im Insurance Use Case werden die Log-Informationen aufgezeichnet, wie
die Konfiguration zeigt. In unserem Fall können die Logs direkt auf der
Konsole gelesen werden. Ein dedizierter Log Server ist nicht vorgesehen.
Wie ein Log Server, beispielsweise Logstash, über Spring Boot Actuator
integriert werden kann, ist in der Spring Boot Dokumentation ausführlich
beschrieben.

### Metriken

Im Insurance Use Case werden die Metriken über Prometheus erfasst. Dazu
ist das Build Management um die folgende Dependency zu erweitern:

    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
    </dependency>

Damit werden die Metriken über die URL
http://localhost:8080/actuator/prometheus dem Prometheus Server
bereitgestellt. Der Prometheus Server selbst wird über die
docker-compose.yaml gestartet. Prometheus ist über die prometheus.yaml
unter scrape_configs so konfiguriert, dass er die Metriken über die
genannte URL alle 5 Sekunden abholt. Es handelt sich also um einen
Pull-basierten Ansatz.

Die Metriken können über Prometheus angezeigt werden. Dazu ist die URL
http://localhost:9090/ zu verwenden. Es gibt zwei Timer-Metriken, die
einmal für den Chat Client und einmal für die Vektordatenbank angezeigt
werden. Die Timer-Metrik für den Chat Client beginnt mit
gen_ai_chat_client_operation\_\*, die Timer-Metrik für die
Vektordatenbank beginnt mit db_vector_client_operation\_\*.

Die Timer-Metrik selbst besteht im Wesentlichen aus drei Werten:

  --------------------------------------------------
  Metrik-Wert   Beschreibung
  ------------- ------------------------------------
  count         Anzahl der ausgeführten Operationen

  max           Maximale Ausführungszeit einer
                Operation

  sum           Gesamtzeit aller Operationen
  --------------------------------------------------

Diese Metriken werden für verschiedene Operationen angezeigt und
ermöglichen es, die Performance des Chat Client und der Vektordatenbank
zu überwachen. Beispielsweise kann über die count-Metrik die Anzahl der
Anfragen ermittelt werden, über die max-Metrik die langsamste Anfrage
identifiziert werden, und über die sum-Metrik die Gesamtlast berechnet
werden.

Im Insurance Use Case ist das Monitoring der Metriken besonders wichtig,
um die Token-Kosten zu überwachen und die Performance der
Vektordatenbank-Abfragen zu optimieren. Die Token-Kosten können über die
Metrik gen_ai_client_token_usage_total überwacht werden.

Es gibt eine Reihe weiterer Metriken, die von Spring AI bereitgestellt
werden. Die hier beschriebenen sind aus unserer Sicht die wichtigsten.

Über Prometheus können Schwellwerte definiert werden, die bei
Überschreitung Alerts auslösen. Dies ermöglicht eine proaktive
Überwachung des Systems und die frühzeitige Erkennung von
Performance-Problemen. Die Metriken können auch in Grafana visualisiert
werden, um Dashboards zu erstellen, die einen schnellen Überblick über
den Systemzustand bieten. Durch die kontinuierliche Überwachung der
Metriken können Trends erkannt und Kapazitätsplanungen durchgeführt
werden.

### Tracing

Im Insurance Use Case werden die Tracing-Informationen über Zipkin
erfasst. Dazu ist das Build Management um zwei Dependencies zu
erweitern. Im Gegensatz zu Prometheus, wo ein proprietäres Protokoll für
die Übertragung der Metriken verwendet wird, wird im Fall von Zipkin mit
OTLP ein Standardprotokoll der OpenTelemetry Organisation verwendet.
Dazu sind die folgenden Dependencies in das Build Management zu
übernehmen:

    <dependency>
        <groupId>io.opentelemetry</groupId>
        <artifactId>opentelemetry-exporter-zipkin</artifactId>
    </dependency>

    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-tracing-bridge-otel</artifactId>
    </dependency>

Der Spring Boot Actuator sendet alle Tracing-Informationen automatisch
an Zipkin. Es handelt sich also um einen Push-basierten Ansatz. Der
Tracing Server selbst wird über die docker-compose.yaml gestartet. Die
Tracing-Informationen können über Zipkin angezeigt werden. Dazu ist die
URL http://localhost:9411/ zu verwenden. Zipkin hat eine Oberfläche,
über die die Tracing-Informationen angezeigt werden können.

Ein **Span** ist die kleinste Einheit eines Trace. Er beschreibt einen
Arbeitsschritt, der betrachtet werden soll. Das kann ein HTTP-Aufruf,
ein Methodenaufruf, eine Datenbank-Query oder ähnliches sein. Jeder Span
kann eine Menge von **Tags** besitzen. Über Tags werden dem Span
abhängig vom Kontext Informationen beigelegt.

Wie bereits in der Einleitung beschrieben, werden in Spring AI für die
Spring AI Module Chat Client, Chat Model, Advisors und Vektor
Datenbanken Tracing-Informationen in Form von Spans und Tags
bereitgestellt. Dabei wird für jeden der genannten Spring AI Module ein
Span erstellt und über Tags Kontextinformationen hinterlegt. Die Tags
sind in der Spring AI Dokumentation beschrieben. Es wird an dieser
Stelle darauf verzichtet, auf die einzelnen Tags einzugehen, denn diese
sind abhängig vom Kontext und dem Problem, das man lösen möchte.

Im Insurance Use Case ermöglicht das Tracing die Nachvollziehbarkeit
aller Operationen im RAG-Flow. Es kann genau verfolgt werden, welche
Dokumente aus der Vektordatenbank abgerufen wurden, wie lange die
einzelnen Operationen gedauert haben und welche Advisors involviert
waren. Dies ist besonders wichtig für das Audit und die
Compliance-Anforderungen im Versicherungsbereich.

# AI Advanced Assistent

Dieses Kapitel behandelt erweiterte Themen, die über die Grundlagen
eines AI-Assistenten hinausgehen. Dazu gehören das Model Context
Protocol (MCP) für die standardisierte Bereitstellung von Fähigkeiten
sowie andere Modelle neben Large Language Models wie Embedding Models,
Image Models und Audio Models. Diese Themen werden nicht für jeden
AI-Assistenten benötigt und sind nicht Gegenstand des Trainings, bieten
aber wertvolle Erweiterungsmöglichkeiten für fortgeschrittene
Anwendungsfälle.

## MCP

MCP steht für Model Context Protocol. Das MCP Protokoll wurde von
Anthropic entwickelt und die Spezifikation ist unter \[7\] zu finden.
Über das MCP Protokoll werden Generative AI Modellen Fähigkeiten zur
Verfügung gestellt, die sie sonst nicht hätten. Fähigkeiten können
Tools, Ressourcen oder Prompts sein.

![Abbildung 6: MCP
Architektur](media/image6.png){alt="Ein Bild, das Schwarz, Dunkelheit enthält. KI-generierte Inhalte können fehlerhaft sein."
width="3.6527777777777777in" height="2.1944444444444446in"}

Der MCP Server stellt diese Fähigkeiten zur Verfügung. Die Kommunikation
mit dem Server kann remote synchron und asynchron über HTTP erfolgen.
Zudem besteht die Möglichkeit, einen Server in den Prozess einzubinden
und über IPC und Stdio mit dem MCP Server zu kommunizieren.

Für Java gibt es eine Implementierung des MCP Protokolls, das MCP Java
SDK. Spring AI integriert das MCP Java SDK von Anthropic in das Spring
Ökosystem.

Das ermöglicht Unternehmen, eigene MCP Server zu implementieren und
beispielsweise über die Cloud einer Vielzahl von AI-Assistenten im
Unternehmen bereitzustellen. Damit haben die Unternehmen die Kontrolle
über die Bereitstellung der Fähigkeiten. Auf der anderen Seite müssen
die Unternehmen, die AI-Assistenten entwickeln, keine Tools mehr
implementieren, um den Zugriff auf Ressourcen aus dem Unternehmen zu
ermöglichen.

Die drei Arten von Fähigkeiten unterscheiden sich wie folgt:

**Tools** sind Funktionen, die aufgerufen werden können. Auf Tools wird
über Tool-Definitionen zugegriffen, wie bereits im Tools-Kapitel
beschrieben.

**Ressourcen** sind Informationen, die für die Anreicherung des Kontexts
benötigt werden, beispielsweise für RAG. Auf die Ressourcen wird über
eine eindeutige URI zugegriffen.

**Prompts** sind Anfragen, die an das Modell geschickt werden können. Es
können Prompts zentral bereitgestellt werden. Über URIs können Prompts
aufgelistet und explizit auf Prompts zugegriffen werden.

Im Insurance Use Case könnten über einen MCP Server zentral Tools für
Kundendaten, Ressourcen für Versicherungspolicies und Prompts für
typische Anfragen bereitgestellt werden, die dann von verschiedenen
AI-Assistenten im Unternehmen genutzt werden können.

### Server

Im Insurance Use Case soll das im Tools-Kapitel gezeigte Tool
getCustomerDetails für die Bereitstellung der Kundeninformationen nicht
vom AI-Assistenten direkt bereitgestellt werden, sondern über einen MCP
Server. Der MCP Server wird über das synchrone HTTP-Protokoll
bereitgestellt. Dafür ist die folgende Dependency in das Build
Management einzufügen:

    <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-starter-mcp-server-webmvc</artifactId>
    </dependency>

Für andere Formen der Kommunikation sind andere Dependencies notwendig.

Der Starter stellt Spring Boot Properties spring.ai.mcp.server.\* zur
Verfügung, mit denen der MCP Server konfiguriert werden kann. Die
folgende Konfiguration zeigt die Spring Boot Properties für die
Implementierung des Insurance MCP Servers:

    spring:
      ai:
        mcp:
          server:
            name: webmvc-mcp-server
            version: 1.0.0
            type: SYNC
            instructions: "This server provides customer details tools"
            capabilities:
              tool: true
              resource: false
              prompt: false
              completion: false

Über das Property name wird der Name des MCP Servers festgelegt. Diese
Konfiguration ist wichtig, da der Client über diesen Namen auf den
Server zugreifen muss. Über das Property type legen wir die Art der
Kommunikation fest. In unserem Fall kommunizieren wir synchron (SYNC)
mit dem Client. Über das Property capabilities wird festgelegt, welche
Fähigkeiten der MCP Server dem MCP Client bereitstellen kann. Im
Insurance Use Case werden nur Tools bereitgestellt.

Die vier möglichen Capabilities sind:

- tool: Tools können bereitgestellt werden

- resource: Ressourcen für die Kontext-Anreicherung (z.B. RAG)

- prompt: Zentral bereitgestellte Prompts

- completion: Completion-Fähigkeiten

Im Insurance Use Case wird nur tool: true gesetzt, da ausschließlich das
Tool getCustomerDetails über den MCP Server bereitgestellt werden soll.
Die anderen Capabilities sind auf false gesetzt.

Der Vorteil dieser Architektur ist, dass das Tool zentral implementiert
und gewartet werden kann. Verschiedene AI-Assistenten im Unternehmen
können über den MCP Server auf das Tool zugreifen, ohne es selbst
implementieren zu müssen. Dies ermöglicht eine konsistente
Bereitstellung von Unternehmensdaten und reduziert den Wartungsaufwand
erheblich.

Wichtig zu beachten ist, dass das Tool selbst nicht direkt vom MCP
Server implementiert werden muss. Das Tool wird typischerweise vom
Unternehmen beispielsweise über eine Cloud mit REST APIs bereitgestellt,
und der MCP Server stellt lediglich die Tool-Beschreibung zur Verfügung.
Alternativ kann der MCP Server das Tool auch direkt über Stdio
bereitstellen.

### Client

Über den MCP Client kommunizieren wir mit dem MCP Server. Über den MCP
Client können wir auf die Fähigkeiten, beispielsweise Tools, des Servers
zugreifen. Für eine synchrone HTTP-Kommunikation müssen wir die folgende
Dependency in das Build Management einfügen:

    <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-starter-mcp-client</artifactId>
    </dependency>

Für andere Formen der Kommunikation sind andere Dependencies notwendig.

Zudem müssen wir über die Spring Boot Properties spring.ai.mcp.client.\*
konfigurieren, mit welchem MCP Server wir wie kommunizieren wollen. Die
folgende Konfiguration zeigt die Einstellungen für den Insurance Use
Case:

    spring:
      ai:
        mcp:
          client:
            enabled: true
            name: insurance-company-tool-mcp-client
            version: 1.0.0
            request-timeout: 30s
            type: SYNC
            sse:
              connections:
                webmvc-mcp-server:
                  url: http://localhost:8081

Über das Property type legen wir, wie bei dem MCP Server, die Art der
Kommunikation fest. Im Insurance Use Case kommunizieren wir synchron
(SYNC). Über das Property connections konfigurieren wir die Verbindung
zum Server.

Da es mehrere MCP Server geben kann, mit denen ein MCP Client
kommuniziert, ist als erstes der Name des Servers festzulegen, für den
man die Verbindungsinformationen festlegen möchte. Im Insurance Use Case
haben wir den Namen webmvc-mcp-server konfiguriert. Unterhalb des Namens
des Servers können nun weitere Verbindungsinformationen konfiguriert
werden. Das wichtigste Property ist url, das die Adresse des MCP Servers
festlegt, in unserem Fall http://localhost:8081.

Für jede Connection wird ein McpClient erstellt, in unserem Fall ein
McpSyncClient. Auf diese List\<McpClient\> kann über den Application
Context zugegriffen werden. Allerdings erfolgt der Zugriff auf den MCP
Server nicht direkt, sondern indirekt über die Ressourcen,
beispielsweise die Tools.

Die Übertragung der Tools, die vom MCP Server bereitgestellt werden,
erfolgt automatisch. Die Instanz ToolCallbackProvider wird automatisch
mit den Tool-Definitionen befüllt. Über den ChatClient.Builder können
diese Tool-Definitionen dann an das Chat Model übergeben und von diesem
aufgerufen werden. Der ToolCallbackProvider liefert alle ToolCallbacks,
die von der AI-Anwendung bereitgestellt werden. In unserem Fall handelt
es sich dabei um SyncMcpToolCallbacks. Ruft das Modell das Tool über die
Methode call() auf, wird über den McpSyncClient automatisch das Tool auf
dem MCP Server aufgerufen und das Ergebnis des Tool-Aufrufs an das
Modell zurückgegeben. Wir müssen dazu nichts weiter tun.

## Models

Es gibt neben den Large Language Models noch andere Modelle wie Image
Models, Audio Models und Embedding Models. Diese anderen Modelle werden
von Spring AI derzeit nicht so umfassend unterstützt wie LLMs, werden
aber hier kurz vorgestellt. Beispiele für die Verwendung dieser Modelle
sind nicht Teil dieses Kapitels.

### Image Model

Das Image Model ermöglicht die Ausgabe von Bildern. Das Image Model
erhält einen Text, der das Aussehen des gewünschten Bildes beschreibt,
und die Ausgabe ist das gewünschte Bild. Das Image Model ist ein Spring
AI Content Model und baut auf dem Spring AI Generic Model auf.

Es gibt, ähnlich analog zum Chat Model, ein ImageModel, ImageOptions,
ImagePrompt, ImageMessage, ImageResponse, ImageGeneration und weitere
Klassen. Die Struktur folgt also dem bekannten Pattern aus dem Chat
Model.

Es werden die Model Provider OpenAI, Stability AI, Baidu\'s QianFan AI
Platform und CogView ZhiPuAI unterstützt. Das Angebot ist somit noch
recht klein im Vergleich zu den Chat Models.

Neben dem Image Model gibt es allerdings nicht analog zum Chat Client
einen Image Client. Das liegt daran, dass das Image Model noch nicht so
gut unterstützt wird wie das Chat Model. Möglicherweise wird es in
Zukunft auch einen Image Client geben, der die Verwendung von Image
Models vereinfacht und ähnliche Komfortfunktionen bietet wie der Chat
Client.

Das Image Model wird in diesem Buch nicht weiter behandelt. Für weitere
Informationen zur Verwendung von Image Models sei auf die Spring AI
Dokumentation verwiesen, die detaillierte Beispiele für die
unterstützten Provider enthält.

Im Insurance Use Case könnten Image Models beispielsweise verwendet
werden, um Visualisierungen von Versicherungspolicies zu erstellen oder
Schadensfälle grafisch darzustellen. Allerdings ist die Implementierung
aufgrund der eingeschränkten Unterstützung derzeit aufwendiger als bei
Chat Models.

### Voice Model

Beim Voice Model kann man zwei Anwendungsfälle unterscheiden:

**Audio Transcription** ist das Erzeugen von Text aus einem Audio, auch
Transkription genannt. Hier wird lediglich das Model von OpenAI
unterstützt. Daher gibt es auch keine allgemeine Implementierung eines
Content Models, sondern lediglich eine konkrete Implementierung für
OpenAI, beispielsweise OpenAiAudioTranscriptionModel. Auch hier gibt es,
wie beim Image Model, keinen Audio Client.

**Text-to-Speech** ist der zweite Anwendungsfall und beschreibt das
Erzeugen eines Audios aus einem gegebenen Text. Auch hier wird lediglich
das Model von OpenAI unterstützt. Daher gibt es auch keine allgemeine
Implementierung eines Content Models, sondern lediglich eine konkrete
Implementierung für OpenAI, beispielsweise OpenAiAudioSpeechModel. Auch
hier gibt es, wie beim Image Model, keinen Audio Client.

Die fehlende Abstraktion durch ein allgemeines Content Model und das
Fehlen eines Audio Clients zeigen, dass Voice Models in Spring AI noch
nicht so ausgereift sind wie Chat Models. Die Unterstützung beschränkt
sich derzeit auf OpenAI-spezifische Implementierungen, was die
Flexibilität einschränkt.

Das Voice Model wird in diesem Buch nicht weiter behandelt. Für weitere
Informationen zur Verwendung von Voice Models sei auf die Spring AI
Dokumentation verwiesen, die detaillierte Beispiele für die
OpenAI-Implementierungen enthält.

Im Insurance Use Case könnten Voice Models beispielsweise verwendet
werden, um Kundenanrufe automatisch zu transkribieren oder um Antworten
des AI-Assistenten als gesprochene Ausgabe bereitzustellen. Dies würde
die Barrierefreiheit erhöhen und neue Interaktionsmöglichkeiten
schaffen. Allerdings ist die Implementierung aufgrund der
eingeschränkten Unterstützung und fehlenden Abstraktion derzeit
aufwendiger als bei Chat Models.

# Literaturverzeichnis {#literaturverzeichnis-1}

\[1\] Craig Walls: \"Spring AI in Action\", Manning Publications (in
preparation)

\[2\] Thomas Vitale: \"Modular RAG Implementation\",
<https://github.com/ThomasVitale/modular-rag>

\[3\] Tavily: \"AI Search API for RAG\", <https://www.tavily.com/>

\[4\] Yunfan Gao et al.: \"Modular RAG: Transforming RAG Systems into
LEGO-like Reconfigurable Frameworks\", arXiv preprint

\[5\] NVIDIA: \"NeMo Guardrails\",
<https://github.com/NVIDIA/NeMo-Guardrails>

\[6\] Guardrails AI: \"Guardrails AI Framework\",
<https://www.guardrailsai.com/>

\[7\] Anthropic: \"Model Context Protocol Specification\",
<https://github.com/modelcontextprotocol>
