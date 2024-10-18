package com.inn.cafe.cafe_management_system.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@NamedQuery(name = "User.findAllByEmailId",
query = "select u from User u where u.email =:email" )

@NamedQuery(name = "User.getAllUser",
        query = "select new com.inn.cafe.cafe_management_system.dto.UserDto(u.id,u.name,u.contactNumber,u.email,u.status) from User u where u.role='user'" )


@Data
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "users")
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
