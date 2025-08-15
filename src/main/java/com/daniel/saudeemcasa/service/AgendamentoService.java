package com.daniel.saudeemcasa.service;

import com.daniel.saudeemcasa.dto.AceiteAgendamentoRequestDTO;
import com.daniel.saudeemcasa.dto.AgendamentoRequestDTO;
import com.daniel.saudeemcasa.exception.ResourceNotFoundException;
import com.daniel.saudeemcasa.exception.ValidationException;
import com.daniel.saudeemcasa.model.*;
import com.daniel.saudeemcasa.repository.AgendamentoRepository;
import com.daniel.saudeemcasa.repository.ServicoRepository;
import com.daniel.saudeemcasa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public Agendamento criarAgendamento(AgendamentoRequestDTO dados) {

        Cliente cliente = (Cliente) userRepository.findById(dados.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Servico servico = servicoRepository.findById(dados.servicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Profissional profissional = null;
        if (dados.profissionalId() != null) {
            profissional = (Profissional) userRepository.findById(dados.profissionalId())
                    .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        }

        Agendamento novoAgendamento = new Agendamento(
                null, // ID será gerado automaticamente
                cliente,
                profissional,
                servico,
                dados.dataHoraAgendamento(),
                dados.enderecoAtendimento(),
                dados.status(),
                dados.valorTotal(),
                dados.observacoes()
        );
        return agendamentoRepository.save(novoAgendamento);
    }


    public List<Agendamento> listarPendentes() {
        return agendamentoRepository.findByStatus(StatusAgendamento.PENDENTE);
    }

    public Agendamento aceitarAgendamento(Long id, AceiteAgendamentoRequestDTO dados) {

        //Busca o agendamento pelo ID
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado"));

        //Encontra o profissional associado ao agendamento
        Profissional profissional = (Profissional) userRepository.findById(dados.profissionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));

        //Atualiza os dados do agendamento
        if (agendamento.getStatus() != StatusAgendamento.PENDENTE) {
            throw new ValidationException("O agendamento já foi aceito, cancelado ou concluído.");
        }
        agendamento.setStatus(StatusAgendamento.CONFIRMADO);
        agendamento.setProfissional(profissional);

        return agendamentoRepository.save(agendamento);

    }

    public Agendamento encerrarAgendamento(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado"));

        if (agendamento.getStatus() != StatusAgendamento.CONFIRMADO) {
            throw new ValidationException("Apenas agendamentos confirmados podem ser encerrados.");
        }
        agendamento.setStatus(StatusAgendamento.CONCLUIDO);
        return agendamentoRepository.save(agendamento);
    }
}
