package br.saasmania.economizae.identityaccess.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
        @NotBlank(message = "TenantId cannot be empty")
        String tenantId,

        @NotBlank(message = "Username cannot be empty")
        String username,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "PasswordHash cannot be empty")
        String passwordHash
) {}