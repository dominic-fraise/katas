package com.example.giftshop;

import com.example.giftshop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ProductRepository {
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createProduct(Product product) {
        String sql = "INSERT INTO product (id, title, price) VALUES (:id, :title, :price)";
        jdbcTemplate.update(sql, Map.of(
                "id", product.getId(),
                "title", product.getTitle(),
                "price", product.getPrice()));
    }
}
