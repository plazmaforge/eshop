package com.ohapon.eshop.dao;

import com.ohapon.eshop.entity.User;

public interface UserDao {

    User login(String username, String password);

}
