package com.sistema_financeiro.entity.user.dto;

import java.util.List;

import com.sistema_financeiro.entity.gasto.gasto;
import com.sistema_financeiro.entity.renda.renda;

public record ResponseUserAndRendaDTO(
    String nome,
    String email,
    List<renda> renda,
    List<gasto> gasto
) {

}
