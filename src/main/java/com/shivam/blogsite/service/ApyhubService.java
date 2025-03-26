package com.shivam.blogsite.service;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApyhubService {
	private final String api_url = "https://api.apyhub.com/sharpapi/api/v1/content/summarize";
	private final String api_key = "APY0Sn1ZuIZA0HjJzK3H4aBWBxFUmpL8WFN6HAjWHyUM787nuyaYRqdFJZTgvrw5sI5TzZdgfC";
	
	public String getSummarizedText(String content) {
		RestTemplate restTemplate = new RestTemplate();
		//Headers
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apy-token", api_key);
        
        //Request Body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("text", content);
        
        //RequestEntity
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
        
        //Response
        ResponseEntity<String> response = restTemplate.exchange(api_url,HttpMethod.POST, request, String.class);
        
        return response.getBody();
	}
}
