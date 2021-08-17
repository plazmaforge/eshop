package com.ohapon.eshop.service;

import java.util.*;
import com.ohapon.eshop.entity.User;

public class SecurityService {

    private List<String> tokens = new ArrayList<>();

    public boolean login(String username, String password) {
        // TODO
        return "test".equals(username) && "test".equals(password);
    }

    public boolean hasToken(String token) {
        return tokens.contains(token);
    }

    public String generateToken() {
        String token = UUID.randomUUID().toString();
        tokens.add(token);
        return token;
    }

}
