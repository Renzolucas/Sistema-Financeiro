package com.sistema_financeiro.entity.user;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema_financeiro.entity.gasto.Gasto;
import com.sistema_financeiro.entity.renda.Renda;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
        name = "nome", 
        unique = false, 
        nullable = false)
    private String nome;

    @Column(
        name = "email",
        unique = true, 
        nullable = false)
    private String email;

    @Column(
        name = "senha",
        unique = false, 
        nullable = false)
    private String senha;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Renda> rendas;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Gasto> gastos;

}
