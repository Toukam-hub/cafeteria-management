package com.gestion.demogestioncafetaria.resource.bill;


public record BillTest(
        Long id,
        String name,
        String category,
        int quantity,
        double price,
        double total) {}
