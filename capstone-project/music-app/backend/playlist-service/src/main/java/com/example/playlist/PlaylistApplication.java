package com.example.playlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced; // ⚠️ New Import
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean; // ⚠️ New Import
import org.springframework.web.client.RestTemplate; // ⚠️ New Import

@SpringBootApplication
@EnableEurekaClient
public class PlaylistApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlaylistApplication.class, args);
    }

    // ✅ ADDED: RestTemplate for inter-service communication
    @Bean
    @LoadBalanced 
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}