package com.example.inventory.commands.dto;

import com.example.inventory.commands.enums.ProductStatus;

public record ChangeProductStatusRequestDTO(ProductStatus status) {

}
