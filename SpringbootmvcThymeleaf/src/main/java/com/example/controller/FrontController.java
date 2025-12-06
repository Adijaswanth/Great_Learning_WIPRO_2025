package com.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class FrontController {
//http://localhost:8080/
@GetMapping("/") // Handle requests to the home page
public String showHomePage() {
return "home"; // This will load home.html from templates folder
}
@GetMapping("/log")
public String showLoginPage() {
return "login"; // Loads login.html
}
@GetMapping("/register")
public String showRegisterPage() {
return "register"; // Load register.html
}
}