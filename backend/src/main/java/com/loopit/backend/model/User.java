package com.loopit.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends Basic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotBlank
    private String username;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{11}$|^\\+?[0-9]{14}$", message = "Phone number must be 11 or 14 digits")
    @Column(unique = true)
    private String phoneNumber;

    @Size(max = 255)
    private String address;

    @Column(nullable = false)
    private String role = "USER";

}
