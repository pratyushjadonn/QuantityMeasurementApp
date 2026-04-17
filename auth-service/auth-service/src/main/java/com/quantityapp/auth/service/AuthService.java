package com.quantityapp.auth.service;

import com.quantityapp.auth.dto.AuthResponseDTO;
import com.quantityapp.auth.dto.LoginRequestDTO;
import com.quantityapp.auth.dto.RegisterRequestDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    AuthResponseDTO register(RegisterRequestDTO dto);
    AuthResponseDTO login(LoginRequestDTO dto);
    String logout(HttpServletRequest request);
}
