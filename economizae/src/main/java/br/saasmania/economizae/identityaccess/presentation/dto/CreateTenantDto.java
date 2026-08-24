package br.saasmania.economizae.identityaccess.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTenantDto(
        @NotBlank(message = "Name cannot be empty")
        String name,

        String plan
) {}