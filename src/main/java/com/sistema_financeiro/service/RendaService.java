package com.sistema_financeiro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //MODELO DTO
    public RendaBodyDTO rendaDTO(renda rendas){
        return new RendaBodyDTO(
            rendas.getValor(),
            rendas.getDataHora()
        );
    }

    //POST
    public RendaBodyDTO createRenda(Integer userId, RendaBodyDTO dto){
        user usuario = userRepository.findById(userId)
            .orElseThrow(()-> new RuntimeException("Not Found"));
        renda rendas = new renda();
        rendas.setUser(usuario);
        rendas.setValor(dto.valor());
        rendas.setDataHora(dto.dataHora());
        rendaRepository.save(rendas);
        
        return rendaDTO(rendas);
    }

    //GET BUSCAR RENDAS NO USER(ID)
    public List<renda> listagemId(Integer userId){
        
        userRepository.findById(userId)
            .orElseThrow(()-> new RuntimeException("Not Found"));

        List<renda> buscarRendas = rendaRepository.findByUserId(userId);

        if(buscarRendas.isEmpty()){throw new RuntimeException("Not Found");}

        return buscarRendas;
    }

    //GET BUSCAR RENDA COM BASE NO ID
    public RendaBodyDTO listagemIdRendas(Integer id){
        renda rendas = rendaRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Not Found"));  

        return rendaDTO(rendas);
    }

    //PUT EDITAR ROTA COM BASE NO ID
    public RendaBodyDTO editarRenda(Integer id, RendaBodyDTO dto){
        renda rendas = rendaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Not Found"));

        rendas.setValor(dto.valor());
        rendas.setDataHora(dto.dataHora());
        rendaRepository.save(rendas);

        return rendaDTO(rendas);
    }

    //DELETE COM BASE NO ID
    @Transactional
    public void deletarRenda(Integer id){
        renda rendas = rendaRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("not found"));
        rendaRepository.delete(rendas);
        
    }
}
