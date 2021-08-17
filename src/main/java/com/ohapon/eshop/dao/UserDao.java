package com.ohapon.eshop.dao;

public interface UserDao {

    boolean existsUser(String name, String password);

}
