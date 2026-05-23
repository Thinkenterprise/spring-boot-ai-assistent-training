package com.thinkenterprise.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class InsuranceAssistantService {

    private final ChatClient chatClient;

    public InsuranceAssistantService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chatService(String input, String conversationId) {
        return chatClient
            .prompt(input)
            .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
            .toolContext(new HashMap<String, Object>(Map.of("session", conversationId)))
            .call()
            .content();
    }

    public String chatServiceWithoutMemory(String input) {
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
