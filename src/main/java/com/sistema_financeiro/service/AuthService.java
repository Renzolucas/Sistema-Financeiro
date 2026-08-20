package com.sistema_financeiro.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistema_financeiro.entity.user.User;
import com.sistema_financeiro.repository.UserRepository;
import com.sistema_financeiro.security.JwtUtil;
import com.sistema_financeiro.security.dto.LoginRequestDTO;
import com.sistema_financeiro.security.dto.TokenResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public TokenResponseDTO login(LoginRequestDTO dto){
        User user = userRepository.findByEmail(dto.email())
            .orElseThrow(()-> new RuntimeException("Email nao cadastrado"));

        if(!passwordEncoder.matches(dto.senha(), user.getSenha())){//Verifica se a senha registrada junto a esse email e compativel com o existente no banco
            throw new RuntimeException("Credenciais invalidas!");

        }

        String token = jwtUtil.gerarToken(dto.email());
                return new TokenResponseDTO(token);
    }
}
