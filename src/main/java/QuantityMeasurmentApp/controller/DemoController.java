package QuantityMeasurmentApp.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/login-success")
    public Map<String, String> loginSuccess(
            @RequestParam String token,
            @RequestParam String name,
            @RequestParam String email) {

        Map<String, String> response = new LinkedHashMap<>();
        response.put("message", "Google login successful");
        response.put("name", name);
        response.put("email", email);
        response.put("token", token);
        return response;
    }
}