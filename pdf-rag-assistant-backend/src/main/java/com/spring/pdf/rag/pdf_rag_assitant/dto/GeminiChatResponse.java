package com.spring.pdf.rag.pdf_rag_assitant.dto;

import java.util.List;

public class GeminiChatResponse {
    private List<Candidate> candidates;

    public GeminiChatResponse() {}

    // Getters and Setters
    public List<Candidate> getCandidates() { return candidates; }
    public void setCandidates(List<Candidate> candidates) { this.candidates = candidates; }

    public static class Candidate {
        private Content content;
        private String finishReason;

        public Candidate() {}

        public Content getContent() { return content; }
        public void setContent(Content content) { this.content = content; }

        public String getFinishReason() { return finishReason; }
        public void setFinishReason(String finishReason) { this.finishReason = finishReason; }
    }

    public static class Content {
        private List<Part> parts;

        public Content() {}

        public List<Part> getParts() { return parts; }
        public void setParts(List<Part> parts) { this.parts = parts; }
    }

    public static class Part {
        private String text;

        public Part() {}

        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }
}
