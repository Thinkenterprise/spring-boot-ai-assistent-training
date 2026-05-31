package com.thinkenterprise.ai.etl;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsuranceProcessEtlPipeline {

	static protected Logger logger = LoggerFactory.getLogger(InsuranceProcessEtlPipeline.class);

	@Bean
	public ApplicationRunner run(VectorStore vectorStore) {
    
		return arg -> {
        logger.info("Starte Laden von Versicherungsbedingungen aus PDF");

		PagePdfDocumentReader pdfReader = new PagePdfDocumentReader("classpath:/conditions.pdf");

		List<Document> documents = pdfReader.get();
		logger.info("Extrahierte {} Dokumente aus PDF", documents.size());

		InsuranceParagraphTextSplitter splitter = new InsuranceParagraphTextSplitter();
		List<Document> paragraphs = splitter.split(documents);
		logger.info("Splitter erzeugte {} Paragraphs", paragraphs.size());
		logger.info("Schreibe {} Paragraphs to Vector Database", paragraphs.size());

		vectorStore.add(paragraphs);

		};
	}

}
