package com.sistema_financeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema_financeiro.entity.user.user;
public interface UserRepository extends JpaRepository<user, Integer>{
    Optional<user> findByEmail(String email);
}
