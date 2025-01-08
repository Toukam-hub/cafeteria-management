package com.gestion.demogestioncafetaria.mapper.user;

import com.gestion.demogestioncafetaria.entity.User;
import com.gestion.demogestioncafetaria.resource.user.SignUpRequest;
import com.gestion.demogestioncafetaria.resource.user.UserRecord;
import com.gestion.demogestioncafetaria.utils.Role;

public class Mapper {
    private Mapper() {}

    public static User map(SignUpRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .contactNumber(request.contactNumber())
                .role(Role.USER)
                .build();
    }

    public static UserRecord map(User user){
        return  new UserRecord(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getContactNumber(),
                user.isStatus()
        );
    }
}
