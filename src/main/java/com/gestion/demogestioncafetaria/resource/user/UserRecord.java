package com.gestion.demogestioncafetaria.resource.user;

public record UserRecord(
        Integer id,
        String name,
        String email,
        String contactNumber,
        boolean status
){}
