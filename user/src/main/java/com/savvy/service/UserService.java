package com.savvy.service;

import com.savvy.dto.UserRequestDto;
import com.savvy.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRequestDto dto);
}
