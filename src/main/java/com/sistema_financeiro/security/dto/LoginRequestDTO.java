package com.sistema_financeiro.security.dto;

public record LoginRequestDTO(
    String email,
    String senha
) {
    
}
