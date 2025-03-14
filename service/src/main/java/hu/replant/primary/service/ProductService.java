package hu.replant.primary.service;

import hu.replant.core.model.Product;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product getProductById(Long id);

    public Product createProduct(@Valid Product product);

    public Product updateProduct(Long id, @Valid Product product);

    void deleteProduct(Long id);
}
