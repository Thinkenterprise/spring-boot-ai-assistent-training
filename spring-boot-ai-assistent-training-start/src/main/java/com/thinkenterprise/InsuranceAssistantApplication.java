package com.thinkenterprise;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thinkenterprise.service.InsuranceAssistantService;

@SpringBootApplication
public class InsuranceAssistantApplication implements ApplicationRunner {

	@Autowired
	ChatModel chatModel;

	@Autowired
	ChatClient chatClient;

	@Autowired
	InsuranceAssistantService insuranceAssistantService;

	public static void main(String[] args) {
		SpringApplication.run(InsuranceAssistantApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String answer = chatModel.call("Hello");
		System.out.println(answer);

		String answerClient = chatClient.prompt("Hello").call().content();
		System.out.println(answerClient);


		String answerService = insuranceAssistantService.chatServiceWithoutMemory("Hello");
		System.out.println(answerService);

		String answerClientParameter = chatClient.prompt(PromptTemplate.builder()
																	   .template("Hallo ich bin der Makler mit dem Namen {brokerName}")
																	   .variables(Map.of("brokerName", "Best Broker Ever"))
																	   .build()
																	   .create())
																	   .call()
																	   .content();
		System.out.println(answerClientParameter);
	}

}