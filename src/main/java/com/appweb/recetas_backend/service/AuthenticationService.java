package com.appweb.recetas_backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import com.appweb.recetas_backend.config.Constants;

import java.security.Key;
import java.util.Date;

@Service
public class AuthenticationService {

    private final Key key;

    public AuthenticationService() {
        this.key = Constants.getSigningKey();
    }

    public String authenticate(String email, String password) {
        return generateToken(email);
    }

    private String generateToken(String email) {
        return Jwts.builder()
            .setIssuer(Constants.ISSUER_INFO)
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRATION_TIME))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }
}