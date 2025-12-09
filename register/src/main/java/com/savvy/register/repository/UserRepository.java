package com.savvy.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savvy.register.entity.UserRegistration;

public interface UserRepository extends JpaRepository<UserRegistration, Long> {
    boolean existsByEmail(String email);
}
