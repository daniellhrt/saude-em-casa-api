package com.daniel.saudeemcasa.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("PROFISSIONAL") // Valor para a coluna user_type quando for um Profissional
@Getter
@Setter
@NoArgsConstructor
public class Profissional extends User {

    @Column(unique = true)
    private String coren;

    public Profissional(String email, String password, String nomeCompleto,
                        String cpf, String telefone, String endereco, String coren) {
        super(email, password, nomeCompleto, cpf, telefone, endereco, UserRole.PROFISSIONAL);
        this.coren = coren;
    }


}
