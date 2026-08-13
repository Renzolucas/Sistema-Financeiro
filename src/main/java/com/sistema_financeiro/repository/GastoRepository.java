package com.sistema_financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema_financeiro.entity.gasto.gasto;

public interface GastoRepository extends JpaRepository<gasto, Integer> {
    
}
