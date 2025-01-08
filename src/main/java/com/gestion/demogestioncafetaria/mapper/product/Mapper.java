package com.gestion.demogestioncafetaria.mapper.product;

import com.gestion.demogestioncafetaria.entity.Category;
import com.gestion.demogestioncafetaria.entity.Product;
import com.gestion.demogestioncafetaria.resource.product.ProductRequest;
import com.gestion.demogestioncafetaria.resource.product.ProductResponse;

public class Mapper {

    private Mapper(){}

    public static Product map(ProductRequest request, Category category){
return Product.builder()
        .name(request.name())
        .category(category)
        .description(request.description())
        .price(request.price())
        .status(request.status())
        .build();
    }

    public static ProductResponse map(Product product){
        return  new ProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory().getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStatus()
        );
    }
}
