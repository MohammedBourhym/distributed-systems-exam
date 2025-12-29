package com.example.order.query.controllers;

import com.example.order.query.entities.OrderEntity;
import com.example.order.query.queries.GetAllOrdersQuery;
import com.example.order.query.queries.GetOrderByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders")
public class OrderQueryController {

    private final QueryGateway queryGateway;

    public OrderQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public CompletableFuture<List<OrderEntity>> getAllOrders() {
        return queryGateway.query(new GetAllOrdersQuery(), ResponseTypes.multipleInstancesOf(OrderEntity.class));
    }

    @GetMapping("/{id}")
    public CompletableFuture<OrderEntity> getOrderById(@PathVariable String id) {
        return queryGateway.query(new GetOrderByIdQuery(id), ResponseTypes.instanceOf(OrderEntity.class));
    }
}
