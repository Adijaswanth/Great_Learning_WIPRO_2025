package com.example.validation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.validation.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger Logger = LoggerFactory.getLogger(UserService.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                  HttpServletRequest request) {

        Logger.warn("Resource not found : {} - {}", request.getRequestURI(), ex.getMessage());

        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex,
                                                         HttpServletRequest request) {

        Logger.error("Unhandled exception : {} - {}", request.getRequestURI(), ex.getMessage());

        return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
