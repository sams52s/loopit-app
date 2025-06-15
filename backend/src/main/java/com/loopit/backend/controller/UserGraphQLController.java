package com.loopit.backend.controller;

import com.loopit.backend.dto.UserDto;
import com.loopit.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserGraphQLController {

    private final UserService userService;

    @QueryMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @MutationMapping
    public UserDto createUser(@Argument CreateUserInput input) {
        return userService.createUser(input.fields());
    }

    @MutationMapping
    public UserDto updateUser(@Argument UpdateUserInput input) {
        return userService.updateUser(input.id(), input.fields());
    }

    @MutationMapping
    public Boolean softDeleteUser(@Argument Long id) {
        return userService.softDeleteUser(id);
    }

    public record UserFieldsInput(
            String username,
            String email,
            String password,
            String phoneNumber,
            String address
    ) {
    }

    public record CreateUserInput(
            UserFieldsInput fields
    ) {
    }

    public record UpdateUserInput(
            Long id,
            UserFieldsInput fields
    ) {
    }
}