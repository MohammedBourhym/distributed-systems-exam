package com.example.inventory.commands.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record UpdateProductStockCommand(
        @TargetAggregateIdentifier String id,
        Integer quantity) {
}