package com.ohapon.eshop.service;

import com.ohapon.eshop.entity.Session;

import java.util.*;

public class SecurityService {

    private UserService userService;

    private Map<String, Session> tokens = new HashMap<String, Session>();

    public SecurityService() {
    }

    public boolean login(String username, String password) {
        return userService.existsUser(username, password);
    }

    public boolean existsToken(String token) {
        return tokens.containsKey(token);
    }

    public String generateToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }

    public void addSession(String token, Session session) {
        tokens.put(token, session);
    }

    public void removeSession(String token) {
        tokens.remove(token);
    }

    public Session getSession(String token) {
        return tokens.get(token);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
