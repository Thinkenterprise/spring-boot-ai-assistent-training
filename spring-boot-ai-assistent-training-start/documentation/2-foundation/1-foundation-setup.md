# Spring Boot AI Foundation Setup Training

In dieser Einheit wird eine lauffähige Spring AI Anwendung auf Basis von Spring Boot aufgesetzt. Als Grundlage dienen die bekannten Spring Boot Konzepte: Starter, Autokonfiguration und Properties.


## Goal

Ziel dieser Einheit ist es, eine lauffähige Spring-AI-Anwendung auf Basis von Spring Boot aufzusetzen und die dafür notwendigen Abhängigkeiten, Build-Konfigurationen und Basiseinstellungen zu verstehen.


## Architecture

**Spring AI** ist ein neues Projekt innerhalb des Spring-Ökosystems und erweitert dieses um Funktionen zur Entwicklung KI-gestützter Anwendungen. Es baut konzeptionell auf den bewährten Mechanismen von *Startern*, *Autokonfiguration* und *Properties* auf.

Eine KI-Assistent-Anwendung nutzt **Spring Boot** für allgemeine Anforderungen wie die Bereitstellung von Web-APIs. Für AI-spezifische Funktionalitäten – beispielsweise die Integration von Sprachmodellen oder Prompt-Verarbeitung – kommt **Spring AI** zum Einsatz. Dadurch wird eine klare Trennung zwischen allgemeinen Anwendungsbelangen und AI-spezifischer Logik erreicht.

Spring AI wurde 2023 als Reaktion auf den ChatGPT-Hype im Spring-Ökosystem aufgesetzt (Lead: Dr. Mark Pollack und Christian Tzolov). Es lässt sich von Python-Projekten wie LangChain und LlamaIndex inspirieren, ist aber **keine Portierung** dieser Projekte – es wurde von Grund auf für das Java- und Spring-Ökosystem entwickelt.

> **Hinweis:** Eine Alternative zu Spring AI im Java-Umfeld ist **LangChain4j**. In diesem Training verwenden wir ausschließlich Spring AI.

Spring Boot (z. B. 3.5.x) und Spring AI (z. B. 1.1.4) sind jeweils über ihre Projektseiten dokumentiert: [Spring Boot](https://spring.io/projects/spring-boot) und [Spring AI](https://spring.io/projects/spring-ai). Spring AI nutzt dabei den Starter-Mechanismus von Spring Boot.


## Libraries

Wir verwenden Java 21 als Long-Term-Support-Version. Spring Boot 3.5.x ist auf Java 21 ausgelegt. Spring AI 1.1.4 baut auf Spring Boot auf und stellt zusätzliche AI-Starter bereit.

```xml
    <properties>
        <java.version>21</java.version>
        <spring-ai.version>1.1.4</spring-ai.version>
        <spring-boot.version>3.5.0</spring-boot.version>
    </properties>
```

Die Parent-BOM (`spring-boot-starter-parent`) stellt einheitliche Dependency-Versionen und sinnvolle Standardkonfigurationen bereit:

```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>${spring-boot.version}</version>
        <relativePath />
    </parent>
```

Der Starter `spring-boot-starter-web` bringt die notwendigen Bausteine für eine klassische Webanwendung mit – insbesondere den eingebetteten Webserver, Spring MVC und die Standardkonfiguration für HTTP-Endpunkte:

```xml
   <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```

Spring AI bietet ein BOM (Bill Of Materials), das kompatible Versionskombinationen aller Spring-AI-Artefakte zusammenfasst:

```xml
     <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.ai</groupId>
                <artifactId>spring-ai-bom</artifactId>
                <version>${spring-ai.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

Für den Zugriff auf ein lokales Sprachmodell über Ollama wird folgender AI-Starter eingebunden:

```xml
   <dependencies>
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-starter-model-ollama</artifactId>
        </dependency>
    </dependencies>
```


## Implementation

Der Einstiegspunkt der Anwendung ist eine klassische Spring-Boot-Startklasse:

```java
package com.thinkenterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }
}
```

## Configuration

Die Basiskonfiguration wird in `application.yaml` gepflegt:

```yaml
spring:
  application:
    name: spring-ai-assistent
```


## Test

1. Anwendung starten: `mvn spring-boot:run`
2. Browser öffnen: `http://localhost:8080`
3. Es wird ein Hinweistext mit „Erstelle eine Chat UI" angezeigt
