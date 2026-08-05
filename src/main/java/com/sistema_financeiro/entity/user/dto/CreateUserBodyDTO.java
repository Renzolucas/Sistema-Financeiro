package com.sistema_financeiro.entity.user.dto;

public record CreateUserBodyDTO(
    String nome,
    String email,
    String senha
) {}
