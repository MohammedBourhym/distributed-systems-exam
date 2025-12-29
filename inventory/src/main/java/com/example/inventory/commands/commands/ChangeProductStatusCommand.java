package com.example.inventory.commands.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.example.inventory.commands.enums.ProductStatus;

public record ChangeProductStatusCommand(
        @TargetAggregateIdentifier String id,
        ProductStatus status) {
}
