package com.sistema_financeiro.entity.gasto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema_financeiro.entity.gasto.enums.Categoria;
import com.sistema_financeiro.entity.user.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "gasto")
@Table(name = "gasto")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class gasto {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private Float valor;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private user user;
}
