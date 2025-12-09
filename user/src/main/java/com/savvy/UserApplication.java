package com.savvy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.savvy.config.SecurityConfig; // ✅ Import the class
import org.springframework.context.annotation.Import; // ✅ Import this annotation

@SpringBootApplication
@Import(SecurityConfig.class)
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
