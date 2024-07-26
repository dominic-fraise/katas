package com.example.giftshop;

import com.example.giftshop.model.Product;
import com.example.giftshop.model.ProductCommand;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.example.giftshop.model.ProductUpdateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    ProductRepository productRepository;
    private final ProductMongoRepository productMongoRepository;
    private final MongoTemplate mongoTemplate;
    ProductController productController;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMongoRepository productMongoRepository, MongoTemplate mongoTemplate) {
        this.productRepository = productRepository;
        this.productMongoRepository = productMongoRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Product createProduct(ProductCommand product) {
        productRepository.createProduct(product);
        return productMongoRepository.insert(new Product(new Random().nextLong(), product.title(), product.price()));
    }

    public Optional<Product> getProduct(Long productId) {
        System.out.println("Reached endpoint");
        return productMongoRepository.findById(String.valueOf(productId));
    }

    public Optional<Product> getProductByTitle(String title) {
        return productRepository.getProductByTitle(title);
    }

    public List<Product> getProducts(String searchQuery) throws SQLException {
        return mongoTemplate.find(new Query(Criteria.where("title").is(searchQuery)), Product.class);
    }

    public void updateProduct(ProductUpdateCommand productUpdateCommand) {
        productRepository.updatePrice(productUpdateCommand);
    }
}
