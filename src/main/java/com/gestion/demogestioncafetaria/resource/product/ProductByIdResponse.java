package com.gestion.demogestioncafetaria.resource.product;

public record ProductByIdResponse(
        Long id,
        String name,
        String description,
        Integer price
) {
}
