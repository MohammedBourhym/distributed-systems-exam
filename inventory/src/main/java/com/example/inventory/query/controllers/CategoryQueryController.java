package com.example.inventory.query.controllers;

import com.example.inventory.query.entities.CategoryEntity;
import com.example.inventory.query.queries.GetAllCategoriesQuery;
import com.example.inventory.query.queries.GetCategoryByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/categories")
public class CategoryQueryController {

    private final QueryGateway queryGateway;

    public CategoryQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public CompletableFuture<List<CategoryEntity>> getAllCategories() {
        return queryGateway.query(new GetAllCategoriesQuery(), ResponseTypes.multipleInstancesOf(CategoryEntity.class));
    }

    @GetMapping("/{id}")
    public CompletableFuture<CategoryEntity> getCategoryById(@PathVariable String id) {
        return queryGateway.query(new GetCategoryByIdQuery(id), ResponseTypes.instanceOf(CategoryEntity.class));
    }
}
