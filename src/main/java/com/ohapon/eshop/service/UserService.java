package com.ohapon.eshop.service;

import com.ohapon.eshop.dao.UserDao;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    boolean existsUser(String username, String password) {
        return getUserDao().existsUser(username, password);
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
