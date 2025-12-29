package com.example.order.command.commands;

import com.example.order.command.model.OrderLine;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import java.util.List;
import java.time.Instant;

public record CreateOrderCommand(
        @TargetAggregateIdentifier String orderId,
        String customerId,
        Instant orderDate,
        String deliveryAddress,
        List<OrderLine> orderLines) {
}
