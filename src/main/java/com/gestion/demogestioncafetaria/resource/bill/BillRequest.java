package com.gestion.demogestioncafetaria.resource.bill;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record BillRequest(
        @Size(min = 3)
        String name,
        @Email
        String email,
        @Size(min = 3)
        String contact,
        @Size(min = 3)
        String paymentMethod,
        @Size(min = 3)
        String total,
        String productDetail
) {
}
