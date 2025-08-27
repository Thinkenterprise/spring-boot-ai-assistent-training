package com.thinkenterprise.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class InsuranceChatService {

    ChatClient chatClient;

    public InsuranceChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public ChatResponse chatService(String input) {
        return chatClient.prompt(input).call().chatResponse();
    }




}
