package com.ohapon.eshop.service;

import com.ohapon.eshop.entity.User;

public class SecurityService {

    public User login(String username, String password) {
        // TODO
        User user = new User();
        user.setName(username);
        return user;
    }

}
