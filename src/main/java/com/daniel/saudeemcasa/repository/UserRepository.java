package com.daniel.saudeemcasa.repository;

import com.daniel.saudeemcasa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Método para encontrar um usuário pelo email
    Optional<User> findByEmail(String email);
    Optional<User> findByCpf(String cpf);

}
