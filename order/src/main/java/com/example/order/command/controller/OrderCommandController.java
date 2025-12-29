package com.example.order.command.controller;

import com.example.order.command.commands.CreateOrderCommand;
import com.example.order.command.dto.CreateOrderRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    private final CommandGateway commandGateway;

    public OrderCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<String> createOrder(@RequestBody CreateOrderRequest request) {
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new CreateOrderCommand(
                id,
                request.customerId(),
                Instant.now(),
                request.deliveryAddress(),
                request.orderLines()));
    }
}
