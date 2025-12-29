package com.example.inventory.commands.events;

import java.math.BigDecimal;

import com.example.inventory.commands.enums.ProductStatus;

public record ProductCreatedEvent(
        String id,
        String name,
        BigDecimal price,
        Integer quantity,
        ProductStatus status,
        String categoryId) {
}
