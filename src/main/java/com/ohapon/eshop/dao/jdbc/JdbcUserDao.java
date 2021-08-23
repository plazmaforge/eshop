package com.ohapon.eshop.dao.jdbc;

import com.ohapon.eshop.dao.UserDao;

import java.sql.*;

public class JdbcUserDao implements UserDao {

    private static final String FIND_QUERY = "SELECT id FROM user WHERE username = ? AND password = ?";

    private DefaultDataSource dataSource;
    public JdbcUserDao(DefaultDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean existsUser(String username, String password) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_QUERY)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery();) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot get user from db", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
