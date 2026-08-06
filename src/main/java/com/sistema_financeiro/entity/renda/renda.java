package com.sistema_financeiro.entity.renda;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema_financeiro.entity.user.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity(name = "renda")
@Table(name = "renda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class renda {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    private float valor;

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private user user;

}
