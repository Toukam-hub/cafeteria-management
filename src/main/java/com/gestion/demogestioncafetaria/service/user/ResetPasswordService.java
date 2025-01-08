package com.gestion.demogestioncafetaria.service.user;

import com.gestion.demogestioncafetaria.entity.ResetPasswordToken;
import com.gestion.demogestioncafetaria.exception.ResourceNotFoundException;
import com.gestion.demogestioncafetaria.repository.ResetPasswordTokenRepository;
import com.gestion.demogestioncafetaria.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResetPasswordService {

    private final UserRepository userRepository;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public boolean execute(String token, Map<String, String> request) {
        log.info("Inside resert password ");
        var resetPasswordTokenOptional = resetPasswordTokenRepository.findByToken(token).orElseThrow(
                () -> new ResourceNotFoundException("Reset token not found")
        );
        if (resetPasswordTokenOptional.getExpirationDate().isBefore(Instant.now())) {
            return false;
        }
        var user = resetPasswordTokenOptional.getUser();
        user.setPassword(passwordEncoder.encode(request.get("newPassword")));
        userRepository.save(user);
        resetPasswordTokenRepository.delete(resetPasswordTokenOptional);
        return true;
    }
}
