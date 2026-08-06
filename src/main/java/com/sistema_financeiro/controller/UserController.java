package com.sistema_financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_financeiro.entity.user.dto.CreateUserBodyDTO;
import com.sistema_financeiro.entity.user.dto.ResponseUserDTO;
import com.sistema_financeiro.entity.user.user;
import com.sistema_financeiro.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserBodyDTO dto){
        user create = userService.createUser(dto);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO(
            create.getNome(),
            create.getEmail()
        ); 
        return ResponseEntity.created(null).body(responseUserDTO);
    }

}
