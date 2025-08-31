package com.spring.pdf.rag.pdf_rag_assitant.service;

import com.spring.pdf.rag.pdf_rag_assitant.config.GeminiProperties;
import com.spring.pdf.rag.pdf_rag_assitant.dto.GeminiChatRequest;
import com.spring.pdf.rag.pdf_rag_assitant.dto.GeminiChatResponse;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.netty.http.client.HttpClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import java.time.Duration;
import java.util.List;

@Service
public class GeminiChatService {
    private final WebClient webClient;
    private final GeminiProperties geminiProperties;

    public GeminiChatService(GeminiProperties geminiProperties) {
        this.geminiProperties = geminiProperties;
        
        // Tạo HttpClient với system DNS resolver thay vì Netty DNS
        HttpClient httpClient = HttpClient.create()
                .resolver(DefaultAddressResolverGroup.INSTANCE)
                .responseTimeout(Duration.ofSeconds(30));
        
        this.webClient = WebClient.builder()
                .baseUrl(geminiProperties.getBaseUrl())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10MB
                .build();
    }

    public String generateContent(String message) {
        try {
            System.out.println("=== Gemini Request Debug ===");
            System.out.println("Base URL: " + geminiProperties.getBaseUrl());
            System.out.println("API Key: " + (geminiProperties.getApiKey() != null ? "***SET***" : "NOT_SET"));
            System.out.println("Model: " + geminiProperties.getChat().getModel());
            
            // Tạo request payload
            GeminiChatRequest.Part part = new GeminiChatRequest.Part(message);
            GeminiChatRequest.Content content = new GeminiChatRequest.Content(List.of(part));
            GeminiChatRequest.GenerationConfig config = new GeminiChatRequest.GenerationConfig(
                    geminiProperties.getChat().getTemperature(),
                    geminiProperties.getChat().getMaxTokens()
            );

            GeminiChatRequest request = new GeminiChatRequest(List.of(content), config);

            // Gọi Gemini API
            String url = String.format("/v1beta/models/%s:generateContent?key=%s",
                    geminiProperties.getChat().getModel(),
                    geminiProperties.getApiKey());
            
            System.out.println("Full URL: " + geminiProperties.getBaseUrl() + url);
            System.out.println("Making request...");

            GeminiChatResponse response = webClient
                    .post()
                    .uri(url)
                    .header("Content-Type", "application/json")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(GeminiChatResponse.class)
                    .timeout(Duration.ofSeconds(30))
                    .block();

            System.out.println("Response received: " + (response != null));

            // Extract response text
            if (response != null && response.getCandidates() != null && !response.getCandidates().isEmpty()) {
                var candidate = response.getCandidates().get(0);
                if (candidate.getContent() != null && candidate.getContent().getParts() != null 
                    && !candidate.getContent().getParts().isEmpty()) {
                    return candidate.getContent().getParts().get(0).getText();
                }
            }

            return "No response generated";

        } catch (WebClientResponseException e) {
            System.err.println("WebClient error: " + e.getMessage());
            System.err.println("Response body: " + e.getResponseBodyAsString());
            throw new RuntimeException("Gemini API error: " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            System.err.println("General error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to generate content with Gemini", e);
        }
    }
}
