package com.example.jwtgeneration.service;

import com.example.jwtgeneration.dto.JwtRequest;
import io.jsonwebtoken.Jwts;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final SecretKey signingKey;

    public JwtService(SecretKey signingKey) {
        this.signingKey = signingKey;
    }

    public GeneratedToken generateToken(JwtRequest request) {
        Instant now = Instant.now();
        Instant expiry = now.plus(1, ChronoUnit.HOURS);

        String token = Jwts.builder()
                .subject(request.getUsername())
                .claim("role", request.getRole())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(signingKey, Jwts.SIG.HS256)
                .compact();

        return new GeneratedToken(token, expiry);
    }

    public record GeneratedToken(String token, Instant expiresAt) {}
}
