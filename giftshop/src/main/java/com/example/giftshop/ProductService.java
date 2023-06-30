package com.example.giftshop;

import com.example.giftshop.model.Product;
import com.example.giftshop.model.ProductCommand;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    ProductRepository productRepository;
    ProductController productController;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductCommand product) {
        productRepository.createProduct(product);

    }

    public Optional<Product> getProduct(Long productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<Product> getProductByTitle(String title) {
        return productRepository.getProductByTitle(title);
    }

    public List<Product> getProducts(String searchQuery) {
        return productRepository.getProducts(searchQuery);
    }
}
