package com.ohapon.eshop.service;

import com.ohapon.eshop.dao.UserDao;
import com.ohapon.eshop.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    User login(String username, String password) {
        return userDao.login(username, password);
    }

}
