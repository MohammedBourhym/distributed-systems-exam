package com.example.inventory.commands.dto;

import java.math.BigDecimal;

public record UpdateProductPriceRequestDTO(
        BigDecimal price) {
}
