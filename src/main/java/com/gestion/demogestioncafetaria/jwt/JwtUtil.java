package com.gestion.demogestioncafetaria.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    private final GetSecretkey getSecretkey;

    public JwtUtil(GetSecretkey getSecretkey) {this.getSecretkey = getSecretkey;}

    public String extractEmail(String token) {
        return this.extractClaims(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expiretionDate = this.extractClaims(token, Claims::getExpiration);
        return expiretionDate.before(new Date());
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, email);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretkey.execute())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String createToken(Map<String, Object> claims, String subject) {
        final long creation = System.currentTimeMillis();
        final long expiration = creation + 60 * 30 * 1000;
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(creation))
                .expiration(new Date(expiration))
                .signWith(getSecretkey.execute())
                .compact();
    }

}
