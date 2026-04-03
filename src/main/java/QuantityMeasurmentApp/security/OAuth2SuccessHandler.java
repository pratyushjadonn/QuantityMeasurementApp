package QuantityMeasurmentApp.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import QuantityMeasurmentApp.entity.AuthProvider;
import QuantityMeasurmentApp.entity.User;
import QuantityMeasurmentApp.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public OAuth2SuccessHandler(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        // Null safe name
        if (name == null || name.isBlank()) {
            name = email;
        }

        final String finalName = name;

        // Save user if not exists
        userRepository.findByEmail(email).orElseGet(() -> {
            User user = new User();
            user.setEmail(email);
            user.setName(finalName);
            user.setProvider(AuthProvider.GOOGLE);
            return userRepository.save(user);
        });

        // Generate JWT
        String token = jwtUtil.generateToken(email);

        clearAuthenticationAttributes(request);

        // 🔥 RETURN RESPONSE DIRECTLY (NO REDIRECT)
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonResponse = "{"
                + "\"token\": \"" + token + "\","
                + "\"name\": \"" + finalName + "\","
                + "\"email\": \"" + email + "\""
                + "}";

        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }
}