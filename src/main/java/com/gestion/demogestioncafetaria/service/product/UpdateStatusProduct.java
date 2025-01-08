package com.gestion.demogestioncafetaria.service.product;

import com.gestion.demogestioncafetaria.exception.ResourceNotFoundException;
import com.gestion.demogestioncafetaria.repository.ProductRepository;
import com.gestion.demogestioncafetaria.resource.product.UpdateStatusRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.gestion.demogestioncafetaria.constent.CafeConstent.PRODUCT_NOT_FOUND;
import static com.gestion.demogestioncafetaria.constent.CafeConstent.PRODUCT_SUCCESSFULLY_UPDATE_STATUS;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateStatusProduct {

    private final ProductRepository productRepository;

    public String execute(UpdateStatusRequest request) {
        int row = this.productRepository.updateProductByStatus(request.status(), request.id());
        if (row == 0) {
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
        }
        return PRODUCT_SUCCESSFULLY_UPDATE_STATUS;
    }
}
