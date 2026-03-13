package ips.SarasAI.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ips.SarasAI.config.GeminiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final GeminiConfig geminiConfig;
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    public String ask(String message){

        try {


            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-goog-api-key", geminiConfig.getKey());

            String body = """
            {
              "contents":[
                {
                  "parts":[
                    {"text":"%s"}
                  ]
                }
              ]
            }
            """.formatted(message);

            HttpEntity<String> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> response =
                    restTemplate.postForEntity(geminiConfig.getUrl(), request, String.class);

            String json = response.getBody();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

            return root
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        } catch (Exception e) {
            throw new RuntimeException("Gemini API error", e);
        }
    }
}