package com.loopit.backend.service;

import com.loopit.backend.dto.UserDto;
import com.loopit.backend.dto.UserMapper;
import com.loopit.backend.model.User;
import com.loopit.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

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
    public UserDto createUser(java.util.Map<String, Object> input) {
        log.info("Creating user with email: {}", input.get("email"));
        User user = User.builder()
                .username((String) input.get("username"))
                .email((String) input.get("email"))
                .password(passwordEncoder.encode((String) input.get("password")))
                .phoneNumber((String) input.get("phoneNumber"))
                .address((String) input.get("address"))
                .role("USER")
                .createdAt(LocalDateTime.now())
                .isDeleted(false)
                .build();
        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(Long id, java.util.Map<String, Object> input) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getIsDeleted()) {
            throw new IllegalStateException("Cannot update a deleted user.");
        }
        log.info("Updating user with ID: {}", id);

        if (input.get("username") != null) user.setUsername((String) input.get("username"));
        if (input.get("email") != null) user.setEmail((String) input.get("email"));
        if (input.get("password") != null) user.setPassword(passwordEncoder.encode((String) input.get("password")));
        if (input.get("phoneNumber") != null) user.setPhoneNumber((String) input.get("phoneNumber"));
        if (input.get("address") != null) user.setAddress((String) input.get("address"));

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}