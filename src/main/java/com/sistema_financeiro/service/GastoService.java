package com.sistema_financeiro.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema_financeiro.entity.gasto.dto.GastoBodyDTO;
import com.sistema_financeiro.entity.gasto.gasto;
import com.sistema_financeiro.entity.user.user;
import com.sistema_financeiro.repository.GastoRepository;
import com.sistema_financeiro.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GastoService {
    private final GastoRepository gastoRepository;
    private final UserRepository userRepository;
    
    //MODELO DO DTO
    public static GastoBodyDTO fazerDTO(gasto gastos){
        return new GastoBodyDTO(
            gastos.getNome(),
            gastos.getValor(),
            gastos.getCategoria(),
            gastos.getDataHora()
        );
    }

    //POST GASTO
    public GastoBodyDTO createBody(Integer id, GastoBodyDTO dto){
        user usuario = userRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Not found User"));
        
        gasto gastos = new gasto();
        gastos.setUser(usuario);
        gastos.setNome(dto.nome());
        gastos.setValor(dto.valor());
        gastos.setCategoria(dto.categoria());
        gastos.setDataHora(dto.dataHora());

        gastoRepository.save(gastos);
        return fazerDTO(gastos);
    }

    //GET COM BASE NO ID GASTO
    public GastoBodyDTO buscarGastoPorId(Integer id){
        gasto gastos = gastoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("not found"));
        
        return fazerDTO(gastos);
    }

    //PUT COM BASE NO ID GASTO
    public GastoBodyDTO editarGastoPorId(Integer id, GastoBodyDTO dto){
        gasto gastos = gastoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("not found"));

        VerificadorDeNulos.atualizarSeNaoNulo(dto.nome(), gastos::setNome);
        VerificadorDeNulos.atualizarSeNaoNulo(dto.valor(), gastos::setValor);
        VerificadorDeNulos.atualizarSeNaoNulo(dto.categoria(), gastos::setCategoria);
        VerificadorDeNulos.atualizarSeNaoNulo(dto.dataHora(), gastos::setDataHora);
        
        gastoRepository.save(gastos);

        return fazerDTO(gastos);
    }

    //DELETE
    @Transactional
    public void deletarGasto(Integer id){
        gasto gastos = gastoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Not found Gasto"));
        gastoRepository.delete(gastos);
    }


}
