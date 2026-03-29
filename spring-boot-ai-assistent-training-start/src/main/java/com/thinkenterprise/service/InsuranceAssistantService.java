package com.thinkenterprise.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class InsuranceAssistantService {

     private final ChatClient chatClient;

    public InsuranceAssistantService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chatService(String input) {
        return chatClient
            .prompt(input)
            .call()
            .content();
    }

    public ChatResponse chatServiceWithResponse(String input) {
        return chatClient
            .prompt(input)
            .call()
            .chatResponse();
    }
    
}
