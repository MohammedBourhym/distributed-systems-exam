package com.example.order.command.dto;

import com.example.order.command.model.OrderLine;
import java.util.List;

public record CreateOrderRequest(
        String customerId,
        String deliveryAddress,
        List<OrderLine> orderLines) {
}
