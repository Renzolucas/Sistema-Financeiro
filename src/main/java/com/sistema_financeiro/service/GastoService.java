package com.sistema_financeiro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema_financeiro.entity.gasto.dto.GastoBodyDTO;
import com.sistema_financeiro.entity.gasto.Gasto;
import com.sistema_financeiro.entity.user.User;
import com.sistema_financeiro.repository.GastoRepository;
import com.sistema_financeiro.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GastoService {
    private final GastoRepository gastoRepository;
    private final UserRepository userRepository;
    
    //MODELO DO DTO
    public static GastoBodyDTO fazerDTO(Gasto gastos){
        return new GastoBodyDTO(
            gastos.getNome(),
            gastos.getValor(),
            gastos.getCategoria(),
            gastos.getDataHora()
        );
    }

    //POST GASTO
    public GastoBodyDTO createBody(Integer id, GastoBodyDTO dto){
        User usuario = userRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Not found User"));
        
        Gasto gastos = new Gasto();
        gastos.setUser(usuario);
        gastos.setNome(dto.nome());
        gastos.setValor(dto.valor());
        gastos.setCategoria(dto.categoria());
        gastos.setDataHora(dto.dataHora());

        gastoRepository.save(gastos);
        return fazerDTO(gastos);
    }

    //GET COM BASE NO ID DE USER
    public List<GastoBodyDTO> listagemIdDeUser(Integer userId){
        userRepository.findById(userId)
            .orElseThrow(()-> new RuntimeException("not found user"));
        
        List<Gasto> gastos = gastoRepository.findByUserId(userId);
        if(gastos.isEmpty()){throw new RuntimeException("not found");}
        
        List<GastoBodyDTO>  novoDTOList = gastos.stream().map(c -> new GastoBodyDTO(
            c.getNome(),
            c.getValor(),
            c.getCategoria(),
            c.getDataHora()
        )).collect(Collectors.toList());
        return novoDTOList;
    }


    //GET COM BASE NO ID GASTO
    public GastoBodyDTO buscarGastoPorId(Integer id){
        Gasto gastos = gastoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("not found"));
        
        return fazerDTO(gastos);
    }

    //PUT COM BASE NO ID GASTO
    public GastoBodyDTO editarGastoPorId(Integer id, GastoBodyDTO dto){
        Gasto gastos = gastoRepository.findById(id)
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
        Gasto gastos = gastoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Not found Gasto"));
        gastoRepository.delete(gastos);
    }


}
