package com.ohapon.eshop.dao.jdbc;

import com.ohapon.eshop.dao.UserDao;
import com.ohapon.eshop.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserDao implements UserDao {

    private static final String FIND_QUERY = "SELECT id, username FROM user WHERE username = ? AND password = ?";

    private static final UserRowMapper ROW_MAPPER = new UserRowMapper();

    private static Logger logger = LogManager.getLogger(JdbcUserDao.class);

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User login(String username, String password) {
        return jdbcTemplate.queryForObject(FIND_QUERY, new Object[]{username, password}, ROW_MAPPER);
    }

}
