package com.loopit.backend.dto;

import java.time.LocalDateTime;

public record UserDto(
        Long id,
        String username,
        String email,
        String phoneNumber,
        String address,
        String role,
        LocalDateTime createdAt,
        Boolean isDeleted
) {
}