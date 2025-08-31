package com.spring.pdf.rag.pdf_rag_assitant.dto;

import java.util.List;

public class GeminiChatRequest {
    private List<Content> contents;
    private GenerationConfig generationConfig;

    public GeminiChatRequest() {}

    public GeminiChatRequest(List<Content> contents, GenerationConfig generationConfig) {
        this.contents = contents;
        this.generationConfig = generationConfig;
    }

    // Getters and Setters
    public List<Content> getContents() { return contents; }
    public void setContents(List<Content> contents) { this.contents = contents; }

    public GenerationConfig getGenerationConfig() { return generationConfig; }
    public void setGenerationConfig(GenerationConfig generationConfig) { this.generationConfig = generationConfig; }

    public static class Content {
        private List<Part> parts;

        public Content() {}
        public Content(List<Part> parts) { this.parts = parts; }

        public List<Part> getParts() { return parts; }
        public void setParts(List<Part> parts) { this.parts = parts; }
    }

    public static class Part {
        private String text;

        public Part() {}
        public Part(String text) { this.text = text; }

        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }

    public static class GenerationConfig {
        private Double temperature;
        private Integer maxOutputTokens;
        private Double topP;
        private Integer topK;

        public GenerationConfig() {}

        public GenerationConfig(Double temperature, Integer maxOutputTokens) {
            this.temperature = temperature;
            this.maxOutputTokens = maxOutputTokens;
        }

        // Getters and Setters
        public Double getTemperature() { return temperature; }
        public void setTemperature(Double temperature) { this.temperature = temperature; }

        public Integer getMaxOutputTokens() { return maxOutputTokens; }
        public void setMaxOutputTokens(Integer maxOutputTokens) { this.maxOutputTokens = maxOutputTokens; }

        public Double getTopP() { return topP; }
        public void setTopP(Double topP) { this.topP = topP; }

        public Integer getTopK() { return topK; }
        public void setTopK(Integer topK) { this.topK = topK; }
    }
}
