package com.inn.cafe.cafe_management_system.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Data
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "user")
public class User implements Serializable {
    private final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name = "name")
    private  String name;

    @Column(name = "contacNumber")
    private  String contactNumber;

    @Column(name = "email")
    private  String email;

    @Column(name = "password")
    private  String password;

    @Column(name = "status")
    private  String status;

    @Column(name = "role")
    private  String role;

}
