package com.example.giftshop;

import com.example.giftshop.model.Product;
import com.example.giftshop.model.ProductCommand;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.example.giftshop.model.ProductUpdateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    ProductRepository productRepository;
    private final ProductMongoRepository productMongoRepository;
    ProductController productController;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMongoRepository productMongoRepository) {
        this.productRepository = productRepository;
        this.productMongoRepository = productMongoRepository;
    }

    public void createProduct(ProductCommand product) {
        productRepository.createProduct(product);
        productMongoRepository.insert(new Product(new Random().nextLong(), product.title(), product.price()));
    }

    public Optional<Product> getProduct(Long productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<Product> getProductByTitle(String title) {
        return productRepository.getProductByTitle(title);
    }

    public List<Product> getProducts(String searchQuery) throws SQLException {
        return productRepository.getProducts(searchQuery);
    }

    public void updateProduct(ProductUpdateCommand productUpdateCommand) {
        productRepository.updatePrice(productUpdateCommand);
    }
}
