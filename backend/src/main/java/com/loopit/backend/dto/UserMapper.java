package com.loopit.backend.dto;

import com.loopit.backend.model.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getRole(),
                user.getCreatedAt(),
                user.getIsDeleted()
        );
    }
}
