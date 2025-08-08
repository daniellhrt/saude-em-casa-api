package com.daniel.saudeemcasa.controller;

import com.daniel.saudeemcasa.dto.LoginRequestDTO;
import com.daniel.saudeemcasa.dto.LoginResponseDTO;
import com.daniel.saudeemcasa.dto.RegisterRequestDTO;
import com.daniel.saudeemcasa.model.User;
import com.daniel.saudeemcasa.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        LoginResponseDTO response = authService.login(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterRequestDTO data) {
        User newUser = authService.register(data);
        return ResponseEntity.ok(newUser);
    }

}
