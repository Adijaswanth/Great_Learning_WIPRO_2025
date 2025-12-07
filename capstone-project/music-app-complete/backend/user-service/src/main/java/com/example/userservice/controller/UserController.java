package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.JwtService; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;
    private final JwtService jwtService; 

    public UserController(UserRepository repo, JwtService jwtService) {
        this.repo = repo;
        this.jwtService = jwtService;
    }

    @GetMapping
    public List<User> all() {
        return repo.findAll();
    }

    // 🚨 NEW ADMIN FEATURE ENDPOINT 🚨
    @GetMapping("/all-users")
    public List<User> listAllUsers() {
        // Fetches all users from the database. This endpoint will be restricted 
        // to ADMIN roles in the API Gateway (as determined in the previous step).
        return repo.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        return ResponseEntity.ok(repo.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        return repo.findByUsername(user.getUsername())
                .map(existingUser -> {
                    if (existingUser.getPassword().equals(user.getPassword())) {
                        
                        // GENERATE TOKEN
                        String token = jwtService.generateToken(existingUser.getUsername());
                        
                        // Create a nice JSON response with the token
                        Map<String, String> response = new HashMap<>();
                        response.put("token", token);
                        response.put("username", existingUser.getUsername());
                        response.put("message", "Login Successful");
                        
                        // Return the Token!
                        return ResponseEntity.ok(response);
                        
                    } else {
                        return ResponseEntity.status(401).body("Invalid password");
                    }
                })
                .orElseGet(() -> ResponseEntity.status(404).body("User not found"));
    }
}