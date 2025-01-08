package com.gestion.demogestioncafetaria.repository;

import com.gestion.demogestioncafetaria.entity.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken,Integer> {
    Optional<ResetPasswordToken> findByToken(String token);
}
