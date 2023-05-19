package com.example.giftshop;

import com.example.giftshop.model.Product;
import com.example.giftshop.model.ProductCommand;
import com.example.giftshop.model.ProductResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ProductResponse getProductResponse(String title) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setCreated(false);
        productRepository.getProductByTitle(title).ifPresent((x) -> {
            productResponse.setCreated(true);
            productResponse.setId(x.getId());
        });
        return productResponse;
    }
}
