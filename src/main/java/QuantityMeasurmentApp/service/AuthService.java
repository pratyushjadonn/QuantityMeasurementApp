package QuantityMeasurmentApp.service;

import QuantityMeasurmentApp.dto.AuthResponseDTO;
import QuantityMeasurmentApp.dto.LoginRequestDTO;
import QuantityMeasurmentApp.dto.RegisterRequestDTO;


import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

	AuthResponseDTO register(RegisterRequestDTO dto);

	AuthResponseDTO login(LoginRequestDTO dto);

	String logout(HttpServletRequest request);
}