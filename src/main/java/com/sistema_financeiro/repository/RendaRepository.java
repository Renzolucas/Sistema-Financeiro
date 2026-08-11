package com.sistema_financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema_financeiro.entity.renda.renda;

public interface RendaRepository extends JpaRepository<renda, Integer>{
    
}
