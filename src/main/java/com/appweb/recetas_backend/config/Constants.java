package com.appweb.recetas_backend.config;

import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Constants {

    public static final String LOGIN_URL = "/authentication/login";

    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";

    // Configuración JWT
    public static final String ISSUER_INFO = "BACKEND_RECETAS";
    public static final String SUPER_SECRET_KEY = "#E@M%R!&EGD%srHG823sAK3&gBi&U4Q7"; // Clave secreta para firmar el token
    public static final long TOKEN_EXPIRATION_TIME = 86_400_000L; // 1 día en milisegundos

    //Generar la clave de firma usando la clave secreta
    public static Key getSigningKey() {
        byte[] keyBytes = Constants.SUPER_SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}