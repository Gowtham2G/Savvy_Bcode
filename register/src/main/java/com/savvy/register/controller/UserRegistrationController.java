package com.savvy.register.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.registration.dto.RegisterRequestDTO;
import com.example.registration.dto.RegisterResponseDTO;
import com.savvy.register.service.UserRegistrationService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/register")
public class UserRegistrationController {

    private final UserRegistrationService userService;

    // ✅ Constructor injection
    public UserRegistrationController(UserRegistrationService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> registerUser(@RequestBody RegisterRequestDTO request) {
        RegisterResponseDTO response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegisterResponseDTO> getUserById(@PathVariable Long id) {
        RegisterResponseDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailAvailability(@RequestParam String email) {
        boolean available = userService.isEmailAvailable(email);
        return ResponseEntity.ok(available);
    }
}
