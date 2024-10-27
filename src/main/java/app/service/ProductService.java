package app.service;

import app.model.Product;

import java.util.List;

public interface ProductService {
    Product findByName(String name);
    Product findById(Integer id);
    Product save(Product product);
    Product update(Product product);
    boolean delete(Product product);
    List<Product> findAll();
}
