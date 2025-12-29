package com.example.inventory.commands.controllers;

import com.example.inventory.commands.dto.*;
import com.example.inventory.commands.commands.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private final CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<String> createProduct(@RequestBody CreateProductRequestDTO request) {
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new CreateProductCommand(
                id,
                request.name(),
                request.price(),
                request.quantity(),
                request.categoryId()));
    }

    @PutMapping("/{id}/price")
    public CompletableFuture<Void> updateProductPrice(@PathVariable String id,
            @RequestBody UpdateProductPriceRequestDTO request) {
        return commandGateway.send(new UpdateProductPriceCommand(id, request.price()));
    }

    @PutMapping("/{id}/stock")
    public CompletableFuture<Void> updateProductStock(@PathVariable String id,
            @RequestBody UpdateProductStockRequestDTO request) {
        return commandGateway.send(new UpdateProductStockCommand(id, request.quantity()));
    }

    @PutMapping("/{id}/status")
    public CompletableFuture<Void> changeProductStatus(@PathVariable String id,
            @RequestBody ChangeProductStatusRequestDTO request) {
        return commandGateway.send(new ChangeProductStatusCommand(id, request.status()));
    }
}
