package com.sistema_financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_financeiro.entity.gasto.dto.GastoBodyDTO;
import com.sistema_financeiro.service.GastoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/gastos")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class GastoController {
    private final GastoService gastoService;

    //GET POR ID
    @GetMapping("/{id}")
    public ResponseEntity<GastoBodyDTO> buscarGasto(@PathVariable Integer id){
        GastoBodyDTO gastoBuscado = gastoService.buscarGastoPorId(id);
        return ResponseEntity.ok().body(gastoBuscado);
    }

    //PUT POR ID
    @PutMapping("/{id}")
    public ResponseEntity<GastoBodyDTO> editarGasto(
        @PathVariable Integer id,
        @Valid @RequestBody GastoBodyDTO dto
    ){
        GastoBodyDTO gastoEditado = gastoService.editarGastoPorId(id, dto);
        return ResponseEntity.created(null).body(gastoEditado);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGastos(@PathVariable Integer id){
        gastoService.deletarGasto(id);
        return ResponseEntity.noContent().build();
    }
}
