package com.savvy.admin.entity;

// ✅ EXPLICIT IMPORTS (Do not use .*)
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_registration")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ The compiler MUST see this now
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;
    
    private String status;
}