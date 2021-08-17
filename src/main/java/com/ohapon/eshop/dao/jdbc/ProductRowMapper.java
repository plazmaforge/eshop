package com.ohapon.eshop.dao.jdbc;

import com.ohapon.eshop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ProductRowMapper {

        public Product mapRow(ResultSet resultSet) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");

            //String productDescription = resultSet.getString("description");
            LocalDateTime date = resultSet.getTimestamp("created_date").toLocalDateTime();

            Product product = new Product(id, name, price, date);
            return product;
        }

}
