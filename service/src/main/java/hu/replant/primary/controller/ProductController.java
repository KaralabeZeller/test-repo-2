package hu.replant.primary.controller;

import hu.replant.dto.ProductDTO;
import hu.replant.core.model.Product;
import hu.replant.core.mapper.ProductMapper;
import hu.replant.primary.service.ProductService;
import jakarta.validation.Valid;
import org.openapitools.api.ProductsApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController implements ProductsApi {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(ProductMapper.toDtoList(products));
    }

    @Override
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDto) {
        Product product = productService.createProduct(ProductMapper.toModel(productDto));
        return new ResponseEntity<>(ProductMapper.toDto(product), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(ProductMapper.toDto(product));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDto) {
        Product product = productService.updateProduct(id, ProductMapper.toModel(productDto));
        return ResponseEntity.ok(ProductMapper.toDto(product));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
