# Spring AI 
https://github.com/spring-projects/spring-ai
https://spring.io/projects/spring-ai

# Insurance Case 
**Tool**: AI Assistent 
**Branche**: Insurance 
**Akteure**: Kunde, Makler, Fachabteilung 
**Use Case**: Makler möchte den Kunden schnell beraten, ohne die Fachabteilung einzubeziehen 
**Scope**: Informationen 
**Unsuitable**: Entscheidungen, Durchführung 


# Chat  
- UI 
- Controller 
- Service 

# Chat Client 
- Chat API 
- Chat Advisor 
- Build Management, Autoconfiguration (Chat Builder)
- Customizing? 


# Model 
- Model API 
- Modelle: Open AI, Ollama, .... 
- Build Management, Autoconfiguration (Model)
- Customizing 

# Prompt 
- System Prompt API (System Prompt, User Prompt)
- Context, PromptTemplate 
- **Practical Case**: 
- *Question*: Hallo? 
- *Answer*: Hallo! Wie kann ich Ihnen helfen? 
- *Question*: Wieviele Versicherungskunden haben wir? 
- *Answer*: Keine Information verfügbar  

# Tools
- Realtime, Input & Output 
- Tool API (Method, Function)
- **Practical Case** 
- *Question*: Kannst Du mir die Kundendaten von Michael Schäfer geben?  
- *Answer*: Kundendaten von Michael Schäfer ... 
- *Question*: Kannst Du mir die Produktdaten von Michael Schäfer geben?  
- *Answer*: Produktdaten von Michael Schäfer ... 
- Show No Tool Result 
- Show Exception Handling 

# Advisor 
- Advisor API 
- Memory Advisor 
- Chat History 
- **Practical Case**: 
- *Question*: Welche Vertrags-ID hat Michael Schäfer? 
- *Answer*: Kundennummer: IN2000 


# RAG 
- Enterprise Context (Big Data) 
- RAG - Paper 
- Vector API 
- ETL API 
- **Practical Case** 
- *Question*: Kann die vereinbarte Versicherungsdauer verlängert werden?
- *Answer*: Ja, die vereinbarte Versicherungsdauer kann auf Anfrage verlängert werden.  

# Test
- Integration Test 
- Evaluation API  

# Observation 
- Micrometer API 
- Instrumentation Chat Client, Model, Advisor, Vectro etc. 
- Metrics http://localhost:9090/ Count, Usage 
- Trace http://localhost:9411/ spring-ai-assistent: http post /chat durchgehen 