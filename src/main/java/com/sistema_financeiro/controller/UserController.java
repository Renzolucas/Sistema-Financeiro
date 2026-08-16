package com.sistema_financeiro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_financeiro.entity.gasto.dto.GastoBodyDTO;
import com.sistema_financeiro.entity.renda.Renda;
import com.sistema_financeiro.entity.renda.dto.RendaBodyDTO;
import com.sistema_financeiro.entity.user.dto.CreateUserBodyDTO;
import com.sistema_financeiro.entity.user.dto.ResponseUserAndRendaDTO;
import com.sistema_financeiro.entity.user.dto.ResponseUserDTO;
import com.sistema_financeiro.service.GastoService;
import com.sistema_financeiro.service.RendaService;
import com.sistema_financeiro.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final RendaService rendaService;
    private final GastoService gastoService;
    @PostMapping
    public ResponseEntity<ResponseUserDTO> createUser(@Valid @RequestBody CreateUserBodyDTO dto){
            ResponseUserDTO create = userService.createUser(dto);
            return ResponseEntity.created(null).body(create);
        
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserAndRendaDTO> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> updateUser(
        @PathVariable Integer id, 
        @Valid @RequestBody ResponseUserDTO dto
    ){
            ResponseUserDTO user = userService.updateUser(id, dto);
            return ResponseEntity.ok().body(user);
    }
    //POST RENDA
    @PostMapping("/{userId}/rendas")
    public ResponseEntity<RendaBodyDTO> createRenda(
        @PathVariable Integer userId,
        @Valid @RequestBody RendaBodyDTO dto
    ){
            RendaBodyDTO create = rendaService.createRenda(userId, dto);
            return ResponseEntity.created(null).body(create);
    }
    //GET BUSCAR TODAS RENDAS DE ID USER
    @GetMapping("/{userId}/rendas")
    public ResponseEntity<List<Renda>> buscarRenda(@PathVariable Integer userId){
        List<Renda> rendaLista = rendaService.listagemId(userId);
        return ResponseEntity.ok().body(rendaLista);
    }
    
    //POST GASTO
    @PostMapping("/{userId}/gastos")
    public ResponseEntity<GastoBodyDTO> createBody(
        @PathVariable Integer userId,
        @Valid @RequestBody GastoBodyDTO dto
    ){
        GastoBodyDTO create = gastoService.createBody(userId, dto);
        return ResponseEntity.created(null).body(create);
    }

    //GET GASTO
    @GetMapping("/{userId}/gastos")
    public ResponseEntity<List<GastoBodyDTO>> listagemDeGastoPorIdUser(@PathVariable Integer userId){
        List<GastoBodyDTO> listagem = gastoService.listagemIdDeUser(userId);
        return ResponseEntity.ok().body(listagem);
    }
}
