package com.example.inventory.query.controllers;

import com.example.inventory.query.entities.ProductEntity;
import com.example.inventory.query.queries.GetAllProductsQuery;
import com.example.inventory.query.queries.GetProductByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private final QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public CompletableFuture<List<ProductEntity>> getAllProducts() {
        return queryGateway.query(new GetAllProductsQuery(), ResponseTypes.multipleInstancesOf(ProductEntity.class));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ProductEntity> getProductById(@PathVariable String id) {
        return queryGateway.query(new GetProductByIdQuery(id), ResponseTypes.instanceOf(ProductEntity.class));
    }
}
