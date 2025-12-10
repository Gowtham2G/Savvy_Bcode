package com.savvy.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ SPECIFIC HANDLER FOR DUPLICATE USERS
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserExists(UserAlreadyExistsException ex) {
        Map<String, String> response = new HashMap<>();
        
        // This gives you the clean JSON you asked for
        response.put("message", ex.getMessage()); 
        response.put("status", "409"); // 409 Conflict is the standard code for duplicates
        
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // (Keep your other handlers below if needed)
}