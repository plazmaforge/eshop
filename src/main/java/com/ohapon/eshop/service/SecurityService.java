package com.ohapon.eshop.service;

import com.ohapon.eshop.entity.User;

public class SecurityService {

    public User login(String username, String password) {

        // TODO
        if (!"test".equals(username) && !"test".equals(password)) {
            return null;
        }

        // TODO
        User user = new User();
        user.setName(username);
        return user;
    }

}
