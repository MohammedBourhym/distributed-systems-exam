package com.example.inventory.commands.controllers;

import com.example.inventory.commands.dto.*;
import com.example.inventory.commands.commands.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/categories")
public class CategoryCommandController {

    private final CommandGateway commandGateway;

    public CategoryCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<String> createCategory(@RequestBody CreateCategoryRequestDTO request) {
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new CreateCategoryCommand(
                id,
                request.name(),
                request.description()));
    }

    @PutMapping("/{id}")
    public CompletableFuture<Void> updateCategory(@PathVariable String id,
            @RequestBody UpdateCategoryRequestDTO request) {
        return commandGateway.send(new UpdateCategoryCommand(
                id,
                request.name(),
                request.description()));
    }
}
