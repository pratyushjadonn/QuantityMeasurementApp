package com.quantityapp.auth.security;

import com.quantityapp.auth.entity.AuthProvider;
import com.quantityapp.auth.entity.User;
import com.quantityapp.auth.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
        String name  = oauthUser.getAttribute("name");
        if (name == null || name.isBlank()) name = email;
        final String finalName = name;

        userRepository.findByEmail(email).orElseGet(() -> {
            User user = new User();
            user.setEmail(email);
            user.setName(finalName);
            user.setProvider(AuthProvider.GOOGLE);
            return userRepository.save(user);
        });

        String token = jwtUtil.generateToken(email);
        clearAuthenticationAttributes(request);

        // ✅ Environment variable se frontend URL lo
        String frontendUrl = System.getenv("FRONTEND_URL") != null
                ? System.getenv("FRONTEND_URL")
                : "http://localhost:3000";

        String redirectUrl = frontendUrl + "/login-success"
                + "?token=" + token
                + "&name=" + java.net.URLEncoder.encode(finalName, "UTF-8")
                + "&email=" + java.net.URLEncoder.encode(email, "UTF-8");

        response.sendRedirect(redirectUrl);
    }
}
