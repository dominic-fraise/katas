package com.example.giftshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.giftshop.model.Product;
import com.example.giftshop.model.ProductCommand;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@SpringBootTest
class ProductRepositoryTest {

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private ProductRepository productRepository;

  @BeforeEach
  void setUp() {
    productRepository = new ProductRepository(namedParameterJdbcTemplate);
  }

  @Test
  void createProduct() {

    //Setup
    ProductCommand testProduct = new ProductCommand("Dom's cool keyring", BigDecimal.valueOf(10.0));

    //act
    productRepository.createProduct(testProduct);
    Product product = productRepository.getProduct(1L).get();

    //assert
    assertEquals("Dom's cool keyring", product.getTitle());
    assertEquals(BigDecimal.valueOf(10.0), product.getPrice());

  }

}