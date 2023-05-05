package com.example.giftshop.model;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

public record ProductCommand(String title, BigDecimal price) {
}
