package com.example.inventory.query.entities;

import com.example.inventory.commands.enums.ProductStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    private String categoryId;
}
