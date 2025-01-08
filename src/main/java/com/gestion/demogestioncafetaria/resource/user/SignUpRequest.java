package com.gestion.demogestioncafetaria.resource.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        String name,
        String contactNumber,
        @Email
        String email,
        @NotBlank
        @Size(min = 4)
        String password
) {}
