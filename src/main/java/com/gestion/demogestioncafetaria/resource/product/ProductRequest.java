package com.gestion.demogestioncafetaria.resource.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductRequest(
        @NotBlank(message = "name of product should be not empty")
        @Size(max = 100, message = "The name must not exceed 100 characters.")
        String name,
        @NotBlank(message = "name of product should be not empty")
        @Size(max = 50, message = "The name must not exceed 50 characters.")
        String category,
        @NotBlank(message = "name of product should be not empty")
        String description,
        @Positive
        Integer price,
        String status

) {
}
