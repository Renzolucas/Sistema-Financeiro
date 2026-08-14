package com.sistema_financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_financeiro.service.GastoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/gastos")
@AllArgsConstructor
public class GastoController {
    private final GastoService gastoService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGastos(@PathVariable Integer id){
        gastoService.deletarGasto(id);
        return ResponseEntity.noContent().build();
    }
}
