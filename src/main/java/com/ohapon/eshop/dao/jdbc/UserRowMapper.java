package com.ohapon.eshop.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.ohapon.eshop.entity.User;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String username = resultSet.getString("username");

        User user = new User(id, username);
        return user;
    }
}
