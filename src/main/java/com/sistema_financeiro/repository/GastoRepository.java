package com.sistema_financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema_financeiro.entity.gasto.Gasto;

public interface GastoRepository extends JpaRepository<Gasto, Integer> {
    List<Gasto> findByUserId(Integer id);
}
