package com.thinkenterprise;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thinkenterprise.ai.etl.RagEtlOperationService;

@SpringBootApplication
public class ChatApplication implements ApplicationRunner {

	
	@Autowired
	RagEtlOperationService ragEtlOperationService;

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		ragEtlOperationService.loadLifeSecurityConditions();
	}


		
}