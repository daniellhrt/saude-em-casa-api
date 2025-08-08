// src/main/java/com/daniel/saudeemcasa/infra/security/TokenService.java
package com.daniel.saudeemcasa.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.daniel.saudeemcasa.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("saude-em-casa-api") // Emissor do token
                    .withSubject(user.getEmail()) // Identifica o usuário principal
                    .withExpiresAt(genExpirationDate()) // Define a data de expiração
                    .sign(algorithm); // Assina o token com o algoritmo e a chave secreta
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("saude-em-casa-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return ""; // Retorna string vazia se o token for inválido
        }
    }

    private Instant genExpirationDate() {
        // Define o tempo de expiração em 2 horas.
        // O ZoneOffset.of("-03:00") define o fuso horário de Brasília.
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}