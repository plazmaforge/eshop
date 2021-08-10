package com.ohapon.eshop.dao.jdbc;

import com.ohapon.eshop.dao.ProductDao;
import com.ohapon.eshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    private static final String FIND_ALL_QUERY = "SELECT id, name, price, created_date FROM product";
    private static final String FIND_ONE_QUERY = "SELECT id, name, price, created_date FROM product WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO product (id, name, price, created_date) VALUES (?, ?, ?, ?)";

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
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                Date createdDate = resultSet.getDate(4);

                Product product = new Product(id, name, price, createdDate);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot get products from db", e);
        }

    }

    @Override
    public Product findById(Long productId) {

        ResultSet resultSet = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ONE_QUERY);
        ) {

            statement.setLong(1, productId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                Date createdDate = resultSet.getDate(4);

                Product product = new Product(id, name, price, createdDate);
                return product;
            }
            throw new RuntimeException("Product not found: id=" + productId);

        } catch (SQLException e) {
            throw new RuntimeException("Cannot get product from db", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {

                }
            }
        }

    }

    @Override
    public void add(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
             ) {

            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setDate(4, new java.sql.Date(product.getDate().getTime()));

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Cannot add product to db", e);
        }
    }

    @Override
    public void update(Product product) {
        // TODO
    }

    @Override
    public void remove(Long productId) {
        // TODO
    }

    private Connection getConnection() throws SQLException {
        return connectionFactory.getConnection();
    }

}
