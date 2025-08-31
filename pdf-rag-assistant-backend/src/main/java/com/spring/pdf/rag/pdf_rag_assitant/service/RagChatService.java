package com.spring.pdf.rag.pdf_rag_assitant.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class RagChatService {
    
    private final GeminiChatService geminiChatService;
    // TODO: Sẽ thêm VectorSearchService và DocumentService sau
    
    public RagChatService(GeminiChatService geminiChatService) {
        this.geminiChatService = geminiChatService;
    }
    
    /**
     * RAG Pipeline Process:
     * 1. User Question → Vector Search
     * 2. Retrieve Relevant Documents  
     * 3. Inject Context into Prompt
     * 4. Generate Response with Gemini
     */
    public String chatWithRAG(String userQuestion, String conversationId) {
        try {
            // Step 1: Search for relevant documents (TODO: implement vector search)
            List<String> relevantDocuments = searchRelevantDocuments(userQuestion);
            
            // Step 2: Build context-aware prompt
            String contextPrompt = buildRAGPrompt(userQuestion, relevantDocuments);
            
            // Step 3: Generate response using Gemini
            String response = geminiChatService.generateContent(contextPrompt);
            
            // Step 4: Store conversation history (TODO: implement)
            // storeConversationHistory(conversationId, userQuestion, response);
            
            return response;
            
        } catch (Exception e) {
            System.err.println("RAG Chat error: " + e.getMessage());
            return "Xin lỗi, tôi gặp lỗi khi xử lý câu hỏi của bạn.";
        }
    }
    
    /**
     * Simple chat without RAG context
     */
    public String simpleChat(String message) {
        return geminiChatService.generateContent(message);
    }
    
    // TODO: Implement vector search
    private List<String> searchRelevantDocuments(String query) {
        // Placeholder - sẽ implement với vector database
        return new ArrayList<>();
    }
    
    /**
     * Build RAG prompt với context documents
     */
    private String buildRAGPrompt(String userQuestion, List<String> documents) {
        StringBuilder prompt = new StringBuilder();
        
        prompt.append("Bạn là một trợ lý AI thông minh có khả năng trả lời câu hỏi dựa trên tài liệu được cung cấp.\n\n");
        
        if (!documents.isEmpty()) {
            prompt.append("=== CONTEXT DOCUMENTS ===\n");
            for (int i = 0; i < documents.size(); i++) {
                prompt.append("Document ").append(i + 1).append(":\n");
                prompt.append(documents.get(i)).append("\n\n");
            }
            prompt.append("=== END CONTEXT ===\n\n");
            
            prompt.append("Dựa trên các tài liệu context ở trên, hãy trả lời câu hỏi sau:\n");
        } else {
            prompt.append("Câu hỏi: ");
        }
        
        prompt.append(userQuestion);
        
        if (!documents.isEmpty()) {
            prompt.append("\n\nLưu ý: Chỉ sử dụng thông tin từ context documents để trả lời. Nếu không tìm thấy thông tin phù hợp, hãy nói rằng bạn không tìm thấy thông tin liên quan trong tài liệu.");
        }
        
        return prompt.toString();
    }
}
