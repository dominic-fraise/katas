package com.example.giftshop;

import com.example.giftshop.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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

    public Optional<Product> getProduct(Long id) {
        String sql = "SELECT * FROM product WHERE id = :id";
        List<Product> query = jdbcTemplate.query(sql, Map.of("id", id),
                (rs, rowNum) -> new Product(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getBigDecimal("price")));
        return query.stream().findFirst();
    }
}
