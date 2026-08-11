package com.sistema_financeiro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema_financeiro.entity.user.dto.CreateUserBodyDTO;
import com.sistema_financeiro.entity.user.dto.ResponseUserAndRendaDTO;
import com.sistema_financeiro.entity.user.dto.ResponseUserDTO;
import com.sistema_financeiro.entity.user.user;
import com.sistema_financeiro.repository.RendaRepository;
import com.sistema_financeiro.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final RendaRepository rendaRepository;
    
    //POST
    public user createUser(CreateUserBodyDTO dto){
        //VERIFICO SE NADA É NULL
        if(dto.nome() == null || dto.email() == null || dto.senha() == null){throw new RuntimeException("Todos os campos são obrigatórios");}

        //VALIDAÇÃO DE EMAIL REPETIDO
        Optional<user> emailRepetido = userRepository.findByEmail(dto.email());
        if(emailRepetido.isPresent()){throw new RuntimeException("Email já cadastrado");}
        //CRIO UM ESPAÇO PARA O NOVO OBJETO USER
        user user = new user();

        //SETO OS CAMPOS COM O DTO QUE VEIO DA WEB
        user.setNome(dto.nome());
        user.setEmail(dto.email()); 
        user.setSenha(dto.senha());

        //SALVO O NOVO OBJETO USER NO BANCO DE DADOS E RETORNO ELE
        return userRepository.save(user);
    }

    //DELETE
    @Transactional
    public void deleteUser(Integer id){
        Optional<user> user = userRepository.findById(id);

        if(user.isPresent() && user.get().getId().equals(id)){
            userRepository.deleteById(id);

        }else{throw new RuntimeException("Usuário não encontrado");}   
    }

    //BUSCAR COM BASE EM ID
    public ResponseUserAndRendaDTO getUserById(Integer id){
        //VERIFICO SE O ID É NULO
        if(id == null){throw new RuntimeException("Id não pode ser nulo");}

        //BUSCO O USUÁRIO NO BANCO DE DADOS, SE NÃO ENCONTRAR LANÇO UMA EXCEÇÃO
        user userBruto = userRepository.findById(id)
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
        user user = userRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Usuário não encontrado"));
        
        user.setNome(dto.nome());
        user.setEmail(dto.email());
        userRepository.save(user);

        return new ResponseUserDTO(
            user.getNome(),
            user.getEmail()
        );
    }
}
