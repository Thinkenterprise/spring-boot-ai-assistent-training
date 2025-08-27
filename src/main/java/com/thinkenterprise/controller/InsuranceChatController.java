package com.thinkenterprise.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinkenterprise.ai.tools.InsuranceException;
import com.thinkenterprise.service.InsuranceChatService;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class InsuranceChatController {

    private InsuranceChatService insuranceChatService;
   
    InsuranceChatController(InsuranceChatService insuranceChatService) {
        this.insuranceChatService=insuranceChatService;
    }

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        ChatResponse response = insuranceChatService.chatService(message);
        message=response.getResult().getOutput().getText();
        return Map.of("reply", message);
    }

    @ExceptionHandler(value = InsuranceException.class)
	ResponseEntity<ProblemDetail> handleException(InsuranceException exception) {
		
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setType(URI.create("http://thinkenterprise.com/InsuranceException"));
		problemDetail.setTitle("Tool Execution Problem");
		problemDetail.setDetail(exception.getMessage());

		return new ResponseEntity<ProblemDetail>(problemDetail, HttpStatus.BAD_REQUEST);
	}
}
