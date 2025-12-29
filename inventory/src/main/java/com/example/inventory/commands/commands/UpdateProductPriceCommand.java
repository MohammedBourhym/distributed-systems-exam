package com.example.inventory.commands.commands;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record UpdateProductPriceCommand(
        @TargetAggregateIdentifier String id,
        BigDecimal price) {
}
