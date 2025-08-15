package com.daniel.saudeemcasa.repository;

import com.daniel.saudeemcasa.model.Agendamento;
import com.daniel.saudeemcasa.model.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {


     List<Agendamento> findByStatus(StatusAgendamento statusAgendamento);

}
