package com.ohapon.eshop.service;

import java.util.*;

public class SecurityService {

    private UserService userService;

    private List<String> tokens = new ArrayList<>();

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public boolean login(String username, String password) {
        return userService.existsUser(username, password);
    }

    public boolean existsToken(String token) {
        return tokens.contains(token);
    }

    public String generateToken() {
        String token = UUID.randomUUID().toString();
        tokens.add(token);
        return token;
    }

}
