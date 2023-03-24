package com.example.giftshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private BigDecimal price;
}
