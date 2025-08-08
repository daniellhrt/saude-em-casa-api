package com.daniel.saudeemcasa.dto;

public record LoginResponseDTO(
        String token,
        String email // Opcional, mas útil para o cliente saber de qual usuário é o token
) {}