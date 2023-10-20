package com.example.giftshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.giftshop.model.Product;
import com.example.giftshop.model.ProductCommand;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@SpringBootTest
class ProductRepositoryTest {

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Test
  void createProduct() {
    ProductCommand testProduct = new ProductCommand("test", BigDecimal.valueOf(1));
    ProductRepository productRepository = new ProductRepository(namedParameterJdbcTemplate);
    productRepository.createProduct(testProduct);
    Product product = productRepository.getProduct(1L).get();
    assertEquals("test", product.getTitle());
    assertEquals(BigDecimal.valueOf(1), product.getPrice());
  }

}