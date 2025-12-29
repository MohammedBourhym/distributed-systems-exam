package com.example.inventory.commands.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateCategoryCommand(
        @TargetAggregateIdentifier String id,
        String name,
        String description) {
}
