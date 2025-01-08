package com.gestion.demogestioncafetaria.resource.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @PositiveOrZero(message = "ID cannot be negative")
        Long id,
        @NotEmpty(message = "Name cannot be empty")
        @Size(min = 2, max = 75, message = "Name must be between 2 and 75 characters")
        String name) {}
