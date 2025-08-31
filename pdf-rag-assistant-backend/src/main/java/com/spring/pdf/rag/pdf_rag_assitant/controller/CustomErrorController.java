package com.spring.pdf.rag.pdf_rag_assitant.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        
        // Get error attributes
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        String errorMessage = (String) request.getAttribute("jakarta.servlet.error.message");
        String requestUri = (String) request.getAttribute("jakarta.servlet.error.request_uri");
        Exception exception = (Exception) request.getAttribute("jakarta.servlet.error.exception");
        
        errorDetails.put("timestamp", System.currentTimeMillis());
        errorDetails.put("status", statusCode);
        errorDetails.put("error", HttpStatus.valueOf(statusCode != null ? statusCode : 500).getReasonPhrase());
        errorDetails.put("message", errorMessage);
        errorDetails.put("path", requestUri);
        
        if (exception != null) {
            errorDetails.put("exception", exception.getClass().getSimpleName());
            errorDetails.put("exceptionMessage", exception.getMessage());
        }
        
        return ResponseEntity.status(statusCode != null ? statusCode : 500).body(errorDetails);
    }
}
