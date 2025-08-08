package com.daniel.saudeemcasa.service;

import com.daniel.saudeemcasa.dto.LoginRequestDTO;
import com.daniel.saudeemcasa.dto.LoginResponseDTO;
import com.daniel.saudeemcasa.dto.RegisterRequestDTO;
import com.daniel.saudeemcasa.exception.InvalidCredentialsException;
import com.daniel.saudeemcasa.exception.ResourceAlreadyExistsException;
import com.daniel.saudeemcasa.exception.ValidationException;
import com.daniel.saudeemcasa.infra.security.TokenService;
import com.daniel.saudeemcasa.model.Cliente;
import com.daniel.saudeemcasa.model.Profissional;
import com.daniel.saudeemcasa.model.User;
import com.daniel.saudeemcasa.model.UserRole;
import com.daniel.saudeemcasa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public LoginResponseDTO login(LoginRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        try {
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return new LoginResponseDTO(token, data.email());
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("E-mail ou senha inválidos.");
        }
    }

    public User register(RegisterRequestDTO data) {
        if (this.userRepository.findByEmail(data.email()).isPresent()) {
            throw new ResourceAlreadyExistsException("E-mail já cadastrado!");
        }

        if (this.userRepository.findByCpf(data.cpf()).isPresent()) {
            throw new ResourceAlreadyExistsException("CPF já cadastrado!");
        }

        String encryptedPassword = passwordEncoder.encode(data.password());

        User newUser;
        if (data.role() == UserRole.CLIENTE) {
            newUser = new Cliente(data.email(), encryptedPassword, data.nomeCompleto(),
                    data.cpf(), data.telefone(), data.endereco());
        } else if (data.role() == UserRole.PROFISSIONAL) {
            if (data.coren() == null || data.coren().isBlank()) {
                throw new ValidationException("COREN é obrigatório para Profissionais!");
            }
            newUser = new Profissional(data.email(), encryptedPassword, data.nomeCompleto(),
                    data.cpf(), data.telefone(), data.endereco(), data.coren());
        } else {
            throw new ValidationException("Tipo de usuário inválido.");
        }

        return this.userRepository.save(newUser);
    }
}