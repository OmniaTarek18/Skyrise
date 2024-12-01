package com.example.backend.Utilites;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleException(NullPointerException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }
}
