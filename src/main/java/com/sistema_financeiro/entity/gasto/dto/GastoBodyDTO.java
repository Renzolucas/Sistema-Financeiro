package com.sistema_financeiro.entity.gasto.dto;

import com.sistema_financeiro.entity.gasto.enums.Categoria;

public record GastoBodyDTO(
    String nome,
    float valor,
    Categoria categoria
) {}
