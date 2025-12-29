package com.example.order.query.handlers;

import com.example.order.command.events.OrderCreatedEvent;
import com.example.order.query.entities.OrderEntity;
import com.example.order.query.entities.OrderLineEntity;
import com.example.order.query.queries.GetAllOrdersQuery;
import com.example.order.query.queries.GetOrderByIdQuery;
import com.example.order.query.repositories.OrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderProjection {

    private final OrderRepository orderRepository;

    public OrderProjection(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(event.orderId());
        orderEntity.setCustomerId(event.customerId());
        orderEntity.setOrderDate(event.orderDate());
        orderEntity.setDeliveryAddress(event.deliveryAddress());
        orderEntity.setStatus(event.status());

        List<OrderLineEntity> orderLines = event.orderLines().stream().map(ol -> {
            OrderLineEntity entity = new OrderLineEntity();
            entity.setProductId(ol.productId());
            entity.setQuantity(ol.quantity());
            entity.setPrice(ol.price());
            entity.setDiscount(ol.discount());
            entity.setOrder(orderEntity);
            return entity;
        }).collect(Collectors.toList());

        orderEntity.setOrderLines(orderLines);
        orderRepository.save(orderEntity);
    }

    @QueryHandler
    public List<OrderEntity> handle(GetAllOrdersQuery query) {
        return orderRepository.findAll();
    }

    @QueryHandler
    public OrderEntity handle(GetOrderByIdQuery query) {
        return orderRepository.findById(query.id()).orElse(null);
    }
}
