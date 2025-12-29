package com.example.inventory.commands.aggregate;

import com.example.inventory.commands.commands.*;
import com.example.inventory.commands.events.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class CategoryAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private String description;

    public CategoryAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command) {
        apply(new CategoryCreatedEvent(command.id(), command.name(), command.description()));
    }

    @EventSourcingHandler
    public void on(CategoryCreatedEvent event) {
        this.id = event.id();
        this.name = event.name();
        this.description = event.description();
    }

    @CommandHandler
    public void handle(UpdateCategoryCommand command) {
        apply(new CategoryUpdatedEvent(command.id(), command.name(), command.description()));
    }

    @EventSourcingHandler
    public void on(CategoryUpdatedEvent event) {
        this.name = event.name();
        this.description = event.description();
    }
}
