# Allgermein 
Das Thema System Prompt ist recht umfangreich und hat einen großen Einfluss auf das Verhalten von Chat Models. 
Es ist daher wichtig sich eingehend mit dem Thema zu beschäftigen und einen Experten im Team dazu aufzubauen. 

# Sources
Im folgenden werden Quellen zum Thema System Prompt genannt 

## Books 
- Das Buch **Mastering System Prompts for LLMs** ist wirklich sehr umfangreich und zeigt alle Aspekte im Umgang mit System Prompts 

## Samples 
- Das Github Projekt [Awesome ChatGPT Prompts](https://github.com/f/awesome-chatgpt-prompts)
- Directory [Prompts Chat](https://prompts.chat/)
- Commercial Directory [Prompts Base](https://promptbase.com/) 

# Template 
- Ein Template für das Unternehmen, das Team, dass den grundsätzlichen Aufbau eines Templates vorgibt siehe systemPromptTemplate.yaml
- Das Template sollte in einem Format wie JSON, YAML erstellt werden 
- Das Template sollte Regeln für die Formatierung von Topics topic:, Placeholder{{}}, Documentation # etc. folgen 
- Das Termplate sollte versioniert werden 

# Topics 
- Es sollte klar geregelt werden, welche Topics in das Template sollen 
- Es sollte klar geregelt werden wie unterhalb der Topics die Formulierungen gestaltet werden. Bsplw. wie genau verbote formuliert werden. 
- Für die Formulierungen werden Best Practice erstellt, die im Template als Beispiel abgebildet werden siehe Anlage Tools 

# Instanz 
- Für eine konkrete Anwendung z.B. Insurance Chat wird das Template instanzziiert, also mit konkreten Inahlten befüllt systemPrompt.yaml  
- Die Erstbefüllung könnte auch eine Generative AI LLM übernehmen. Diese könnte dann in einem Prozess weiter verfeinert werden 
- Es könnte auch ein Beispiel verwendet werden, dass dann weiter verfeinert wird z.B. aus Awesome ChatGPT Prompts  

# Compiler 
- Ein Compiler sollte aus der Template Instanz einen lesbaren Text machen 
- Ein Prompt Compiler könne ein Phyten, Java Script sein oder ein einfaches Bash Script 
- In der Regel sind Schritte wie Säuberung der Formatierungen wie z.B. #, Befüllung von Placeholder {{}} und Ablage in einem File notwendig systemPrompt.sh 

# Usage 
- Das z.B. systemPrompt.st sollte mit kontextspezifischen Inhalten aus der Domäne befüllt werden  

# Anlage 

## Tools 
### Build In Tools 
### Customer Tools  