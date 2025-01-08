package com.gestion.demogestioncafetaria.resource.product;

public record ProductResponse(
        Long id,
        String name,
        String Category,
        String description,
        Integer price,
        String status
) {
}
