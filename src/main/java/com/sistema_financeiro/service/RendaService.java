package com.sistema_financeiro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema_financeiro.entity.renda.dto.RendaBodyDTO;
import com.sistema_financeiro.entity.renda.renda;
import com.sistema_financeiro.entity.user.user;
import com.sistema_financeiro.repository.RendaRepository;
import com.sistema_financeiro.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RendaService {
    private final RendaRepository rendaRepository;
    private final UserRepository userRepository;

    //POST
    public renda createRenda(Integer userId, RendaBodyDTO dto){
        user usuario = userRepository.findById(userId)
            .orElseThrow(()-> new RuntimeException("Usuario nao encontrado"));
        renda renda = new renda();
        renda.setUser(usuario);
        renda.setValor(dto.valor());
        renda.setDataHora(dto.dataHora());
        
        return rendaRepository.save(renda);
    }
    //GET COM BASE NO ID DE USER
    public List<?> listagemId(Integer userId){
        
        userRepository.findById(userId)
            .orElseThrow(()-> new RuntimeException("Usuario nao encontrado"));
        List<renda> buscarRendas = rendaRepository.findByUserId(userId);
        if(buscarRendas.isEmpty()){
            throw new RuntimeException("Not Found");
        }
        return buscarRendas;
    }
}
