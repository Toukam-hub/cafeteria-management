package com.gestion.demogestioncafetaria.service.user;

import com.gestion.demogestioncafetaria.entity.User;
import com.gestion.demogestioncafetaria.exception.AuthenticateException;
import com.gestion.demogestioncafetaria.jwt.JwtUtil;
import com.gestion.demogestioncafetaria.resource.user.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Login {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public String execute(LoginRequest request) {
        log.info("Inside login {}", request);
        Authentication authenticate = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        var principal = (User) authenticate.getPrincipal();
        if (!principal.isAccountNonExpired()) {
            throw new AuthenticateException("Authentication failed: account is expired.");
        }
        String token = this.jwtUtil.generateToken(principal.getUsername(), principal.getAuthorities().iterator().next().getAuthority());
        return "{\"token\":\"" + token + "\"}";

    }
}
