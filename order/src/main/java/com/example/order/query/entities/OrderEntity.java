package com.example.order.query.entities;

import com.example.order.command.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    private String id;
    private String customerId;
    private Instant orderDate;
    private Instant deliveryDate; // Can be null initially
    private String deliveryAddress;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderLineEntity> orderLines;
}
