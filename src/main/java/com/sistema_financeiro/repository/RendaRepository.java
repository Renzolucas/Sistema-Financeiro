package com.sistema_financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema_financeiro.entity.renda.Renda;

public interface RendaRepository extends JpaRepository<Renda, Integer>{
    List<Renda> findByUserId(Integer id);
}
