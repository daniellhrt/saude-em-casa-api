
package com.daniel.saudeemcasa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/protected")
    public ResponseEntity<String> getProtectedData() {
        return ResponseEntity.ok("Você acessou um endpoint protegido! O token JWT está funcionando.");
    }

    @GetMapping("/public")
    public ResponseEntity<String> getPublicData() {
        return ResponseEntity.ok("Este é um endpoint público. Nenhum token é necessário.");
    }
}