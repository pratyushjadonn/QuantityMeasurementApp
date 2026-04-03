package QuantityMeasurmentApp.security;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class TokenBlacklist {

    // FIX: synchronizedSet — thread-safe, concurrent requests mein crash nahi hoga
    private final Set<String> blacklist = Collections.synchronizedSet(new HashSet<>());

    public void blacklistToken(String token) {
        blacklist.add(token);
    }

    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}