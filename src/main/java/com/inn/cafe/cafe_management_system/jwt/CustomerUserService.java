package com.inn.cafe.cafe_management_system.jwt;


import com.inn.cafe.cafe_management_system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    private com.inn.cafe.cafe_management_system.model.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {} in class CustomerUserService", username);
        // Recherche de l'utilisateur par son email
        userDetail = userRepository.findAllByEmailId(username);
        if (Objects.isNull(userDetail)) {
            throw new UsernameNotFoundException("User not found.");
        }
        // Renvoi d'un User avec des droits vides (à ajuster avec les rôles si nécessaire)
        return org.springframework.security.core.userdetails.User
                .withUsername(userDetail.getEmail())
                .password(userDetail.getPassword())
                .authorities(new ArrayList<>())
                .accountLocked(false) // Ajoutez d'autres propriétés si besoin
                .build();
    }

    // Méthode pour obtenir les détails de l'utilisateur, si nécessaire
    public com.inn.cafe.cafe_management_system.model.User getUserDetail() {
        return userDetail;
    }
}