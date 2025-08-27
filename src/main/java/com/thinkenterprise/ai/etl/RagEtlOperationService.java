package com.thinkenterprise.ai.etl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class RagEtlOperationService {

   static protected Logger logger = LoggerFactory.getLogger(RagEtlOperationService.class);
   
    VectorStore vectorStore;

    public RagEtlOperationService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void loadLifeSecurityConditions() {
        
		PagePdfDocumentReader pdfReader = new PagePdfDocumentReader("classpath:/conditions.pdf");

		List<Document> documents = pdfReader.get();

		ParagraphTextSplitter splitter = new ParagraphTextSplitter();
		List<Document> paragraphs = splitter.split(documents);

		/*
		for (Document paragraph : paragraphs) {
            logger.info("Paragraph: " + paragraph.getMetadata());
            logger.info("Paragraph: " + paragraph.getText());
		}
 */
		vectorStore.add(paragraphs);

		List<Document> allDocs = vectorStore.similaritySearch(
				SearchRequest.builder()
				             .query("Kann die vereinbarte Versicherungsdauer verlängert werden?")
							 .topK(3)
							 .build());

		/* 				 
		for (Document doc : allDocs) {
			logger.info("Paragraph: " + doc.getMetadata());
            logger.info("Paragraph: " + doc.getText());
		}
*/	
	}

}
