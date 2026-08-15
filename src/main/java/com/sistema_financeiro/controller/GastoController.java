package com.sistema_financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_financeiro.entity.gasto.dto.GastoBodyDTO;
import com.sistema_financeiro.service.GastoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/gastos")
@AllArgsConstructor
public class GastoController {
    private final GastoService gastoService;

    //GET POR ID
    @GetMapping("/{id}")
    public ResponseEntity<GastoBodyDTO> buscarGasto(@PathVariable Integer id){
        GastoBodyDTO gastoBuscado = gastoService.buscarGastoPorId(id);
        return ResponseEntity.ok().body(gastoBuscado);
    }


    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGastos(@PathVariable Integer id){
        gastoService.deletarGasto(id);
        return ResponseEntity.noContent().build();
    }
}
