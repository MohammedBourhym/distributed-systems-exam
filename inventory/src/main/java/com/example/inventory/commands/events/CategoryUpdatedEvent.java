package com.example.inventory.commands.events;

public record CategoryUpdatedEvent(
        String id,
        String name,
        String description) {
}