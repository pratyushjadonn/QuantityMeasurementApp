package com.quantityapp.auth.controller;

import com.quantityapp.auth.dto.AuthResponseDTO;
import com.quantityapp.auth.dto.LoginRequestDTO;
import com.quantityapp.auth.dto.RegisterRequestDTO;
import com.quantityapp.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO dto) {
        return ResponseEntity.ok(service.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(service.login(dto));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        return ResponseEntity.ok(service.logout(request));
    }

    // Google OAuth2 redirect
    @GetMapping("/google")
    public void redirectToGoogle(jakarta.servlet.http.HttpServletResponse response)
            throws java.io.IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }
}
