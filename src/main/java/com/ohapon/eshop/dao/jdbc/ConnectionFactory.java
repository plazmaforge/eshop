package com.ohapon.eshop.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private String url;
    private String user;
    private String password;

    public ConnectionFactory(Properties properties) {
        this.url = properties.getProperty("url");
        this.user = properties.getProperty("user");
        this.password = properties.getProperty("password");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
