package com.savvy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.savvy.dto.UserRequestDto;
import com.savvy.dto.UserResponseDto;
import com.savvy.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDto> getUserProfile(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserProfile(email));
    }
}
