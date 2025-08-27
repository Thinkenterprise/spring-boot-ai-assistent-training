package com.thinkenterprise.ai.configuration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.execution.DefaultToolExecutionExceptionProcessor;
import org.springframework.ai.tool.execution.ToolExecutionExceptionProcessor;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.ai.tool.method.MethodToolCallback;
import org.springframework.ai.tool.support.ToolDefinitions;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.ReflectionUtils;

import com.thinkenterprise.ai.parameter.ProductRequest;
import com.thinkenterprise.ai.properties.InsuranceProperties;
import com.thinkenterprise.ai.rag.InsuranceDocumentProcessor;
import com.thinkenterprise.ai.rag.InsuranceQueryTransformer;
import com.thinkenterprise.ai.tools.InsuranceCustomerDetailsTool;
import com.thinkenterprise.domain.Product;
import com.thinkenterprise.service.InsuranceProductService;


@Configuration
@EnableConfigurationProperties(InsuranceProperties.class)
public class ChatConfiguration {

        @Value("classpath:/systemPrompt.st")
        private Resource systemPromptResource;

        @Bean
        public ChatClient createClient(ChatClient.Builder builder, ChatMemory chatMemory,
                        InsuranceCustomerDetailsTool insuranceCustomerDetailsTool, 
                        RetrievalAugmentationAdvisor retrievalAugmentationAdvisor) {


                SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPromptResource);
                Map<String, Object> variables = Map.of("ToolCustomerDetails", "get_CustomerDetails",
                                "ToolProductDetailsByCustomer", "get_ProductDetailsByCustomer");

                Prompt prompt = systemPromptTemplate.create(variables);

                var chatClient = builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                                .defaultAdvisors(retrievalAugmentationAdvisor)
                                .defaultSystem(prompt.getContents())
                                .defaultTools(insuranceCustomerDetailsTool)
                                //.defaultToolNames("get_ProductDetailsByCustomer")
                                .defaultToolContext(new HashMap<String, Object>(Map.of("databaseHost", "hostValue")))
                                .build();

                return chatClient;
        }

        @Bean
        public ToolExecutionExceptionProcessor toolExecutionExceptionProcessor() {
                return new DefaultToolExecutionExceptionProcessor(true);
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
        public RetrievalAugmentationAdvisor creaRetrievalAugmentationAdvisor(VectorStoreDocumentRetriever vectorStoreDocumentRetriever){
                return RetrievalAugmentationAdvisor.builder()
                                                   .queryTransformers(new InsuranceQueryTransformer())
                                                   .documentRetriever(vectorStoreDocumentRetriever)
                                                   .documentPostProcessors(new InsuranceDocumentProcessor())
                                                   .queryAugmenter(ContextualQueryAugmenter.builder()
                                                                                           .allowEmptyContext(true)
                                                                                           .build())
                                                   .build();
        }


        public ToolCallback createMethodBasedToolCallback(InsuranceCustomerDetailsTool insuranceCustomerDetailsTool) {
                Method method = ReflectionUtils.findMethod(InsuranceCustomerDetailsTool.class, "getCustomerDetails");
                ToolCallback toolCallback = MethodToolCallback.builder()
                                .toolDefinition(ToolDefinitions.builder(method)
                                                .description("Stellt eine Anfrage an den Versicherungsservice, um Kundendaten zu erhalten.")
                                                .build())
                                .toolMethod(method)
                                .toolObject(insuranceCustomerDetailsTool)
                                .build();
                return toolCallback;
        }

        public ToolCallback createFunctionBasedToolCallback(InsuranceProductService insuranceProductService) {
                return FunctionToolCallback.<ProductRequest, Product>builder(
                                "get_ProductDetailsByCustomer",
                                (ProductRequest productRequest, ToolContext context) -> insuranceProductService
                                                .getProductDetailsByCustomer(productRequest.getName()))
                                .description("Stellt eine Anfrage an den Versicherungsservice, um Produktdaten zu erhalten.")
                                .inputType(ProductRequest.class)
                                .build();
        }

}
