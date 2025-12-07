package com.example.apigateway.config;

import com.example.apigateway.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                // ⭐ Allow Spring Boot to serve static resources
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                // Allow all frontend pages
                .antMatchers("/", "/index", "/index.html",
                             "/admin-login", "/admin-login.html",
                             "/admin-dashboard", "/admin-dashboard.html",
                             "/user-login", "/user-login.html",
                             "/user-register", "/user-register.html",
                             "/user-dashboard", "/user-dashboard.html",
                             "/style.css",
                             "/api/users/login", "/api/users/register").permitAll()

                .anyRequest().authenticated()
            .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
