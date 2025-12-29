package com.example.order.command.model;

import java.math.BigDecimal;

public record OrderLine(
        String productId,
        Integer quantity,
        BigDecimal price,
        BigDecimal discount) {
}
