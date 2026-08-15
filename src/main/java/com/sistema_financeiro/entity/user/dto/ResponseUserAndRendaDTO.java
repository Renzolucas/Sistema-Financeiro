package com.sistema_financeiro.entity.user.dto;

import java.util.List;

import com.sistema_financeiro.entity.gasto.Gasto;
import com.sistema_financeiro.entity.renda.Renda;

public record ResponseUserAndRendaDTO(
    String nome,
    String email,
    List<Renda> renda,
    List<Gasto> gasto
) {

}
