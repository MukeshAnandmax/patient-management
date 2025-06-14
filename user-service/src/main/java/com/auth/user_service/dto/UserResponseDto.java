package com.auth.user_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String email;
    private LocalDateTime createdAt;

}
