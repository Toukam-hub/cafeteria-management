package com.gestion.demogestioncafetaria.service.user;

import com.gestion.demogestioncafetaria.entity.ResetPasswordToken;
import com.gestion.demogestioncafetaria.entity.User;
import com.gestion.demogestioncafetaria.exception.ResourceNotFoundException;
import com.gestion.demogestioncafetaria.jwt.JwtUtil;
import com.gestion.demogestioncafetaria.notification.EmailService;
import com.gestion.demogestioncafetaria.repository.ResetPasswordTokenRepository;
import com.gestion.demogestioncafetaria.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

import static com.gestion.demogestioncafetaria.constent.CafeConstent.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForgotPassword {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

    public String execute(Map<String, String> request) {
        log.info("Inside ForgotPassword : {}", request);
        var user = this.userRepository.findByEmail(request.get("email")).orElseThrow(
                () -> new ResourceNotFoundException(USER_NOT_FOUND)
        );
        String resetToken = jwtUtil.generateToken(user.getEmail(), user.getAuthorities().iterator().next().getAuthority());
        this.resetPasswordTokenRepository.save(this.getResetToken(resetToken, user));
        String resetLink = "http://localhost:8080/user/reset-password?token=" + resetToken;
        this.emailService.forgotMail(user.getEmail(), "reset link by Cafe Manager System", resetLink);
        return "Check your mail for reset password.";
    }

    private ResetPasswordToken getResetToken(String token, User user) {
        return ResetPasswordToken.builder()
                .token(token)
                .expirationDate(Instant.now().plusMillis(1000 * 60 * 30L))
                .user(user)
                .build();
    }
}
