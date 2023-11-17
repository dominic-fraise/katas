package com.example.giftshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.giftshop.model.Product;
import com.example.giftshop.model.ProductCommand;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

  @ParameterizedTest
  @CsvSource(value = {"Title 1, 10",
                      "hand-soap, 10.35",
                      "hat-$%£@^&*()!, 10000000000000",
                      "title 2, 10",
                      "title 2, 10"
  })
  void getProductByTitleTest(String title, BigDecimal price) {
    System.out.println(title);
    System.out.println(price);
    ProductCommand testProduct = new ProductCommand(title, price);

    productRepository.createProduct(testProduct);

    Product product = productRepository.getProductByTitle(title).get();

    assertEquals(title, product.getTitle());
    assertEquals(price, product.getPrice());
  }

}