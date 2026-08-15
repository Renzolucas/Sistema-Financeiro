package com.sistema_financeiro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema_financeiro.entity.renda.dto.RendaBodyDTO;
import com.sistema_financeiro.entity.renda.renda;
import com.sistema_financeiro.entity.user.user;
import com.sistema_financeiro.repository.RendaRepository;
import com.sistema_financeiro.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RendaService {
    private final RendaRepository rendaRepository;
    private final UserRepository userRepository;

    //POST
    public renda createRenda(Integer userId, RendaBodyDTO dto){
        user usuario = userRepository.findById(userId)
            .orElseThrow(()-> new RuntimeException("Not Found"));
        renda renda = new renda();
        renda.setUser(usuario);
        renda.setValor(dto.valor());
        renda.setDataHora(dto.dataHora());
        
        return rendaRepository.save(renda);
    }
    //GET BUSCAR RENDAS NO USER(ID)
    public List<renda> listagemId(Integer userId){
        
        userRepository.findById(userId)
            .orElseThrow(()-> new RuntimeException("Not Found"));
        List<renda> buscarRendas = rendaRepository.findByUserId(userId);
        if(buscarRendas.isEmpty()){
            throw new RuntimeException("Not Found");
        }
        return buscarRendas;
    }
    //GET BUSCAR RENDA COM BASE NO ID
    public RendaBodyDTO listagemIdRendas(Integer id){
        renda rendas = rendaRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Not Found"));        
        return new RendaBodyDTO(
            rendas.getValor(),
            rendas.getDataHora()
        );
    }
    //PUT EDITAR ROTA COM BASE NO ID
    public RendaBodyDTO editarRenda(Integer id, RendaBodyDTO dto){
        renda buscarRenda = rendaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Not Found"));

        buscarRenda.setValor(dto.valor());
        buscarRenda.setDataHora(dto.dataHora());
        rendaRepository.save(buscarRenda);

        return new RendaBodyDTO(
            buscarRenda.getValor(),
            buscarRenda.getDataHora()
        );
    }
}
