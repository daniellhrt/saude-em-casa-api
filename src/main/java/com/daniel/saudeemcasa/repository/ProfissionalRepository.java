package com.daniel.saudeemcasa.repository;

import com.daniel.saudeemcasa.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    // Método para encontrar um profissional pelo coren
    Optional<Profissional> findByCoren(String coren);
}
