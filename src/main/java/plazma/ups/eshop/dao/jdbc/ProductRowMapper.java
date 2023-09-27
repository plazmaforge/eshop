package plazma.ups.eshop.dao.jdbc;

import plazma.ups.eshop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ProductRowMapper {

        public Product mapRow(ResultSet resultSet) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            double price = resultSet.getDouble("price");

            Timestamp timestamp = resultSet.getTimestamp("created_date");
            LocalDateTime date = timestamp == null ? null : timestamp.toLocalDateTime();

            Product product = new Product(id, name, description, price, date);
            return product;
        }

}
