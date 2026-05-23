package com.thinkenterprise.ai.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.thinkenterprise.ai.tools.InsuranceAssistantCustomerDetailsTool;
import com.thinkenterprise.ai.tools.InsuranceProperties;

@Configuration
@EnableConfigurationProperties(InsuranceProperties.class)
public class InsuranceAssistantConfiguration {

    @Value("classpath:/systemPrompt.st")
    private Resource systemPromptResource;


    @Bean
    public ChatClient createClient(ChatClient.Builder chatClientBuilder,ChatMemory chatMemory, 
                                   InsuranceAssistantCustomerDetailsTool insuranceCustomerDetailsTool) {

        var chatClient = chatClientBuilder.defaultOptions(createChatOptions())
                                          .defaultSystem(createSystemPrompt().toString())
                                          .defaultAdvisors(createChatMemoryAdvisor(chatMemory))
                                          .defaultAdvisors(a -> a.param(ChatMemory.CONVERSATION_ID, "InsuranceAssistent"))
                                          .defaultTools(insuranceCustomerDetailsTool)
                                          .defaultToolContext(new HashMap<String, Object>(Map.of("session", "No Id")))
                                          .build();
        return chatClient;
    }

    private Advisor createChatMemoryAdvisor(ChatMemory chatMemory) {
        return MessageChatMemoryAdvisor.builder(chatMemory).build();
    }

    private Prompt createSystemPrompt() {

        return SystemPromptTemplate.builder()
                                   .resource(systemPromptResource)
                                   .variables(Map.of("assistentName", "AI Insurance Assistent",
                                                     "getCustomerDetails", "getCustomerDetails"))
                                   .build()
                                   .create();
    }

    private ChatOptions.Builder createChatOptions() {
        
        return  ChatOptions.builder()
                           .maxTokens(512);
    }

}
