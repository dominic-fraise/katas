package com.example.giftshop.model;

import java.math.BigDecimal;

public record ProductUpdateCommand(BigDecimal price, Long id) {

}
