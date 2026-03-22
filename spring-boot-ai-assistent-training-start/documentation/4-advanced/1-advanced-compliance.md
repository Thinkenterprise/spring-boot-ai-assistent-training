# Advanced Compliance

## Ziel

Ziel dieser Einheit ist es, die Bedeutung von **Compliance** im Kontext von Generativer AI zu verstehen und zu zeigen, wie Spring AI die Einhaltung von Compliance-Anforderungen unterstützt.


## Motivation

Im Rahmen von Generativer AI müssen Compliance-Anforderungen eingehalten werden. Diese werden durch Regelwerke wie den **EU AI Act** oder die **DSGVO** vorgegeben und umfassen ethische, gesetzliche und sicherheitsbezogene Aspekte.

Werden Compliance-Anforderungen nicht eingehalten, können rechtliche Strafen oder Reputationsverlust zu erheblichen finanziellen Schäden führen. Bekannte Beispiele sind Compliance-Verstöße bei Google (Gemini) und Microsoft (Bing), die hohe Reputationsschäden verursacht haben.

Im Insurance Use Case sind Compliance-Anforderungen besonders relevant, da sensible Kundendaten verarbeitet werden und regulatorische Anforderungen streng sind.


## Guardrails

Im Rahmen von Chat Models sorgen **Guardrails** für die Einhaltung der Compliance. Guardrails sind Mechanismen, die sicherstellen, dass:

- das Modell keine **unangemessenen Inhalte** generiert
- das Modell keine **sensiblen Informationen** preisgibt
- das Modell keine **regulatorischen Vorgaben** verletzt
- das Modell vor **Prompt Injection** und anderen Angriffen geschützt wird

**Beispiele im Insurance Use Case:**

- Schutz personenbezogener Daten in Logs
- Vermeidung diskriminierender Inhalte bei der Beratung
- Verhinderung unqualifizierter medizinischer Aussagen
- Sicherstellung, dass Antworten auf Versicherungsthemen beschränkt bleiben


## Spring AI – Implementierung

Spring AI unterstützt die Implementierung von Guardrails über **Advisors**. Advisors agieren als Interceptoren im Request-Response-Zyklus und können Anfragen und Antworten prüfen und filtern.

Spring AI verweist für konkrete Guardrail-Implementierungen auf etablierte Open Source Projekte:

- **NeMo Guardrails** von NVIDIA
- **Guardrails AI**

> **Hinweis:** Eine Integration bestehender Lösung oder die Nutzung von **Guardrails-as-a-Service** ist sinnvoller als eine Neuimplementierung. Spring AI bietet hierfür die Grundlage über das Advisor-Konzept.


## Test

1. System Prompt so formulieren, dass der AI-Assistent ausschließlich Versicherungsfragen beantwortet
2. Versuchen, den Assistenten mit themenfremden Fragen zu testen (z. B. *„Was ist die Hauptstadt von Frankreich?"*)
3. Prüfen, ob der Assistent korrekt ablehnt oder umleitet
4. Sensible Kundendaten aus dem Log überprüfen und bei Bedarf Logging-Konfiguration anpassen
