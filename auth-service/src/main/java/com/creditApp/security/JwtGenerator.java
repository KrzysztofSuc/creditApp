package com.creditApp.security;

import com.creditApp.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
public class JwtGenerator {

    private final int expirationTime;
    private final String secret;

    public JwtGenerator(@Value("${jwt.expirationTime}") int expirationTime,
                        @Value("${jwt.secret}") String secret) {
        this.expirationTime = expirationTime;
        this.secret = secret;
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("role", user.getRole());
        //String encodedString = Base64.getEncoder().encodeToString(secret.getBytes());
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", Collections.singletonList("ROLE_"+ user.getRole()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
