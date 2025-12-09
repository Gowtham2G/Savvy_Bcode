package com.savvy.service;

import com.savvy.dto.UserResponseDto;

public interface UserService {
    UserResponseDto getUserProfile(String email);
}
