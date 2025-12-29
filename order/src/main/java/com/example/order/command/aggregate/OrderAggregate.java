package com.example.order.command.aggregate;

import com.example.order.command.commands.CreateOrderCommand;
import com.example.order.command.enums.OrderStatus;
import com.example.order.command.events.OrderCreatedEvent;
import com.example.order.command.model.OrderLine;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String customerId;
    private Instant orderDate;
    private Instant deliveryDate;
    private String deliveryAddress;
    private OrderStatus status;
    private List<OrderLine> orderLines;

    public OrderAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        // Logic to validate command could go here
        if (command.orderLines() == null || command.orderLines().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one line");
        }

        apply(new OrderCreatedEvent(
                command.orderId(),
                command.customerId(),
                command.orderDate(),
                command.deliveryAddress(),
                command.orderLines(),
                OrderStatus.CREATED));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.orderId();
        this.customerId = event.customerId();
        this.orderDate = event.orderDate();
        this.deliveryAddress = event.deliveryAddress();
        this.orderLines = event.orderLines();
        this.status = event.status();
    }
}
