package com.example.giftshop;

import static java.sql.DriverManager.getConnection;

import com.example.giftshop.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.giftshop.model.ProductCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ProductRepository {
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createProduct(ProductCommand product) {
        String sql = "INSERT INTO product (title, price) VALUES (:title, :price)";
        jdbcTemplate.update(sql, Map.of(
                "title", product.title(),
                "price", product.price()));
    }

    public Optional<Product> getProduct(Long id) {
        String sql = "SELECT * FROM product WHERE id = :id";
        List<Product> query = jdbcTemplate.query(sql, Map.of("id", id),
                (rs, rowNum) -> new Product(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getBigDecimal("price")));
        return query.stream().findFirst();
    }

    public Optional<Product> getProductByTitle(String title) {
        String sql = "SELECT * FROM product WHERE title = :title";
        List<Product> query = jdbcTemplate.query(sql, Map.of("title", title),
                (rs, rowNum) -> new Product(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getBigDecimal("price")));
        return query.stream().findFirst();
    }

    public List<Product> getProducts(String searchQuery) throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/giftshop");
        String sql = "SELECT id, title, price FROM product WHERE title LIKE ?";
        PreparedStatement pstmt = db.prepareStatement(searchQuery);
        pstmt.setString( 1, searchQuery);
        log.info(sql);
        List<Product> query = jdbcTemplate.query(sql, Map.of("searchQuery", searchQuery),
                (rs, rowNum) -> new Product(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getBigDecimal("price")));
        log.info(query.toString());
        return query;
    }
}
