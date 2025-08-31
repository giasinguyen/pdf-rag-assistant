package com.spring.pdf.rag.pdf_rag_assitant.config;

import com.spring.pdf.rag.pdf_rag_assitant.service.GeminiChatService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GeminiProperties.class)
public class AiConfig {

    @Bean
    public GeminiChatService geminiChatService(GeminiProperties geminiProperties) {
        return new GeminiChatService(geminiProperties);
    }
}
