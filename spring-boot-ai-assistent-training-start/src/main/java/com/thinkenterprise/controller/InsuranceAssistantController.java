package com.thinkenterprise.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinkenterprise.service.InsuranceAssistantService;

@RestController
@RequestMapping("/chat")
//@CrossOrigin(origins = "*")
public class InsuranceAssistantController {

   
    private InsuranceAssistantService insuranceChatService;
   
    InsuranceAssistantController(InsuranceAssistantService insuranceChatService) {
        this.insuranceChatService=insuranceChatService;
    }

    @PostMapping
    public String chat(@RequestBody String input) {
       return insuranceChatService.chatService(input);
    }
}
