package com.thinkenterprise;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thinkenterprise.ai.etl.ParagraphTextSplitter;

@SpringBootApplication
public class ChatApplication implements ApplicationRunner {

	@Autowired
	VectorStore vectorStore;

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/*
		 * PagePdfDocumentReader pdfReader = new
		 * PagePdfDocumentReader("classpath:/test-document.pdf");
		 * 
		 * 
		 * 
		 * 
		 * List<Document> documents = pdfReader.get();
		 * 
		 * for (Document document : documents) {
		 * System.out.println("Document: " + document.getText());
		 * System.out.println("Metadata: " + document.getMetadata());
		 * }
		 * 
		 * // You can add more logic here to interact with the documents or perform
		 * other tasks
		 * System.out.println("PDF reading completed.");
		 */
		// PDF mit TikaDocumentReader einlesen
		PagePdfDocumentReader reader = new PagePdfDocumentReader("classpath:/test-document.pdf");
		List<Document> docs = reader.get();

		// Paragraphen-Splitter anwenden
		ParagraphTextSplitter splitter = new ParagraphTextSplitter();
		List<Document> paragraphs = splitter.split(docs);

		vectorStore.add(paragraphs);

		List<Document> allDocs = vectorStore.similaritySearch(
				SearchRequest.builder().query(".*").build());

		for (Document doc : allDocs) {
			System.out.println("Text: " + doc.getText());
			System.out.println("Metadata: " + doc.getMetadata());
		}
	}

}