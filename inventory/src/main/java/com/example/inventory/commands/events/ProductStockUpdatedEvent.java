package com.example.inventory.commands.events;

public record ProductStockUpdatedEvent(
        String id,
        Integer quantity) {
}
