package com.sistema_financeiro.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_financeiro.security.dto.LoginRequestDTO;
import com.sistema_financeiro.security.dto.TokenResponseDTO;
import com.sistema_financeiro.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public TokenResponseDTO authToken(@RequestBody @Valid LoginRequestDTO dto){
        TokenResponseDTO token = authService.login(dto);
        return token;
    }
}
