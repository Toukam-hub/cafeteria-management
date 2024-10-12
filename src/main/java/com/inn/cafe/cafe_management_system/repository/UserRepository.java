package com.inn.cafe.cafe_management_system.repository;

import com.inn.cafe.cafe_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findAllByEmailId(@Param("email") String email);
}
