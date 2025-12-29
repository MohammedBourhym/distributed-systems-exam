package com.example.inventory.commands.events;

import java.math.BigDecimal;

public record ProductPriceUpdatedEvent(
        String id,
        BigDecimal price) {
}
