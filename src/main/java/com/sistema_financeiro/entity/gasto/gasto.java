package com.sistema_financeiro.entity.gasto;

import com.sistema_financeiro.entity.gasto.enums.Categoria;
import com.sistema_financeiro.entity.user.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "gasto")
@Table(name = "gasto")
public class gasto {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private float valor;

    private Categoria categoria;

    private user user;
}
