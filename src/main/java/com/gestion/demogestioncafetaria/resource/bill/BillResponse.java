package com.gestion.demogestioncafetaria.resource.bill;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record BillResponse(
        Long id,
        String name,
        String email,
        String contact,
        String paymentMethod,
        String total,
        String productDetail,
        String username
) {
}
