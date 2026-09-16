package com.thinkenterprise.ai.etl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.transformer.splitter.TextSplitter;

public class InsuranceParagraphTextSplitter extends TextSplitter {

    private static final int MAX_CHUNK_SIZE = 800;

    @Override
    protected List<String> splitText(String text) {
        List<String> result = new ArrayList<>();

        String[] paragraphs = text.split("\\n\\s*\\n");

        for (String paragraph : paragraphs) {
            if (paragraph.trim().isEmpty()) {
                continue;
            }

            if (paragraph.length() <= MAX_CHUNK_SIZE) {
                result.add(paragraph);
            } else {
                result.addAll(splitLongParagraph(paragraph));
            }
        }

        return result;
    }

    private List<String> splitLongParagraph(String paragraph) {
        List<String> chunks = new ArrayList<>();
        String[] sentences = paragraph.split("(?<=[.!?])\\s+");

        StringBuilder chunk = new StringBuilder();

        for (String sentence : sentences) {
            if ((chunk.length() + sentence.length()) <= MAX_CHUNK_SIZE) {
                if (chunk.length() > 0) {
                    chunk.append(" ");
                }
                chunk.append(sentence);
            } else {
                if (chunk.length() > 0) {
                    chunks.add(chunk.toString());
                }
                chunk = new StringBuilder(sentence);
            }
        }

        if (chunk.length() > 0) {
            chunks.add(chunk.toString());
        }

        return chunks;
    }
}

