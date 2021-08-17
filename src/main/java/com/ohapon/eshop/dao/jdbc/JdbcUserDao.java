package com.ohapon.eshop.dao.jdbc;

import com.ohapon.eshop.dao.UserDao;

import java.sql.*;

public class JdbcUserDao implements UserDao {

    private static final String FIND_QUERY = "SELECT id FROM user WHERE name = ? AND password = ?";

    private ConnectionFactory connectionFactory;
    public JdbcUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public boolean existsUser(String name, String password) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_QUERY)) {
            statement.setString(1, name);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery();) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot get user from db", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return connectionFactory.getConnection();
    }

}
