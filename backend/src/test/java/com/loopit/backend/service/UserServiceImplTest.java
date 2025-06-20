package com.loopit.backend.service;

import com.loopit.backend.dto.UserDto;
import com.loopit.backend.model.User;
import com.loopit.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void shouldCreateUser() {
        Map<String, Object> input = new HashMap<>();
        input.put("username", "testUser");
        input.put("email", "test@example.com");
        input.put("password", "plainPass");
        input.put("phoneNumber", "1234567890");
        input.put("address", "Test Address");

        when(passwordEncoder.encode("plainPass")).thenReturn("hashedPass");

        User savedUser = User.builder()
                .id(1L)
                .username("testUser")
                .email("test@example.com")
                .password("hashedPass")
                .phoneNumber("1234567890")
                .address("Test Address")
                .role("USER")
                .createdAt(LocalDateTime.now())
                .isDeleted(false)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserDto result = userService.createUser(input);

        assertEquals("testUser", result.username());
        assertEquals("test@example.com", result.email());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldThrowOnInvalidLogin() {
        when(userRepository.findByEmail("fail@example.com")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.login("fail@example.com", "pass");
        });

        assertEquals("Invalid credentials", exception.getMessage());
    }

    @Test
    void shouldLoginSuccessfully() {
        User user = User.builder()
                .email("login@example.com")
                .password("hashedPass")
                .build();

        when(userRepository.findByEmail("login@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("plainPass", "hashedPass")).thenReturn(true);

        String result = userService.login("login@example.com", "plainPass");

        assertEquals("Login successful", result);
    }
}
