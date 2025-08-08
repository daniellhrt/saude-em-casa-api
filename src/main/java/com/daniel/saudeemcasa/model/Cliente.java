package com.daniel.saudeemcasa.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CLIENTE")
@NoArgsConstructor
public class Cliente extends User {

    public Cliente(String email, String password, String nomeCompleto,
                   String cpf, String telefone, String endereco) {
        super(email, password, nomeCompleto, cpf, telefone, endereco, UserRole.CLIENTE);
    }
}
