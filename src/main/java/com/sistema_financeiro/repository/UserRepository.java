package com.sistema_financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema_financeiro.entity.user.user;
@Repository
public interface UserRepository extends JpaRepository<user, Integer>{
    
}
