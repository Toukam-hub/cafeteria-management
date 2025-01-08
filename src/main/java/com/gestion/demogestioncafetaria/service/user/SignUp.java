package com.gestion.demogestioncafetaria.service.user;

import com.gestion.demogestioncafetaria.exception.ResourceAlreadyExistException;
import com.gestion.demogestioncafetaria.mapper.user.Mapper;
import com.gestion.demogestioncafetaria.repository.UserRepository;
import com.gestion.demogestioncafetaria.resource.user.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUp {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void execute(SignUpRequest request) {
        log.info("inside signup {}", request);
        if (this.userRepository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistException("This email already exists in database");
        }
        var user = Mapper.map(request);
        String passwordCrypte = this.bCryptPasswordEncoder.encode(request.password());
        user.setPassword(passwordCrypte);
        this.userRepository.save(user);
    }
}
