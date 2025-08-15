package com.daniel.saudeemcasa.dto;

import com.daniel.saudeemcasa.model.StatusAgendamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AgendamentoRequestDTO(
        @NotNull(message = "O ID do cliente é obrigatório.")
        Long clienteId,

        // O profissional pode ser opcional em uma solicitação inicial,
        // pois a plataforma irá fazer o "matching" depois.
        Long profissionalId,

        @NotNull(message = "O ID do serviço é obrigatório.")
        Long servicoId,

        @NotNull(message = "A data e hora do agendamento são obrigatórias.")
        LocalDateTime dataHoraAgendamento,

        @NotBlank(message = "O endereço do atendimento é obrigatório.")
        String enderecoAtendimento,

        // O status é definido internamente pela aplicação, mas pode ser
        // enviado na requisição para um controle mais explícito.
        @NotNull(message = "O status do agendamento é obrigatório.")
        StatusAgendamento status,

        BigDecimal valorTotal,

        String observacoes
) {
    // Aqui você pode adicionar validações personalizadas se necessário.
}
