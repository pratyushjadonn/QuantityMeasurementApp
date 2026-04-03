package QuantityMeasurmentApp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import QuantityMeasurmentApp.dto.AuthResponseDTO;
import QuantityMeasurmentApp.dto.LoginRequestDTO;
import QuantityMeasurmentApp.dto.RegisterRequestDTO;
// FIX: AuthProvider import missing tha — compile error fix
import QuantityMeasurmentApp.entity.AuthProvider;
import QuantityMeasurmentApp.entity.User;
import QuantityMeasurmentApp.repository.UserRepository;
import QuantityMeasurmentApp.security.JwtUtil;
import QuantityMeasurmentApp.security.TokenBlacklist;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;
    private final TokenBlacklist tokenBlacklist;

    public AuthServiceImpl(UserRepository repo, PasswordEncoder encoder, JwtUtil jwt, TokenBlacklist tokenBlacklist) {
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
      

        System.out.println("🔥 JWT TOKEN: " + token);
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

        System.out.println("🔥 JWT TOKEN: " + token);
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