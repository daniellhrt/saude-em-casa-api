package com.daniel.saudeemcasa.dto;

import jakarta.validation.constraints.NotNull;

public record AceiteAgendamentoRequestDTO(
        @NotNull(message = "O ID do agendamento é obrigatório.")
        Long agendamentoId,

        @NotNull(message = "O ID do profissional é obrigatório.")
        Long profissionalId
) {}
