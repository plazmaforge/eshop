package com.ohapon.eshop.service;

import com.ohapon.eshop.dao.UserDao;
import com.ohapon.eshop.entity.User;

public class UserService {

    private UserDao userDao;

    public UserService() {
    }

    User login(String username, String password) {
        return userDao.login(username, password);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
