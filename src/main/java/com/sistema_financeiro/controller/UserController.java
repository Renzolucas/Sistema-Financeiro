package com.sistema_financeiro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_financeiro.entity.renda.dto.RendaBodyDTO;
import com.sistema_financeiro.entity.renda.renda;
import com.sistema_financeiro.entity.user.dto.CreateUserBodyDTO;
import com.sistema_financeiro.entity.user.dto.ResponseUserAndRendaDTO;
import com.sistema_financeiro.entity.user.dto.ResponseUserDTO;
import com.sistema_financeiro.entity.user.user;
import com.sistema_financeiro.service.RendaService;
import com.sistema_financeiro.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final RendaService rendaService;
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserBodyDTO dto){

        try { 
            user create = userService.createUser(dto);
            ResponseUserDTO responseUserDTO = new ResponseUserDTO(
                create.getNome(),
                create.getEmail()
        ); 
            return ResponseEntity.created(null).body(responseUserDTO);

        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        try{userService.deleteUser(id);
            return ResponseEntity.noContent().build();
            
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserAndRendaDTO> getUserById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody ResponseUserDTO dto){
        try{
            ResponseUserDTO user = userService.updateUser(id, dto);
            return ResponseEntity.ok().body(user);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //POST RENDA
    @PostMapping("/{userId}/rendas")
    public ResponseEntity<?> createRenda(
        @PathVariable Integer userId,
        @Valid @RequestBody RendaBodyDTO dto
    ){
        try{
            renda create = rendaService.createRenda(userId, dto);
            RendaBodyDTO rendaDto = new RendaBodyDTO(
                create.getValor(),
                create.getDataHora()
            );
            return ResponseEntity.created(null).body(rendaDto);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{userId}/rendas")
    public ResponseEntity<List<?>> buscarRenda(@PathVariable Integer userId){
        List<?> rendaLista = rendaService.listagemId(userId);
        return ResponseEntity.ok().body(rendaLista);
    }
}
