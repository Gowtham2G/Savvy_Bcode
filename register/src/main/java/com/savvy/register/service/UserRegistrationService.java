package com.savvy.register.service;

import com.example.registration.dto.RegisterRequestDTO;
import com.example.registration.dto.RegisterResponseDTO;

public interface UserRegistrationService {
    RegisterResponseDTO registerUser(RegisterRequestDTO request);
    RegisterResponseDTO getUserById(Long id);
    boolean isEmailAvailable(String email);
}
