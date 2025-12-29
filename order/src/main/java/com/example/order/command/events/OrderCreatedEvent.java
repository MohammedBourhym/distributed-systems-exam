package com.example.order.command.events;

import com.example.order.command.enums.OrderStatus;
import com.example.order.command.model.OrderLine;
import java.util.List;
import java.time.Instant;

public record OrderCreatedEvent(
        String orderId,
        String customerId,
        Instant orderDate,
        String deliveryAddress,
        List<OrderLine> orderLines,
        OrderStatus status) {
}
