package com.ohapon.eshop.service;

import com.ohapon.eshop.dao.UserDao;

public class UserService {

    private UserDao userDao;

    public UserService() {
    }

    boolean existsUser(String username, String password) {
        return userDao.existsUser(username, password);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
