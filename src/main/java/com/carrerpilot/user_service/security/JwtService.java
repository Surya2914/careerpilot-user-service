package com.carrerpilot.user_service.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;

@Service 
public class JwtService 
{
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
}

public String generateToken(String email)
{
    return Jwts.builder()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis()+1000*60*60))
            .signWith(getSigningKey())
            .compact();
}
public  String extractEmail(String token)
{
    Claims claims=Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();

    return claims.getSubject();
}

public boolean istokenValid(String token, String email)
{
    String tokenEmail=extractEmail(token);

    return tokenEmail.equals(email);
}


// @PostConstruct
// public void checkJwtSecret() {
//     System.out.println("JWT secret configured: "
//             + (secret != null && !secret.isBlank()));

//     System.out.println("JWT secret length: "
//             + (secret == null ? 0 : secret.length()));
// }
    
}
