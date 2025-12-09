package com.savvy.login.entity;

import jakarta.persistence.*;
import lombok.Data; // Or use manual getters/setters if you prefer

@Entity
@Table(name = "user_registration") // ✅ Connects to the SAME table
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;
    private String password; // Login needs this!
    private String role;
    private String status;
}