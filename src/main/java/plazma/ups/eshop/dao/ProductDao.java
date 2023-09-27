package plazma.ups.eshop.dao;

import plazma.ups.eshop.entity.Product;
import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    Product findById(Long id);

    List<Product> findByText(String text);

    void add(Product product);

    void update(Product product);

    void remove(Long id);

}
