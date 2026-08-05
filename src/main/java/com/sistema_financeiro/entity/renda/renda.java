package com.sistema_financeiro.entity.renda;

import java.time.LocalDateTime;

import com.sistema_financeiro.entity.user.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Entity(name = "renda")
@Table(name = "renda")
@Getter
@Setter
@AllArgsConstructor

public class renda {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    private float valor;

    private LocalDateTime dataHora;

    private user user;

}
