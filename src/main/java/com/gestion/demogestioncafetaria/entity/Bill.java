package com.gestion.demogestioncafetaria.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "bill")
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String name;
    private String email;
    @Column(name = "contact_number")
    private String contact;
    @Column(name = "payement_method")
    private String payementMethod;
    private String total;
    @Column(name = "product_detail",columnDefinition = "TEXT")
    private String productDetail;
    private String createBy;
}
