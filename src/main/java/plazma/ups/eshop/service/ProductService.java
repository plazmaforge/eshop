package plazma.ups.eshop.service;

import plazma.ups.eshop.dao.ProductDao;
import plazma.ups.eshop.entity.Product;
import java.util.List;

public class ProductService {

    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        return getProductDao().findAll();
    }

    public List<Product> findByText(String text) {
        return getProductDao().findByText(text);
    }

    public Product findById(Long productId) {
        return getProductDao().findById(productId);
    }

    public void add(Product product) {
        getProductDao().add(product);
    }

    public void update(Product product) {
        getProductDao().update(product);
    }

    public void remove(Long productId) {
        getProductDao().remove(productId);
    }

    private ProductDao getProductDao() {
        return productDao;
    }

}
