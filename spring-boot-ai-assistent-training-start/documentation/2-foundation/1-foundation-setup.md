# Spring Boot AI Foundation Setup Training  
Allgemeine Beschreibung des Trainings 


## Goal 
Ziel dieser Einheit ist es, eine lauffaehige Spring-AI-Anwendung auf Basis von Spring Boot aufzusetzen und die dafuer notwendigen Abhaengigkeiten, Build-Konfigurationen und Basiseinstellungen zu verstehen. 


## Architecture 
Abbildung 1.1 zeigt, dass eine Spring Boot AI Anwendung aus Spring Boot und Spring AI besteht. Spring Boot (z. B. 3.5.0) und Spring AI (z. B. 1.1.2) sind jeweils über ihre Projektseiten dokumentiert: [Spring Boot](https://spring.io/projects/spring-boot) und [Spring AI](https://spring.io/projects/spring-ai). Beide Projekte stehen nebeneinander, wobei Spring AI den Starter-Mechanismus von Spring Boot nutzt.  

## Libraries
Wir verwenden Java 21 als Long‑Term‑Support-Version; Spring Boot 3.5.0 ist auf Java 21 ausgelegt und liefert aktuelle Verbesserungen und Bugfixes. Spring AI 1.1.2 baut auf Spring Boot auf und stellt zusätzliche AI‑Starter bereit, die sich nahtlos in das Spring‑Ökosystem integrieren.

```xml
    <properties>
        <java.version>21</java.version>
        <spring-ai.version>1.1.2</spring-ai.version>
        <spring-boot.version>3.5.0</spring-boot.version>
    </properties>
```

Spring Boot unterstützt sowohl Maven als auch Gradle als Build-Tools und liefert dafür passende Starter und Build‑Plugins. Seit Spring Boot 3.5 wird die Parent‑BOM (`spring-boot-starter-parent`) empfohlen, da sie einheitliche Dependency‑Versionen und sinnvolle Standardkonfigurationen bereitstellt.  

```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>${spring-boot.version}</version>
        <relativePath />
    </parent>
```

Der Starter `spring-boot-starter-web` bringt die notwendigen Bausteine fuer eine klassische Webanwendung mit, insbesondere den eingebetteten Webserver, Spring MVC und die Standardkonfiguration fuer HTTP-Endpunkte.

```xml
   <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```

Spring AI bietet sowohl für Maven als auch für Gradle ein BOM (Bill Of Materials), das die passenden Versionen der AI‑Abhängigkeiten zusammenfasst. Mit dem BOM‑Konzept stellt man sicher, dass alle Spring‑AI‑Artefakte kompatible und getestete Versionskombinationen verwenden, ohne jede Abhängigkeit einzeln festlegen zu müssen.


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

Als Beispiel fuer den naechsten Schritt kann ein AI-Starter wie `spring-ai-starter-model-ollama` eingebunden werden, um spaeter ein lokales Sprachmodell ueber Ollama aus der Anwendung heraus anzusprechen.

```xml
   <dependencies>
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-starter-model-ollama</artifactId>
        </dependency>
    </dependencies>
```


## Implementation 
Der Einstiegspunkt der Anwendung ist eine klassische Spring-Boot-Startklasse, die den Application Context initialisiert und damit die gesamte Anwendung startet. 
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
Die Basiskonfiguration wird in `application.yaml` gepflegt; hier werden zunaechst der Anwendungsname und spaeter weitere AI-, Modell- und Infrastrukturparameter definiert.
```yaml
spring:
  application:
    name: spring-ai-assistent
``` 

## Test 

1. Anwendung Starten 
2. `URL: localhost:8080` 
3. Es wird ein Text mit "Erstelle eine Chat UI" angezeigt.


