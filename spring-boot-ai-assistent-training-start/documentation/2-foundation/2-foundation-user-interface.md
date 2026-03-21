# Spring Boot AI Foundation User Interface Training  
In dieser Einheit lernen wir, wie eine einfache Benutzeroberflaeche fuer den AI-Assistenten aufgebaut wird, wie sie mit dem Spring-Boot-Backend zusammenspielt und welche UI-Strategien von statischer Seite bis Rich Client sinnvoll sind.  

## Domain
Die fachliche Herausforderung in diesem Schritt ist die klare Trennung zwischen Benutzeroberflaeche und AI-Logik. Die UI soll eine einfache, nachvollziehbare Interaktion ermoeglichen, waehrend die eigentliche Modellkommunikation sauber ueber Backend-Endpunkte in Spring Boot und Spring AI erfolgt.


## Architecture  
Fuer den Einstieg ist eine schlanke Architektur sinnvoll: Eine Web-UI sendet Requests an REST-Endpunkte, und das Backend kapselt den Zugriff auf Prompt-, Model- und Chat-Client-Funktionen. Das passt zur im Buch beschriebenen Trennung zwischen allgemeiner Web-Anwendung (Spring Boot) und AI-spezifischer Logik (Spring AI).

```text
+--------------------+      HTTP/JSON       +--------------------------+
| Browser / Benutzer | <------------------> | Spring Boot Web API      |
| chat.html / SPA    |                      | Controller / Service      |
+--------------------+                      +------------+-------------+
                                                         |
                                                         | Spring AI
                                                         v
                                              +--------------------------+
                                              | ChatClient / Model API   |
                                              | z. B. Ollama             |
                                              +--------------------------+
```

Bei der UI gibt es grundsaetzlich drei Wege: Erstens eine selbst gebaute, statische Seite direkt in Spring Boot, zweitens ein eigener Rich Client (z. B. React, Vue oder Angular) und drittens wiederverwendbare Chat-UI-Bausteine aus bestehenden Frameworks. Fuer das Training starten wir bewusst mit der einfachen statischen Variante, weil sie die technischen Grundlagen transparent macht.


Moegliche UI-Varianten:

- Statische HTML-Seiten direkt aus Spring Boot (`src/main/resources/static`): [Spring Boot Static Content](https://docs.spring.io/spring-boot/reference/web/servlet.html#web.servlet.spring-mvc.static-content)
- Serverseitige Templates mit Thymeleaf: [Thymeleaf](https://www.thymeleaf.org/)
- Single-Page-App mit React: [React](https://react.dev/)
- Single-Page-App mit Vue.js: [Vue](https://vuejs.org/)
- Single-Page-App mit Angular: [Angular](https://angular.dev/)
- Wiederverwendbare Chat-UI-Komponenten (Beispiel): [Bot Framework Web Chat](https://github.com/microsoft/BotFramework-WebChat)


## Libraries  
Fuer diese Trainingsseite sind keine zusaetzlichen UI-Bibliotheken notwendig. Der bereits vorhandene `spring-boot-starter-web` reicht aus, um statische Inhalte auszuliefern und HTTP-Endpunkte fuer die UI bereitzustellen.

## Implementation 
In diesem Schritt haben wir die statische Web-Seite generieren lassen und nicht von Grund auf selbst implementiert. Sie liegt unter `src/main/resources/static/chat.html` und wird vom Spring-Boot-Web-Stack direkt ausgeliefert.

```text
src/main/resources/static/
    index.html
    chat.html
```
## Configuration 
Wir verwenden eine zentrale CORS-Konfiguration, damit Freigaben fuer Origins und Methoden an einer Stelle gepflegt werden und nicht ueber einzelne `@CrossOrigin`-Annotationen im Code verteilt sind. Fuer produktive Szenarien sollten konkrete Origins statt `*` freigegeben werden.

Beispiel fuer eine zentrale CORS-Konfiguration in Spring MVC:

```java
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST");
    }
}
```

Die Basiskonfiguration bleibt weiterhin in `application.yaml`:

```yaml
spring:
  application:
    name: spring-ai-assistent
```

## Test 
Ja, die Anwendung kann bereits sinnvoll getestet werden, auch ohne eigenen Chat-Controller. In diesem Schritt testen wir gezielt die statische Auslieferung der HTML-Seite und die Frontend-Basisfunktion im Browser.

Eine einfache Teststrategie fuer die HTML-Seite:

1. Build ausfuehren: `mvn clean package`
2. Anwendung starten: `mvn spring-boot:run`
3. Browser oeffnen und Seite aufrufen (z. B. `http://localhost:8080`)
4. UI-Interaktion pruefen (Eingaben, Buttons, Antwortdarstellung)
5. Browser-DevTools pruefen (Netzwerk-Requests, HTTP-Status, CORS)


