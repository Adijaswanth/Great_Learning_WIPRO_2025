package com.example.adminservice.controller;

import com.example.adminservice.entity.Admin;
import com.example.adminservice.repository.AdminRepository;
import com.example.adminservice.security.JwtUtil; // Import your util
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminRepository repo;

    @Autowired
    private RestTemplate rest;

    @Autowired
    private JwtUtil jwtUtil; // ✅ INJECT IT (Don't create it manually)

    // ⚡ LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {

        return repo.findByUsername(admin.getUsername())
                .map(existing -> {
                    if (existing.getPassword().equals(admin.getPassword())) {

                        // ✅ USE THE INJECTED SERVICE
                        String token = jwtUtil.generateToken(
                                existing.getUsername(),
                                Map.of(
                                        "role", "ADMIN",
                                        "username", existing.getUsername()
                                )
                        );

                        return ResponseEntity.ok(Map.of(
                                "token", token,
                                "message", "Login successful"
                        ));
                    } else {
                        return ResponseEntity.status(401).body("Invalid password");
                    }
                })
                .orElseGet(() -> ResponseEntity.status(404).body("Admin not found"));
    }

    // ----------------------- SONG APIs -----------------------

    // Add Song
    @PostMapping("/songs")
    public ResponseEntity<String> addSong(@RequestBody String songJson) {
        String url = "http://SONG-SERVICE/api/songs";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(songJson, headers);
        return rest.postForEntity(url, request, String.class);
    }

    // Update Visibility
    @PutMapping("/songs/{id}/visibility")
    public ResponseEntity<String> setVisibility(@PathVariable Long id, @RequestParam boolean visible) {
        String url = "http://SONG-SERVICE/api/songs/" + id + "/visibility?visible=" + visible;
        return rest.exchange(url, HttpMethod.PUT, null, String.class);
    }
}