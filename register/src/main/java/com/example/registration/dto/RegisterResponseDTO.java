package com.example.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponseDTO {
    private long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String role;
    private String status;
}

