package com.sistema_financeiro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema_financeiro.entity.user.dto.CreateUserBodyDTO;
import com.sistema_financeiro.entity.user.user;
import com.sistema_financeiro.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public user createUser(CreateUserBodyDTO dto){
        user user = new user();
        //VALIDAÇÃO DE EMAIL REPETIDO
        Optional<user> emailRepetido = userRepository.findByEmail(dto.email());
        if(emailRepetido.isPresent()){
            throw new RuntimeException("Email já cadastrado");
        }
        user.setNome(dto.nome());
        user.setEmail(dto.email()); 
        user.setSenha(dto.senha());


        return userRepository.save(user);
    }
}
