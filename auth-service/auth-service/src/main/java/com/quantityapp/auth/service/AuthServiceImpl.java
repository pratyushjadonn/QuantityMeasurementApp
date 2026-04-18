package com.quantityapp.auth.service;

import com.quantityapp.auth.dto.AuthResponseDTO;
import com.quantityapp.auth.dto.LoginRequestDTO;
import com.quantityapp.auth.dto.RegisterRequestDTO;
import com.quantityapp.auth.entity.AuthProvider;
import com.quantityapp.auth.entity.User;
import com.quantityapp.auth.repository.UserRepository;
import com.quantityapp.auth.security.JwtUtil;
import com.quantityapp.auth.security.TokenBlacklist;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;
    private final TokenBlacklist tokenBlacklist;

    public AuthServiceImpl(UserRepository repo, PasswordEncoder encoder,
                           JwtUtil jwt, TokenBlacklist tokenBlacklist) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
        this.tokenBlacklist = tokenBlacklist;
    }

    @Override
    public AuthResponseDTO register(RegisterRequestDTO dto) {
        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with this email.");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setProvider(AuthProvider.LOCAL);
        repo.save(user);

        String token = jwt.generateToken(user.getEmail());
        return new AuthResponseDTO(user.getName(), user.getEmail(), token);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO dto) {
        User user = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials."));

        if (user.getProvider() != AuthProvider.LOCAL) {
            throw new RuntimeException("This account uses Google login. Please sign in with Google.");
        }

        if (user.getPassword() == null || !encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials.");
        }

        String token = jwt.generateToken(user.getEmail());
        return new AuthResponseDTO(user.getName(), user.getEmail(), token);
    }

    @Override
    public String logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklist.blacklistToken(token);
        }
        return "Logged out successfully.";
    }
}
