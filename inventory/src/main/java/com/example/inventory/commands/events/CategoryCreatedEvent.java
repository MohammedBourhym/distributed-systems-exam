package com.example.inventory.commands.events;

public record CategoryCreatedEvent(
        String id,
        String name,
        String description) {
}