package com.example.inventory.commands.aggregate;

import com.example.inventory.commands.enums.ProductStatus;
import com.example.inventory.commands.commands.*;
import com.example.inventory.commands.events.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private ProductStatus status;
    private String categoryId;

    public ProductAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        apply(new ProductCreatedEvent(
                command.id(),
                command.name(),
                command.price(),
                command.quantity(),
                ProductStatus.AVAILABLE, // Default status on creation
                command.categoryId()));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.id = event.id();
        this.name = event.name();
        this.price = event.price();
        this.quantity = event.quantity();
        this.status = event.status();
        this.categoryId = event.categoryId();
    }

    @CommandHandler
    public void handle(UpdateProductPriceCommand command) {
        if (command.price().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        apply(new ProductPriceUpdatedEvent(command.id(), command.price()));
    }

    @EventSourcingHandler
    public void on(ProductPriceUpdatedEvent event) {
        this.price = event.price();
    }

    @CommandHandler
    public void handle(UpdateProductStockCommand command) {
        if (command.quantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        apply(new ProductStockUpdatedEvent(command.id(), command.quantity()));
    }

    @EventSourcingHandler
    public void on(ProductStockUpdatedEvent event) {
        this.quantity = event.quantity();
    }

    @CommandHandler
    public void handle(ChangeProductStatusCommand command) {
        apply(new ProductStatusChangedEvent(command.id(), command.status()));
    }

    @EventSourcingHandler
    public void on(ProductStatusChangedEvent event) {
        this.status = event.status();
    }
}
