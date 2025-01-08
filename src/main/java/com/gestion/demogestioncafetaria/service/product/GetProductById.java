package com.gestion.demogestioncafetaria.service.product;

import com.gestion.demogestioncafetaria.exception.ResourceNotFoundException;
import com.gestion.demogestioncafetaria.mapper.product.Mapper;
import com.gestion.demogestioncafetaria.repository.ProductRepository;
import com.gestion.demogestioncafetaria.resource.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.gestion.demogestioncafetaria.constent.CafeConstent.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductById {

    private final ProductRepository productRepository;

    public ProductResponse execute(Long id) {
        return Mapper.map(this.productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(PRODUCT_NOT_FOUND)
        ));
    }
}
