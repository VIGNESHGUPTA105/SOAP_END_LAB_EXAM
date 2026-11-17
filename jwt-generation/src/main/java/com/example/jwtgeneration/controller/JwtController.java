package com.example.jwtgeneration.controller;

import com.example.jwtgeneration.dto.JwtRequest;
import com.example.jwtgeneration.dto.JwtResponse;
import com.example.jwtgeneration.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {
    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/generate")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtResponse generate(@Valid @RequestBody JwtRequest request) {
        JwtService.GeneratedToken generated = jwtService.generateToken(request);
        return new JwtResponse(generated.token(), generated.expiresAt());
    }
}
