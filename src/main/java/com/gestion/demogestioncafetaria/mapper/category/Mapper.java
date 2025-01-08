package com.gestion.demogestioncafetaria.mapper.category;

import com.gestion.demogestioncafetaria.entity.Category;
import com.gestion.demogestioncafetaria.resource.category.CategoryResponse;

public class Mapper {

    private Mapper() {}

    public static CategoryResponse map(Category request) {
        return new CategoryResponse(request.getId(), request.getName());
    }
}
