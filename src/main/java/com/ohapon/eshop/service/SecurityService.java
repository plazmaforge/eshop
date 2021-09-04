package com.ohapon.eshop.service;

import com.ohapon.eshop.entity.Session;
import com.ohapon.eshop.entity.User;

import java.util.*;

public class SecurityService {

    private UserService userService;

    private Map<String, Session> tokens = new HashMap<String, Session>();

    public SecurityService() {
    }

    public User login(String username, String password) {
        return userService.login(username, password);
    }

    public boolean existsToken(String token) {
        return tokens.containsKey(token);
    }

    public Session addSession() {
        String token = generateToken();
        return addSession(token);
    }

    protected Session addSession(String token) {
        Session session = new Session(token);
        tokens.put(token, session);
        return session;
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

    protected String generateToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }

}
