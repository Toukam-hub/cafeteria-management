package com.gestion.demogestioncafetaria.service.user;

import com.gestion.demogestioncafetaria.entity.User;
import com.gestion.demogestioncafetaria.mapper.user.Mapper;
import com.gestion.demogestioncafetaria.repository.UserRepository;
import com.gestion.demogestioncafetaria.resource.user.UserRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllUser {

    private final UserRepository userRepository;

    public List<UserRecord> getAllUser() {
        log.info("Inside GetAllUser");
        return this.userRepository.findAll().stream().
                map(Mapper::map).toList();
    }

    public List<String> getAllAdmin() {
        List<String> list = this.userRepository.findAll().stream()
                .filter(user -> "ADMIN".equals(user.getRole().name()))
                .map(User::getEmail).toList();
        log.info("Inside GetAllAdmin {}",list);
        return new ArrayList<>(list);
    }
}
