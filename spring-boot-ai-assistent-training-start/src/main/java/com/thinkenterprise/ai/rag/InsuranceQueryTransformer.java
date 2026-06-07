package com.thinkenterprise.ai.rag;

import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.preretrieval.query.transformation.QueryTransformer;

public class InsuranceQueryTransformer implements QueryTransformer {

    @Override
    public Query transform(Query query) {
       return query;
    }

}
