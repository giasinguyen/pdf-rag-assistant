package com.spring.pdf.rag.pdf_rag_assitant.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.ai.gemini")
public class GeminiProperties {
    private String apiKey;
    private String baseUrl = "https://generativelanguage.googleapis.com";
    private Chat chat = new Chat();
    private Embedding embedding = new Embedding();

    // Getters and Setters
    public String getApiKey() { return apiKey; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

    public String getBaseUrl() { return baseUrl; }
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }

    public Chat getChat() { return chat; }
    public void setChat(Chat chat) { this.chat = chat; }

    public Embedding getEmbedding() { return embedding; }
    public void setEmbedding(Embedding embedding) { this.embedding = embedding; }

    public static class Chat {
        private String model = "gemini-1.5-flash";
        private double temperature = 0.2;
        private int maxTokens = 1024;

        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }

        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }

        public int getMaxTokens() { return maxTokens; }
        public void setMaxTokens(int maxTokens) { this.maxTokens = maxTokens; }
    }

    public static class Embedding {
        private String model = "text-embedding-004";

        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
    }
}
