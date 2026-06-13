package com.thinkenterprise.ai;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.evaluation.RelevancyEvaluator;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.evaluation.EvaluationRequest;
import org.springframework.ai.evaluation.EvaluationResponse;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.qdrant.QdrantContainer;

import com.thinkenterprise.service.InsuranceChatService;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class InsuranceApplicationTests {

	@Container
	@ServiceConnection
	static OllamaContainer ollamaContainer = new OllamaContainer("ollama/ollama");

	@Container
	@ServiceConnection
	static QdrantContainer qdrantContainer = new QdrantContainer("qdrant/qdrant");

	@Test
	public void testRagLifeSecurityConditions(@Autowired InsuranceChatService insuranceChatService,
			@Autowired ChatModel chatModel) {

		// Evaluator to check the relavancy of the answer
		RelevancyEvaluator relevancyEvaluator = new RelevancyEvaluator(ChatClient.builder(chatModel));

		// The test query
		String query = "Kann die vereinbarte Versicherungsdauer verlängert werden?";

		// Get response from our Chat Model with RAG Context information
		ChatResponse response = insuranceChatService.chatServiceWithResponse(query);
		String responseContent = response.getResult().getOutput().getText();

		// Get RAG Data
		@SuppressWarnings("unchecked")
		List<Document> responseDocuments = (List<Document>) response.getMetadata()
				.get(RetrievalAugmentationAdvisor.DOCUMENT_CONTEXT);


		// Create Evaluation Request
		EvaluationRequest evaluationRequest = new EvaluationRequest(query, responseDocuments, responseContent);

		// Create releavncy Evaluation
		EvaluationResponse evaluationResponse = relevancyEvaluator.evaluate(evaluationRequest);

		// Execute releavncy Evaluation and Assertation
		Assertions.assertTrue(evaluationResponse.isPass());
	}

}
