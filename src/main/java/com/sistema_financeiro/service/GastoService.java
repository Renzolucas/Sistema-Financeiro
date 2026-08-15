package com.sistema_financeiro.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema_financeiro.entity.gasto.dto.GastoBodyDTO;
import com.sistema_financeiro.entity.gasto.gasto;
import com.sistema_financeiro.entity.user.user;
import com.sistema_financeiro.repository.GastoRepository;
import com.sistema_financeiro.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GastoService {
    private final GastoRepository gastoRepository;
    private final UserRepository userRepository;

    //POST GASTO
    public GastoBodyDTO createBody(Integer id, GastoBodyDTO dto){
        user usuario = userRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Not found User"));
        
        gasto gasto = new gasto();
        gasto.setUser(usuario);
        gasto.setNome(dto.nome());
        gasto.setValor(dto.valor());
        gasto.setCategoria(dto.categoria());
        gasto.setDataHora(dto.dataHora());

        gastoRepository.save(gasto);
        return new GastoBodyDTO(
            gasto.getNome(),
            gasto.getValor(),
            gasto.getCategoria(),
            gasto.getDataHora()
        );
    }

    //GET COM BASE NO ID GASTO
    public GastoBodyDTO buscarGastoPorId(Integer id){
        gasto gastos = gastoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("not found"));
        
        return new GastoBodyDTO(
            gastos.getNome(),
            gastos.getValor(),
            gastos.getCategoria(),
            gastos.getDataHora()
        );
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

        return new GastoBodyDTO(
            gastos.getNome(),  
            gastos.getValor(),
            gastos.getCategoria(), 
            gastos.getDataHora()
        );
    }

    //DELETE
    @Transactional
    public void deletarGasto(Integer id){
        gasto gastos = gastoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Not found Gasto"));
        gastoRepository.delete(gastos);
    }


}
