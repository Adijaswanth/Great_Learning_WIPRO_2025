package com.example.apigateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final RestTemplate rest = new RestTemplate();

    @Value("${routes.user-service:http://localhost:8081}")
    private String userService;

    @Value("${routes.song-service:http://localhost:8082}")
    private String songService;

    @Value("${routes.playlist-service:http://localhost:8083}")
    private String playlistService;

    @Value("${routes.admin-service:http://localhost:8084}")
    private String adminService;

    @Value("${routes.notification-service:http://localhost:8090}")
    private String notificationService;

    // Generic forwarder to a downstream service
    private ResponseEntity<?> forward(String baseUrl, String downstreamPath, HttpMethod method, HttpEntity<?> requestEntity) {
        String url = baseUrl + downstreamPath;
        try {
            ResponseEntity<byte[]> response = rest.exchange(url, method, requestEntity, byte[].class);
            HttpHeaders headers = new HttpHeaders();
            headers.putAll(response.getHeaders());
            return new ResponseEntity<>(response.getBody(), headers, response.getStatusCode());
        } catch (RestClientException ex) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(("Gateway error: " + ex.getMessage()).getBytes());
        }
    }

    // Example mappings - adjust as needed
    @RequestMapping("/users/**")
    public ResponseEntity<?> users(HttpMethod method, HttpEntity<byte[]> requestEntity, HttpServletRequest request) throws IOException {
        String path = request.getRequestURI().substring("/api".length());
        return forward(userService, path, method, requestEntity);
    }

    @RequestMapping("/songs/**")
    public ResponseEntity<?> songs(HttpMethod method, HttpEntity<byte[]> requestEntity, HttpServletRequest request) throws IOException {
        String path = request.getRequestURI().substring("/api".length());
        return forward(songService, path, method, requestEntity);
    }

    @RequestMapping("/playlists/**")
    public ResponseEntity<?> playlists(HttpMethod method, HttpEntity<byte[]> requestEntity, HttpServletRequest request) throws IOException {
        String path = request.getRequestURI().substring("/api".length());
        return forward(playlistService, path, method, requestEntity);
    }

    @RequestMapping("/admin/**")
    public ResponseEntity<?> admin(HttpMethod method, HttpEntity<byte[]> requestEntity, HttpServletRequest request) throws IOException {
        String path = request.getRequestURI().substring("/api".length());
        return forward(adminService, path, method, requestEntity);
    }

    @RequestMapping("/notify/**")
    public ResponseEntity<?> notify(HttpMethod method, HttpEntity<byte[]> requestEntity, HttpServletRequest request) throws IOException {
        String path = request.getRequestURI().substring("/api".length());
        return forward(notificationService, path, method, requestEntity);
    }

    // Health
    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}
