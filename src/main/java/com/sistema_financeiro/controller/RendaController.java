package com.sistema_financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_financeiro.entity.renda.dto.RendaBodyDTO;
import com.sistema_financeiro.service.RendaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/rendas")
@AllArgsConstructor
public class RendaController {
    private final RendaService rendaService;

    //GET 
    @GetMapping("/{id}")
    public ResponseEntity<RendaBodyDTO> buscarRenda(@PathVariable Integer id){
        RendaBodyDTO buscarRenda = rendaService.listagemIdRendas(id);
        return ResponseEntity.ok().body(buscarRenda);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<RendaBodyDTO> editarRenda(
        @PathVariable Integer id,
        @Valid @RequestBody RendaBodyDTO dto
        ){
        RendaBodyDTO editarRendas = rendaService.editarRenda(id, dto);
        return ResponseEntity.ok().body(editarRendas);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRendas(@PathVariable Integer id){
        rendaService.deletarRenda(id);
        return ResponseEntity.noContent().build();
    }
}
