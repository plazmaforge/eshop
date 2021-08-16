package com.ohapon.eshop.dao.jdbc;

import com.ohapon.eshop.dao.ProductDao;
import com.ohapon.eshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    private static final String FIND_ALL_QUERY = "SELECT id, name, price, created_date FROM product ORDER BY id";
    private static final String FIND_ONE_QUERY = "SELECT id, name, price, created_date FROM product WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO product (id, name, price, created_date) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE product SET name = ?, price = ?, created_date = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM product WHERE id = ?";

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
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ONE_QUERY)) {
            statement.setLong(1, productId);
            try (ResultSet resultSet = statement.executeQuery();) {
                if (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    double price = resultSet.getDouble(3);
                    Date createdDate = resultSet.getDate(4);

                    Product product = new Product(id, name, price, createdDate);
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
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setDate(3, new java.sql.Date(product.getDate().getTime()));
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
