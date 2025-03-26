package com.shivam.blogsite.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIService {
    private final String API_URL = "https://api.openai.com/v1/chat/completions";
    @Value("${api_key}")
    private String API_KEY; 

    public String summarizeText(String content) {
    	try {
    		RestTemplate restTemplate = new RestTemplate();

            // Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + API_KEY);

            // Request Body
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "gpt-3.5-turbo");
            requestBody.put("messages", new Object[]{
                Map.of("role", "system", "content", "You are a text summarization assistant."),
                Map.of("role", "user", "content", "Summarize the following text: " + content)
            });
            requestBody.put("max_tokens", 100);

            // Request Entity
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            // Send Request
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, request, String.class);

            return response.getBody();
    	}catch (Exception  e) {
			System.out.println("Api is down or you exceeded you limit of api calls!" + e.getLocalizedMessage());
			return null;
		}
        
    }
}
