package com.savvy.login.service;

import com.savvy.login.dto.LoginRequestDto;
import com.savvy.login.dto.LoginResponseDto;

public interface LoginService {
    // ✅ Matches the method in your Impl
    LoginResponseDto login(LoginRequestDto request);
}