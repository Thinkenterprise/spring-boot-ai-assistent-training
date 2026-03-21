# Spring Boot AI <Topic> <Stroke> Training  
Allgemeine Beschreibung des Trainings 

## Domain
Was ist die fachliche Herausforderung 


## Architecture   
Wie sieht die Architektur aus 


## Libraries  
Welche Dependencies muss ich einfügen 
```xml
<properties>
        <java.version>21</java.version>
        <spring-ai.version>1.1.2</spring-ai.version>
        <spring-boot.version>3.5.0</spring-boot.version>
    </properties>
```

## Implementation 
Welchen Code muss ich einfügen 
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hallo Welt!");
    }
}
```
## Confgiuration 
Welche Konfiguration ist notwendig  
```yaml
spring:
  application:
    name: spring-ai-assistent
```

## Test 
Wie Teste ich das, was ich geschrieben habe 

``mvn clean package``


