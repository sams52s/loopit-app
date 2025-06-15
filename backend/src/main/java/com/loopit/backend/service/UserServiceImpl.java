package com.loopit.backend.service;

import com.loopit.backend.controller.UserGraphQLController.UserFieldsInput;
import com.loopit.backend.dto.UserDto;
import com.loopit.backend.dto.UserMapper;
import com.loopit.backend.model.User;
import com.loopit.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAllByOrderByIsDeletedAscCreatedAtDesc()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    public UserDto createUser(UserFieldsInput input) {
        log.info("Creating user with email: {}", input.email());
        User user = User.builder()
                .username(input.username())
                .email(input.email())
                .password(passwordEncoder.encode(input.password()))
                .phoneNumber(input.phoneNumber())
                .address(input.address())
                .role("USER")
                .createdAt(LocalDateTime.now())
                .isDeleted(false)
                .build();
        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(Long id, UserFieldsInput input) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getIsDeleted()) {
            throw new IllegalStateException("Cannot update a deleted user.");
        }
        log.info("Updating user with ID: {}", id);

        if (input.username() != null) user.setUsername(input.username());
        if (input.email() != null) user.setEmail(input.email());
        if (input.password() != null) user.setPassword(input.password());
        if (input.phoneNumber() != null) user.setPhoneNumber(input.phoneNumber());
        if (input.address() != null) user.setAddress(input.address());

        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public boolean softDeleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.info("Soft deleting user with ID: {}", id);
        user.setIsDeleted(true);
        userRepository.save(user);
        return true;
    }


    @Override
    public String login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return "Login successful";
    }
}