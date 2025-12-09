package com.savvy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Long userId;
    private String name;
    private String email;
    private String role;
    private String status;
}
