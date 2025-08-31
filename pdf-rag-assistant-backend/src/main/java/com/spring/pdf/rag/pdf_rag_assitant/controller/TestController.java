package com.spring.pdf.rag.pdf_rag_assitant.controller;

import com.spring.pdf.rag.pdf_rag_assitant.service.GeminiChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    
    private final GeminiChatService geminiChatService;

    public TestController(GeminiChatService geminiChatService) {
        this.geminiChatService = geminiChatService;
    }

    @GetMapping("/ping")
    public String ping() {
        try {
            logger.info("Received ping request");
            String response = geminiChatService.generateContent("Say hello from RAG system!");
            logger.info("Generated response: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Error in ping endpoint: ", e);
            throw e;
        }
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message) {
        try {
            logger.info("Received chat request with message: {}", message);
            String response = geminiChatService.generateContent(message);
            logger.info("Generated response: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Error in chat endpoint: ", e);
            throw e;
        }
    }
}
