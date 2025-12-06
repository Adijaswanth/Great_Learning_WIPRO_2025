package com.task.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.converter.HttpMessageNotReadableException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> invalidDate(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Invalid date format (use yyyy-MM-dd)");
    }
}
