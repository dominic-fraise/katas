package com.example.giftshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
@Document
public class Product {
    private Long id;
    private String title;
    private BigDecimal price;
}
