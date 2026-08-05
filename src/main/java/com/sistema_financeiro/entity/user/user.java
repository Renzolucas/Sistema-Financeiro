package com.sistema_financeiro.entity.user;


import java.util.List;

import com.sistema_financeiro.entity.gasto.gasto;
import com.sistema_financeiro.entity.renda.renda;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
public class user {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String email;

    private String senha;

    private List<renda> rendas;

    private List<gasto> gastos;

}
