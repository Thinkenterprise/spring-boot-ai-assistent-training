package com.thinkenterprise.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*") 
public class ChatController {

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> payload) {
        String userMessage = payload.get("message");
        String response = generateBotReply(userMessage); // Hier eigene Logik oder LLM-Anbindung
        return Map.of("reply", response);
    }

    private String generateBotReply(String userMessage) {
        // Dummy-Logik oder Backend-KI-Aufruf hier
        return "Echo: " + userMessage;
    } 
}
