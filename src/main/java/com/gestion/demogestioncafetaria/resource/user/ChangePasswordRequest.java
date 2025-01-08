package com.gestion.demogestioncafetaria.resource.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
        @NotBlank
        @Size(min = 4)
        String oldPassword,
        @NotBlank
        @Size(min = 4)
        String newPassword) {}
