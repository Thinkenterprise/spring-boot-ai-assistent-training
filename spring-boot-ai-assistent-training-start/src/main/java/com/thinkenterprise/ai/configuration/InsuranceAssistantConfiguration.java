package com.thinkenterprise.ai.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsuranceAssistantConfiguration {

    @Bean
    public ChatClient createClient(ChatClient.Builder builder) {
        var chatClient = builder.defaultOptions(ChatOptions.builder().
                                                            maxTokens(512)
                                                            .build())
                                                            .build();
        return chatClient;
    }

}
