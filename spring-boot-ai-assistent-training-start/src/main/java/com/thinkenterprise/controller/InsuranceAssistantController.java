package com.thinkenterprise.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinkenterprise.service.InsuranceAssistantService;

@RestController
@RequestMapping("/chat")
public class InsuranceAssistantController {

    private InsuranceAssistantService insuranceChatService;

    InsuranceAssistantController(InsuranceAssistantService insuranceChatService) {
        this.insuranceChatService = insuranceChatService;
    }

    @PostMapping("console")
    public String chat(@RequestBody String input) {
        return insuranceChatService.chatServiceWithoutMemory(input);
    }

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        String conversationId = payload.get("conversationId");
        var result = insuranceChatService.chatService(message,conversationId);
        return Map.of("reply", result);
    }

}
