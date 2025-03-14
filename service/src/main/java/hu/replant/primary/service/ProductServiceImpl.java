package hu.replant.primary.service;

import hu.replant.core.model.Product;
import hu.replant.core.mapper.ProductMapper;
import hu.replant.secondary.persistence.entity.ProductEntity;
import hu.replant.secondary.persistence.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        logger.info("Fetching all products");
        List<ProductEntity> productEntities = productRepository.findAll();
        logger.debug("Fetched {} products from the database", productEntities.size());
        return ProductMapper.toModelList(productEntities);
    }

    @Override
    public Product getProductById(Long id) {
        logger.info("Fetching product by ID: {}", id);
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        if (productEntity == null) {
            logger.warn("Product with ID {} not found", id);
        } else {
            logger.debug("Fetched product: {}", productEntity);
        }
        return ProductMapper.toModel(productEntity);
    }

    @Override
    public Product createProduct(Product product) {
        logger.info("Creating a new product: {}", product);
        ProductEntity productEntity = ProductMapper.toEntity(product);
        ProductEntity savedProduct = productRepository.save(productEntity);
        logger.debug("Product created successfully: {}", savedProduct);
        return ProductMapper.toModel(savedProduct);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        logger.info("Updating product with ID {}: {}", id, product);
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Product with ID {} not found for update", id);
                    return new RuntimeException("Product not found");
                });

        productEntity.setName(product.getName() != null ? product.getName() : productEntity.getName());
        productEntity.setQuantity(product.getQuantity() != null ? product.getQuantity() : productEntity.getQuantity());
        productEntity.setUpdatedAt(Instant.now());

        ProductEntity savedProduct = productRepository.save(productEntity);
        logger.debug("Product updated successfully: {}", savedProduct);
        return ProductMapper.toModel(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        logger.info("Deleting product with ID: {}", id);
        if (!productRepository.existsById(id)) {
            logger.warn("Product with ID {} not found for deletion", id);
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
        logger.debug("Product with ID {} deleted successfully", id);
    }
}