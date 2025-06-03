package com.thinkenterprise.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.thinkenterprise.ai.tools.InsuranceCustomerNumberTool;

@Configuration
public class ChatConfiguration {


    @Value("classpath:/systemPrompt.st")
    private Resource systemPromptResource;
 
    @Bean
    public ChatClient createClient(ChatClient.Builder builder, ChatMemory chatMemory, InsuranceCustomerNumberTool insuranceCustomerNumberTool) {
        
    
        var chatClient = builder.defaultAdvisors( MessageChatMemoryAdvisor.builder(chatMemory).build())
                                .defaultSystem(systemPromptResource)
                                .defaultTools(insuranceCustomerNumberTool)
                                .build();
        return chatClient;
    }



}
