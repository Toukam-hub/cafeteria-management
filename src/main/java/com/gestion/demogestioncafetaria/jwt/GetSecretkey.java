package com.gestion.demogestioncafetaria.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class GetSecretkey {

    private final String secretKey;

    public GetSecretkey(@Value("${jwt.secret}") String secretKey) {this.secretKey = secretKey;}

    public SecretKey execute() {
        byte[] decoder = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(decoder);
    }
}
