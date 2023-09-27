package plazma.ups.eshop.dao;

import plazma.ups.eshop.entity.Product;
import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    List<Product> findByText(String text);

    Product findById(Long productId);

    void add(Product product);

    void update(Product product);

    void remove(Long productId);

}
