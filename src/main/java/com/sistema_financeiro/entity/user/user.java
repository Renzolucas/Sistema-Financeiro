package com.sistema_financeiro.entity.user;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema_financeiro.entity.gasto.gasto;
import com.sistema_financeiro.entity.renda.renda;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "senha")
    private String senha;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<renda> rendas;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<gasto> gastos;

}
