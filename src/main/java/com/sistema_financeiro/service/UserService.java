package com.sistema_financeiro.service;

import org.springframework.stereotype.Service;

import com.sistema_financeiro.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
