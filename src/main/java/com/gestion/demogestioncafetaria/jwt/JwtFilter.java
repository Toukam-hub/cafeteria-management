package com.gestion.demogestioncafetaria.jwt;

import com.gestion.demogestioncafetaria.service.user.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.gestion.demogestioncafetaria.constent.CafeConstent.BASEURL;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    Claims claims = null;
    private String email = null;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isExcludedPath(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = extractToken(request);
        if (token != null) {
            email = jwtUtil.extractEmail(token);
            claims = jwtUtil.extractAllClaims(token);
            authenticateUser(email, token, request);
        }

        filterChain.doFilter(request, response);
    }

    private boolean isExcludedPath(HttpServletRequest request) {
        String path = request.getContextPath();
        return path.matches(BASEURL + "/signup | " + BASEURL + "/login | " + BASEURL + "/forgotPassword |" + BASEURL + "/reset-password");
    }

    private String extractToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    private void authenticateUser(String email, String token, HttpServletRequest request) {
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetail = this.userDetailsServiceImpl.loadUserByUsername(email);
            if (Boolean.TRUE.equals(jwtUtil.validateToken(token, userDetail))) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetail, null, userDetail.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    }

    public String getCurentEmail() {
        return this.email;
    }
}
