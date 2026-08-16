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
                .subject(email)//dono do token
                .issuedAt(new Date())//data de criacao
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 *60 *10))
                .signWith(getChave())
                .compact();
    }
    //Extrai o email de dentro e um token ja existente
    public String extrairEmail(String token){
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
