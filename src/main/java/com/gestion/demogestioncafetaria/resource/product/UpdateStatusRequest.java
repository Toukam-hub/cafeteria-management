package com.gestion.demogestioncafetaria.resource.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateStatusRequest(
        @Positive(message = "id must be positive")
        Long id,
        @NotBlank(message = "status must not be empty")
        @Size(min = 3)
        String status
) {
}
