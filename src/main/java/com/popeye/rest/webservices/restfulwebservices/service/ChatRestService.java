package com.popeye.rest.webservices.restfulwebservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ChatRestService {

    RestTemplate restTemplate= new RestTemplate();

    private static final String HEADER_X_API_KEY = "api-key";

    public String postMessage() {

//        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("https://api.psnext.info/api/chat/");
//        URI url = uriComponentsBuilder.build(false).toUri();

        HttpEntity<?> httpEntity = new HttpEntity<>(null, buildHeaders());
        try	{
            String queryUri = UriComponentsBuilder.fromHttpUrl("https://api.psnext.info/api/chat/")
                    .path("models")
                    .build(false)
                    .toUriString();

            ResponseEntity<Void> response = restTemplate.exchange(
                    queryUri,
                    HttpMethod.GET,
                    httpEntity,
                    Void.class);

            return response.getBody().toString();
        } catch (Exception e) {
        }
        return null;
    }

    public HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySW5mbyI6eyJpZCI6NDAyOCwicm9sZXMiOlsiZGVmYXVsdCJdLCJwYXRpZCI6IjliY2Y4Yzk3LTAxNWQtNDZmNS1hNWI0LTYyNTI5NmZhOGU3NCJ9LCJpYXQiOjE3MDcwMzc0MzksImV4cCI6MTcwOTYyOTQzOX0.nIG3PxgwfX7OYrcDNfyXJJs78mZQ7C7ehW2DxjGkvnc");
        return headers;
    }
}


