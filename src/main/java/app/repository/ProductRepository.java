package app.repository;

import app.model.Product;

public interface ProductRepository extends CRUDRepository<Product, Integer> {
    Product findByName(String name);
}
