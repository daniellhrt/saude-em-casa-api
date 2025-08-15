package com.daniel.saudeemcasa.dto;

import com.daniel.saudeemcasa.model.Agendamento;
import com.daniel.saudeemcasa.model.StatusAgendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        Long id,
        Long clienteId,
        Long profissionalId,
        Long servicoId,
        LocalDateTime dataHoraAgendamento,
        String enderecoAtendimento,
        StatusAgendamento status,
        BigDecimal valorTotal,
        String observacoes
) {
    public AgendamentoResponseDTO(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getCliente() != null ? agendamento.getCliente().getId() : null,
                agendamento.getProfissional() != null ? agendamento.getProfissional().getId() : null,
                agendamento.getServico() != null ? agendamento.getServico().getId() : null,
                agendamento.getDataHoraAgendamento(),
                agendamento.getEnderecoAtendimento(),
                agendamento.getStatus(),
                agendamento.getValorTotal(),
                agendamento.getObservacoes()
        );
    }
}