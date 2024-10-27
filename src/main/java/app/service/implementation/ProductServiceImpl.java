package app.service.implementation;

import app.model.Product;
import app.repository.ProductRepository;
import app.service.ProductService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository = RepositorySinglePointAccess.getProductRepository();

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id);
    }
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Product update(Product product) {
        return productRepository.update(product);
    }
    @Override
    public boolean delete(Product product) {
        return productRepository.delete(product);
    }
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
