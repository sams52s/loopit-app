package com.loopit.backend.service;

import com.loopit.backend.controller.UserGraphQLController.UserFieldsInput;
import com.loopit.backend.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto createUser(UserFieldsInput input);

    UserDto updateUser(Long id, UserFieldsInput input);

    boolean softDeleteUser(Long id);

    String login(String email, String password);
}
