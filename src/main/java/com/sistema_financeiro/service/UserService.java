package com.sistema_financeiro.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema_financeiro.entity.user.dto.CreateUserBodyDTO;
import com.sistema_financeiro.entity.user.dto.ResponseUserAndRendaDTO;
import com.sistema_financeiro.entity.user.dto.ResponseUserDTO;
import com.sistema_financeiro.entity.user.User;
import com.sistema_financeiro.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    //MODELO DTO
    public ResponseUserDTO userDTO(User users){
            return new ResponseUserDTO(
            users.getNome(),
            users.getEmail()
        ); 
    }

    //POST
    public ResponseUserDTO createUser(CreateUserBodyDTO dto){
        //VERIFICO SE NADA É NULL
        if(dto.nome() == null || dto.email() == null || dto.senha() == null){throw new RuntimeException("Todos os campos são obrigatórios");}

        //VALIDAÇÃO DE EMAIL REPETIDO
        Optional<User> emailRepetido = userRepository.findByEmail(dto.email());
        if(emailRepetido.isPresent()){throw new RuntimeException("Email já cadastrado");}
        //CRIO UM ESPAÇO PARA O NOVO OBJETO USER
        User users = new User();

        //SETO OS CAMPOS COM O DTO QUE VEIO DA WEB
        users.setNome(dto.nome());
        users.setEmail(dto.email()); 
        users.setSenha(dto.senha());

        //SALVO O NOVO OBJETO USER NO BANCO DE DADOS E RETORNO ELE
        userRepository.save(users);
        return userDTO(users);
    }

    //BUSCAR COM BASE EM ID
    public ResponseUserAndRendaDTO getUserById(Integer id){
        //VERIFICO SE O ID É NULO
        if(id == null){throw new RuntimeException("Id não pode ser nulo");}

        //BUSCO O USUÁRIO NO BANCO DE DADOS, SE NÃO ENCONTRAR LANÇO UMA EXCEÇÃO
        User userBruto = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new ResponseUserAndRendaDTO(
            userBruto.getNome(),
            userBruto.getEmail(),
            userBruto.getRendas(),
            userBruto.getGastos()
        );
    }

    //PUT
    public ResponseUserDTO updateUser(Integer id, ResponseUserDTO dto){
        User users = userRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Usuário não encontrado"));
        
        users.setNome(dto.nome());
        users.setEmail(dto.email());
        userRepository.save(users);

        return userDTO(users);
    }

    //DELETE
    @Transactional
    public void deleteUser(Integer id){
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent() && user.get().getId().equals(id)){
            userRepository.deleteById(id);

        }else{throw new RuntimeException("Usuário não encontrado");}   
    }

}
