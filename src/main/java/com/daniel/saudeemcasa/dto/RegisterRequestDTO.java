package com.daniel.saudeemcasa.dto;

import com.daniel.saudeemcasa.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(

        @NotBlank(message = "O nome completo é obrigatório.")
        String nomeCompleto,

        @NotBlank(message = "O CPF é obrigatório.")
        @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.") // Validação de tamanho para CPF
        String cpf,

        @NotBlank(message = "O telefone é obrigatório.")
        String telefone,

        @NotBlank(message = "O endereço é obrigatório.")
        String endereco,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "E-mail inválido.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String password,

        @NotNull(message = "O tipo de usuário é obrigatório (CLIENTE ou PROFISSIONAL).")
        UserRole role,

        String coren // Opcional, apenas para Profissionais

) {
}