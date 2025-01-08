package com.gestion.demogestioncafetaria.service.product;

import com.gestion.demogestioncafetaria.mapper.product.Mapper;
import com.gestion.demogestioncafetaria.repository.ProductRepository;
import com.gestion.demogestioncafetaria.resource.product.ProductRequest;
import com.gestion.demogestioncafetaria.service.category.GetCategoryByName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateProduct {

    private final ProductRepository productRepository;
    private final GetCategoryByName getCategoryByName;

    public String execute(ProductRequest request) {
        log.info("Inside CreateProduct :{}", request);
        var category = this.getCategoryByName.execute(request.category());
        this.productRepository.save(Mapper.map(request, category));
        return "Product Added  Successfully !";
    }
}
