package com.example.inventory.commands.events;

import com.example.inventory.commands.enums.ProductStatus;

public record ProductStatusChangedEvent(
        String id,
        ProductStatus status) {
}