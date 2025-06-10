package com.thinkenterprise.ai.etl;

import java.util.Arrays;
import java.util.List;

import org.springframework.ai.transformer.splitter.TextSplitter;

public class ParagraphTextSplitter extends TextSplitter {
    @Override
    protected List<String> splitText(String text) {
        // Teilt den Text an doppelten Zeilenumbrüchen (typisch für Paragraphen)
        return Arrays.asList(text.split("\\n\\s*\\n"));
    }

}
