package com.thinkenterprise.ai.rag;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.postretrieval.document.DocumentPostProcessor;

public class InsuranceDocumentProcessor implements DocumentPostProcessor{

    @Override
    public List<Document> process(Query query, List<Document> documents) {
        return documents;
    }

}
