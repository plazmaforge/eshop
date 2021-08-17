package com.ohapon.eshop.web;

import com.ohapon.eshop.entity.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PageGeneratorTest {

    @Test
    void testGetPage() {

        LocalDateTime now = LocalDateTime.now();
        List<Product> products = new ArrayList<>();
        products.add(new Product(100, "Product 10", null, 123.45));
        products.add(new Product(200, "Product 10", null, 123.45));

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("products", products);

        PageGenerator generator = new PageGenerator();
        String response = generator.getPage("/products.html", parametersMap);
        assertNotNull(response);
    }

}
