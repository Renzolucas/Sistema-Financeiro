package com.sistema_financeiro.entity.gasto.dto;

import java.time.LocalDateTime;

import com.sistema_financeiro.entity.gasto.enums.Categoria;

public record GastoBodyDTO(
    String nome,
    Float valor,
    Categoria categoria,
    LocalDateTime dataHora
) {}
