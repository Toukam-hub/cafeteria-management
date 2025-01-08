package com.gestion.demogestioncafetaria.resource.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateRequest(
        @NotBlank
        Integer id,
        @NotBlank
        Boolean status
) {}
