package com.example.inventory.query.handlers;

import com.example.inventory.commands.events.*;
import com.example.inventory.query.entities.ProductEntity;
import com.example.inventory.query.queries.GetAllProductsQuery;
import com.example.inventory.query.queries.GetProductByIdQuery;
import com.example.inventory.query.repositories.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductProjection {

    private final ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity entity = new ProductEntity(
                event.id(),
                event.name(),
                event.price(),
                event.quantity(),
                event.status(),
                event.categoryId());
        productRepository.save(entity);
    }

    @EventHandler
    public void on(ProductPriceUpdatedEvent event) {
        productRepository.findById(event.id()).ifPresent(entity -> {
            entity.setPrice(event.price());
            productRepository.save(entity);
        });
    }

    @EventHandler
    public void on(ProductStockUpdatedEvent event) {
        productRepository.findById(event.id()).ifPresent(entity -> {
            entity.setQuantity(event.quantity());
            productRepository.save(entity);
        });
    }

    @EventHandler
    public void on(ProductStatusChangedEvent event) {
        productRepository.findById(event.id()).ifPresent(entity -> {
            entity.setStatus(event.status());
            productRepository.save(entity);
        });
    }

    @QueryHandler
    public List<ProductEntity> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }

    @QueryHandler
    public ProductEntity handle(GetProductByIdQuery query) {
        return productRepository.findById(query.id()).orElse(null);
    }
}
