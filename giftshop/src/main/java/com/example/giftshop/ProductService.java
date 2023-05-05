package com.example.giftshop;

import com.example.giftshop.model.Product;
import com.example.giftshop.model.ProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

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
}
