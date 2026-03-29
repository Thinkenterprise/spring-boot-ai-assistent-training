# Spring Boot AI Foundation User Interface Training

In dieser Einheit lernen wir, wie eine einfache Benutzeroberfläche für den AI-Assistenten aufgebaut wird, wie sie mit dem Spring-Boot-Backend zusammenspielt und welche UI-Strategien von einer statischen Seite bis hin zu einem Rich Client sinnvoll sind.


## Domain

Die fachliche Herausforderung in diesem Schritt ist die klare Trennung zwischen Benutzeroberfläche und AI-Logik. Die UI soll eine einfache, nachvollziehbare Interaktion ermöglichen, während die eigentliche Modellkommunikation sauber über Backend-Endpunkte in Spring Boot und Spring AI erfolgt.


## Architecture

In dieser Einheit betrachten wir ausschließlich das **User Interface**. Die UI ist für Eingabe, Ausgabe und Nutzerführung verantwortlich und nutzt den Controller als klaren Integrationspunkt zum Backend.

```text
      **User Interface**
      **(Web UI)**
          ↓
      Controller
      (REST Endpoints)
          ↓
      Service
      (Business Logic)
          ↓
      Chat Client
      (Abstraction über Content Model API)
          ↓
      Chat Model
      (Provider-spezifische Implementierung)
```

Für die UI gibt es grundsätzlich drei Wege: erstens eine selbst gebaute, statische Seite direkt in Spring Boot, zweitens ein eigener Rich Client (z. B. React, Vue oder Angular) und drittens wiederverwendbare Chat-UI-Bausteine aus bestehenden Frameworks. Für das Training starten wir bewusst mit der einfachen statischen Variante, weil sie die technischen Grundlagen transparent macht.

Mögliche UI-Varianten:

- Statische HTML-Seiten direkt aus Spring Boot (`src/main/resources/static`): [Spring Boot Static Content](https://docs.spring.io/spring-boot/reference/web/servlet.html#web.servlet.spring-mvc.static-content)
- Serverseitige Templates mit Thymeleaf: [Thymeleaf](https://www.thymeleaf.org/)
- Single-Page-App mit React: [React](https://react.dev/)
- Single-Page-App mit Vue.js: [Vue](https://vuejs.org/)
- Single-Page-App mit Angular: [Angular](https://angular.dev/)
- Wiederverwendbare Chat-UI-Komponenten (Beispiel): [Bot Framework Web Chat](https://github.com/microsoft/BotFramework-WebChat)


## Libraries

Für diese Trainingseinheit sind keine zusätzlichen UI-Bibliotheken notwendig. Der bereits vorhandene `spring-boot-starter-web` reicht aus, um statische Inhalte auszuliefern und HTTP-Endpunkte für die UI bereitzustellen.


## Implementation

In diesem Schritt haben wir die statische Web-Seite generieren lassen und nicht von Grund auf selbst implementiert. Sie liegt unter `src/main/resources/static/chat.html` und wird vom Spring-Boot-Web-Stack direkt ausgeliefert.

```text
src/main/resources/static/
    index.html
    chat.html
```

Die statische HTML-Seite kommuniziert mit einem REST-Endpunkt im Backend, um die Chat-Anfragen zu verarbeiten. Der Controller reicht die Anfrage an den Service weiter und gibt die Antwort des AI-Modells zurück:

```java
@PostMapping
public Map<String, String> chat(@RequestBody Map<String, String> payload) {
    String message = payload.get("message");
    var result = insuranceChatService.chatService(message);
    return Map.of("reply", result);
}
```

Diese Methode empfängt die Benutzer-Eingabe als JSON, leitet sie an den Service weiter und gibt die Antwort des AI-Modells als JSON-Antwort an die UI zurück.

## Test

Im folgenden wollen wir nun prüfen, ob unser User Interface funktioniert. 

1. Build ausführen: `mvn clean package`
2. Anwendung starten: `mvn spring-boot:run`
3. Browser öffnen und Seite aufrufen: `http://localhost:8080`
4. UI-Interaktion prüfen (Eingaben, Buttons, Antwortdarstellung)
