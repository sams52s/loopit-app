package com.loopit.backend.service;

import com.loopit.backend.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDto> getUsers();

    UserDto createUser(Map<String, Object> input);

    UserDto updateUser(Long id, Map<String, Object> input);

    boolean softDeleteUser(Long id);

    String login(String email, String password);
}
