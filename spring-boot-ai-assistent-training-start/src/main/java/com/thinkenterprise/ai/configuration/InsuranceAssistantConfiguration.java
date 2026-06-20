package com.thinkenterprise.ai.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import com.thinkenterprise.ai.rag.InsuranceDocumentProcessor;
import com.thinkenterprise.ai.rag.InsuranceQueryTransformer;
import com.thinkenterprise.ai.tools.InsuranceAssistantCustomerDetailsTool;
import com.thinkenterprise.ai.tools.InsuranceProperties;

@Configuration
@EnableConfigurationProperties(InsuranceProperties.class)
public class InsuranceAssistantConfiguration {

    @Value("classpath:/systemPrompt.st")
    private Resource systemPromptResource;

    @Bean
    @Profile("!mcp")
    public ToolCallbackProvider localToolCallbackProvider(
            InsuranceAssistantCustomerDetailsTool insuranceCustomerDetailsTool) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(insuranceCustomerDetailsTool)
                .build();
    }

    @Bean
    public ChatClient createClient(ChatClient.Builder chatClientBuilder,
            MessageChatMemoryAdvisor messageChatMemoryAdvisor,
            ToolCallbackProvider tools,
            RetrievalAugmentationAdvisor retrievalAugmentationAdvisor) {

        var chatClient = chatClientBuilder.defaultOptions(createChatOptions())
                .defaultSystem(createSystemPrompt().toString())
                .defaultAdvisors(messageChatMemoryAdvisor)
                .defaultAdvisors(a -> a.param(ChatMemory.CONVERSATION_ID, "InsuranceAssistent"))
                .defaultTools(tools)
                .defaultToolContext(new HashMap<String, Object>(Map.of("session", "No Id")))
                .defaultAdvisors(retrievalAugmentationAdvisor)
                .build();
        return chatClient;
    }


    @Bean
    public MessageChatMemoryAdvisor createChatMemoryAdvisor(ChatMemory chatMemory) {
        return MessageChatMemoryAdvisor.builder(chatMemory).build();
    }

   

    private Prompt createSystemPrompt() {

        return SystemPromptTemplate.builder()
                .resource(systemPromptResource)
                .variables(Map.of("assistentName", "AI Insurance Assistent",
                        "getCustomerDetails", "getCustomerDetails"))
                .build()
                .create();
    }

    private ChatOptions.Builder createChatOptions() {

        return ChatOptions.builder()
                .maxTokens(512);
    }

    @Bean
    public VectorStoreDocumentRetriever creaStoreDocumentRetriever(VectorStore vectorStore) {
        return VectorStoreDocumentRetriever.builder()
                .vectorStore(vectorStore)
                .topK(3)
                .similarityThreshold(0.8)
                .build();
    }

    @Bean
    public RetrievalAugmentationAdvisor creaRetrievalAugmentationAdvisor(
            VectorStoreDocumentRetriever vectorStoreDocumentRetriever) {
        return RetrievalAugmentationAdvisor.builder()
                .queryTransformers(new InsuranceQueryTransformer())
                .documentRetriever(vectorStoreDocumentRetriever)
                .documentPostProcessors(new InsuranceDocumentProcessor())
                .queryAugmenter(ContextualQueryAugmenter.builder()
                        .allowEmptyContext(true)
                        .build())
                .build();
    }

}
