package com.sistema_financeiro.entity.renda.dto;

import java.time.LocalDateTime;

public record RendaBodyDTO(
    float valor,
    LocalDateTime dataHora
){}
