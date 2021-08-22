package com.ohapon.eshop.dao.jdbc;

import com.ohapon.eshop.dao.ProductDao;
import com.ohapon.eshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    private static final String FIND_ALL_QUERY = "SELECT id, name, description, price, created_date FROM product ORDER BY name";
    private static final String FIND_BY_TEXT_QUERY = "SELECT id, name, description, price, created_date FROM product WHERE UPPER(name) LIKE ? OR UPPER(description) LIKE ? ORDER BY name";
    private static final String FIND_BY_ID_QUERY = "SELECT id, name, description, price, created_date FROM product WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO product (id, name, description, price, created_date) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE product SET name = ?, description = ?, price = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM product WHERE id = ?";

    private static final ProductRowMapper ROW_MAPPER = new ProductRowMapper();

    private ConnectionFactory connectionFactory;
    public JdbcProductDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Product> findAll() {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY)) {
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = ROW_MAPPER.mapRow(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot get products from db", e);
        }

    }

    @Override
    public List<Product> findByText(String text) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_TEXT_QUERY)) {
            List<Product> products = new ArrayList<>();

            statement.setString(1, "%" + text.toUpperCase() + "%");
            statement.setString(2, "%" + text.toUpperCase() + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = ROW_MAPPER.mapRow(resultSet);
                    products.add(product);
                }
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot get products from db", e);
        }

    }

    @Override
    public Product findById(Long productId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setLong(1, productId);
            try (ResultSet resultSet = statement.executeQuery();) {
                if (resultSet.next()) {
                    Product product = ROW_MAPPER.mapRow(resultSet);
                    return product;
                }
                throw new RuntimeException("Product not found: id=" + productId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot get product from db", e);
        }
    }

    @Override
    public void add(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {

            // TODO
            long id = (long) (Math.random() * 10);
            product.setId(id);

            //statement.getGeneratedKeys();
            //try(ResultSet keySet = statement.getGeneratedKeys()) {
            //    keySet.next();
            //    long id = keySet.getLong(1);
            //    product.setId(id);
            //}

            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Cannot add product to db", e);
        }
    }

    @Override
    public void update(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setLong(4, product.getId());

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Cannot update product in db: productId = " + product.getId(), e);
        }
    }

    @Override
    public void remove(Long productId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {

            statement.setLong(1, productId);

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Cannot delete product from db: productId = " + productId, e);
        }
    }

    private Connection getConnection() throws SQLException {
        return connectionFactory.getConnection();
    }

}
