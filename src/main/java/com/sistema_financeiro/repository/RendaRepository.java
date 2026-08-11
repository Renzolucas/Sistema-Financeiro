package com.sistema_financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema_financeiro.entity.renda.renda;

public interface RendaRepository extends JpaRepository<renda, Integer>{
    List<renda> findByUserId(Integer id);
}
