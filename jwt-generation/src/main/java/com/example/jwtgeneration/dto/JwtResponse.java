package com.example.jwtgeneration.dto;

import java.time.Instant;

public class JwtResponse {
    private final String token;
    private final String tokenType;
    private final Instant expiresAt;

    public JwtResponse(String token, Instant expiresAt) {
        this.token = token;
        this.tokenType = "Bearer";
        this.expiresAt = expiresAt;
    }

    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }
    public Instant getExpiresAt() { return expiresAt; }
}
