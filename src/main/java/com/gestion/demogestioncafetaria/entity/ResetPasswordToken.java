package com.gestion.demogestioncafetaria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ResetPasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    @Column(name = "expiration_date")
    private Instant expirationDate;
    @ManyToOne
    private User user;
}
