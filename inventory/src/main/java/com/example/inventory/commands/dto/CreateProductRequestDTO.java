package com.example.inventory.commands.dto;

import java.math.BigDecimal;

public record CreateProductRequestDTO(
        String name,
        BigDecimal price,
        Integer quantity,
        String categoryId) {
}
