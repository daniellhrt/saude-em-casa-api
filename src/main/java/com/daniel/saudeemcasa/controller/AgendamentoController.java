package com.daniel.saudeemcasa.controller;

import com.daniel.saudeemcasa.dto.AceiteAgendamentoRequestDTO;
import com.daniel.saudeemcasa.dto.AgendamentoRequestDTO;
import com.daniel.saudeemcasa.dto.AgendamentoResponseDTO;
import com.daniel.saudeemcasa.model.Agendamento;
import com.daniel.saudeemcasa.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    // O método agora recebe o DTO e retorna a entidade completa

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> criarAgendamento(@RequestBody AgendamentoRequestDTO dados) {
        Agendamento agendamentoSalvo = agendamentoService.criarAgendamento(dados);
        return ResponseEntity.ok(new AgendamentoResponseDTO(agendamentoSalvo));
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<AgendamentoResponseDTO>> listarPendentes() {
        List<Agendamento> agendamentos = agendamentoService.listarPendentes();
        List<AgendamentoResponseDTO> dtos = agendamentos.stream()
                .map(AgendamentoResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PatchMapping("/aceitar/{id}")
    public ResponseEntity<AgendamentoResponseDTO> aceitarAgendamento(@PathVariable Long id,
                                                                     @RequestBody AceiteAgendamentoRequestDTO dados) {
        Agendamento agendamentoAceito = agendamentoService.aceitarAgendamento(id, dados);
        return ResponseEntity.ok(new AgendamentoResponseDTO(agendamentoAceito));
    }

    @PatchMapping("/encerrar/{id}")
    public ResponseEntity<AgendamentoResponseDTO> encerrarAgendamento(@PathVariable Long id) {
        Agendamento agendamentoEncerrado = agendamentoService.encerrarAgendamento(id);
        return ResponseEntity.ok(new AgendamentoResponseDTO(agendamentoEncerrado));
    }


}
