package com.gestion.demogestioncafetaria.service.product;

import com.gestion.demogestioncafetaria.exception.ResourceNotFoundException;
import com.gestion.demogestioncafetaria.repository.ProductRepository;
import com.gestion.demogestioncafetaria.resource.product.ProductRequest;
import com.gestion.demogestioncafetaria.service.category.GetCategoryByName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.gestion.demogestioncafetaria.constent.CafeConstent.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateProduct {

    private final ProductRepository productRepository;
    private final GetCategoryByName getCategoryByName;

    public String execute(Long id, ProductRequest request) {
        log.info("Inside UpdateProduct : {}", request);
        var category = this.getCategoryByName.execute(request.category());
        var product = this.productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(PRODUCT_NOT_FOUND));
        product.setName(request.name());
        product.setCategory(category);
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStatus(request.status());
        this.productRepository.save(product);
        return "Product update Successfully !";
    }


}
