package com.ohapon.eshop.service;

import com.ohapon.eshop.entity.Session;
import com.ohapon.eshop.entity.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SecurityService {

    private UserService userService;

    private Map<String, Session> tokens = new HashMap<String, Session>();

    public SecurityService(UserService userService) {
        this.userService = userService;
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

    public Session addSession(User user) {
        Session session = addSession();
        session.setUser(user);
        return session;
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

    protected String generateToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }

}
