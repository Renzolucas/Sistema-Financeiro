package com.sistema_financeiro.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    //para fins educativos, essa chave vai ficar aqui, analisador!!!
    private final String CHAVE_SECRETA = "minha-chave-secreta-bem-longa-para-o-projeto-educacional-123456";

    private SecretKey getChave(){
        return Keys.hmacShaKeyFor(CHAVE_SECRETA.getBytes());
    }

    //gera novo token contendo email e uma data expirante
    public String gerarToken(String email){
        return Jwts.builder()
        //CRACHA
                //o identificador
                .subject(email)//dono do token

                //data de inicio/expiração do cracha
                .issuedAt(new Date())//data de criacao
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 *60 *10))

                //assinatura pra saber que foi esse sistema qm o criou
                .signWith(getChave())
                .compact();
    }
    //Extrai o email e um token ja existente
    public String extrairEmail(String token){
        //Aqui vai ser a etapa aonde o back-end verifica toda vez que o usuario faz uma requisição
        //ele recebe so uma string token, aqui será aberto o arquivo e "lido" todo conteudo dentro de token 
        return Jwts.parser()
                .verifyWith(getChave())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    //verifica se o token e valido (assinatura true + data nao expirou)
    public boolean validarToken(String token){
        try{
            Jwts.parser()
                    .verifyWith(getChave())
                    .build()
                    .parseSignedClaims(token);
                    return true;
        }catch(Exception e){
            return false;
        }
    }
}
