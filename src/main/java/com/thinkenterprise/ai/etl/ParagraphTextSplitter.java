package com.thinkenterprise.ai.etl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.ai.transformer.splitter.TextSplitter;

public class ParagraphTextSplitter extends TextSplitter {

    private static final int DEFAULT_MAX_CHUNK_SIZE = 1000;

    private final int maxChunkSize;

    public ParagraphTextSplitter() {
        this(DEFAULT_MAX_CHUNK_SIZE);
    }

    public ParagraphTextSplitter(int maxChunkSize) {
        if (maxChunkSize <= 0) {
            throw new IllegalArgumentException("maxChunkSize must be > 0");
        }
        this.maxChunkSize = maxChunkSize;
    }

    @Override
    protected List<String> splitText(String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }

        List<String> paragraphs = Arrays.stream(text.split("\\n\\s*\\n"))
                .map(String::trim)
                .filter(p -> !p.isEmpty())
                .toList();

        List<String> chunks = new ArrayList<>();
        for (String paragraph : paragraphs) {
            if (paragraph.length() <= maxChunkSize) {
                chunks.add(paragraph);
            } else {
                chunks.addAll(splitIntoMaxSize(paragraph, maxChunkSize));
            }
        }

        return chunks;
    }

    private List<String> splitIntoMaxSize(String paragraph, int maxSize) {
        List<String> chunks = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (String token : paragraph.split("\\s+")) {
            if (token.isEmpty()) {
                continue;
            }

            if (token.length() > maxSize) {
                if (current.length() > 0) {
                    chunks.add(current.toString().trim());
                    current.setLength(0);
                }
                for (int i = 0; i < token.length(); i += maxSize) {
                    int end = Math.min(i + maxSize, token.length());
                    chunks.add(token.substring(i, end));
                }
                continue;
            }

            if (current.length() + token.length() + 1 > maxSize) {
                chunks.add(current.toString().trim());
                current.setLength(0);
            }

            if (current.length() > 0) {
                current.append(' ');
            }
            current.append(token);
        }

        if (current.length() > 0) {
            chunks.add(current.toString().trim());
        }

        return chunks;
    }

}
