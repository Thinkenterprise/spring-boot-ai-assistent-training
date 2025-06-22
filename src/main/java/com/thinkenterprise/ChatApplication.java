package com.thinkenterprise;

<<<<<<< HEAD
=======
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.vectorstore.SearchRequest;
>>>>>>> 95bb83c7f58a929220b0d9b9ecf6f4785b4317c2
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatApplication implements ApplicationRunner {

	@Autowired
	VectorStore vectorStore;

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
<<<<<<< HEAD
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
		/* 
=======
		// PDF mit PDFBox einlesen
>>>>>>> 95bb83c7f58a929220b0d9b9ecf6f4785b4317c2
		PagePdfDocumentReader reader = new PagePdfDocumentReader("classpath:/test-document.pdf");
		List<Document> docs = reader.get();

		
		ParagraphTextSplitter splitter = new ParagraphTextSplitter();
		List<Document> paragraphs = splitter.split(docs);

<<<<<<< HEAD
		
		vectorStore.add(paragraphs);
=======
		// vectorStore.add(paragraphs);
>>>>>>> 95bb83c7f58a929220b0d9b9ecf6f4785b4317c2

		// List<Document> allDocs = vectorStore.similaritySearch(
		// 		SearchRequest.builder().query(".*").build());

<<<<<<< HEAD
		for (Document doc : allDocs) {
			System.out.println("Text: " + doc.getText());
			System.out.println("Metadata: " + doc.getMetadata());
		}
			*/
=======
		// for (Document doc : allDocs) {
		// 	System.out.println("Text: " + doc.getText());
		// 	System.out.println("Metadata: " + doc.getMetadata());
		// }
>>>>>>> 95bb83c7f58a929220b0d9b9ecf6f4785b4317c2
	}

}