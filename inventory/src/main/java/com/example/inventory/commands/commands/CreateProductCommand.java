package com.example.inventory.commands.commands;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateProductCommand(
        @TargetAggregateIdentifier String id,
        String name,
        BigDecimal price,
        Integer quantity,
        String categoryId) {
}