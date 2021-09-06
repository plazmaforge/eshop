package com.ohapon.eshop.dao.jdbc;

import com.ohapon.eshop.dao.ProductDao;
import com.ohapon.eshop.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProductDao implements ProductDao {

    private static final String FIND_ALL_QUERY = "SELECT id, name, description, price, created_date FROM product ORDER BY name";
    private static final String FIND_BY_TEXT_QUERY = "SELECT id, name, description, price, created_date FROM product WHERE UPPER(name) LIKE ? OR UPPER(description) LIKE ? ORDER BY name";
    private static final String FIND_BY_ID_QUERY = "SELECT id, name, description, price, created_date FROM product WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO product (id, name, description, price, created_date) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE product SET name = ?, description = ?, price = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM product WHERE id = ?";

    private static final ProductRowMapper ROW_MAPPER = new ProductRowMapper();

    private static Logger logger = LogManager.getLogger(JdbcProductDao.class);

    private JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, ROW_MAPPER);
    }

    @Override
    public Product findById(Long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new Object[]{id}, ROW_MAPPER);
    }

    @Override
    public List<Product> findByText(String text) {
        String likeText = "%" +text.toUpperCase() + "%";
        return jdbcTemplate.query(FIND_BY_TEXT_QUERY, new Object[]{likeText, likeText}, ROW_MAPPER);
    }

    @Override
    public void add(Product product) {
        long id = generateId(jdbcTemplate.getDataSource());
        product.setId(id);

        jdbcTemplate.update(INSERT_QUERY,
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public void update(Product product) {
        jdbcTemplate.update(UPDATE_QUERY,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getId());
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }

    private long generateId(DataSource dataSource) {

        //long id = (long) (Math.random() * 10);

        //statement.getGeneratedKeys();
        //try(ResultSet keySet = statement.getGeneratedKeys()) {
        //    keySet.next();
        //    long id = keySet.getLong(1);
        //    product.setId(id);
        //}

        try (Statement statement = dataSource.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT MAX(ID) + 1 FROM product")) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            throw new SQLException("Cannot generate id for Product");
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException("Cannot generate id for Product", e);
        }

    }

}
