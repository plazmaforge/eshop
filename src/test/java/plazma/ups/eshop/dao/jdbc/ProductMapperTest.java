package plazma.ups.eshop.dao.jdbc;

import plazma.ups.eshop.entity.Product;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductMapperTest {


    @Test
    void testMapRow() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        LocalDateTime date = LocalDateTime.now();
        when(resultSet.getLong("id")).thenReturn(123L);
        when(resultSet.getString("name")).thenReturn("Product Name");
        when(resultSet.getString("description")).thenReturn("Product Description");
        when(resultSet.getDouble("price")).thenReturn(123.45);
        when(resultSet.getTimestamp("created_date")).thenReturn(Timestamp.valueOf(date));


        ProductRowMapper rowMapper = new ProductRowMapper();
        Product product = rowMapper.mapRow(resultSet);

        assertNotNull(product);

        assertEquals(123L, product.getId());
        assertEquals("Product Name", product.getName());
        assertEquals("Product Description", product.getDescription());
        assertEquals(123.45, product.getPrice());
        assertEquals(Timestamp.valueOf(date).getTime(), product.getDate().toInstant(OffsetDateTime.now().getOffset()).toEpochMilli());
    }

}
