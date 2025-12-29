package com.example.inventory.query.handlers;

import com.example.inventory.commands.events.*;
import com.example.inventory.query.entities.CategoryEntity;
import com.example.inventory.query.queries.GetAllCategoriesQuery;
import com.example.inventory.query.queries.GetCategoryByIdQuery;
import com.example.inventory.query.repositories.CategoryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryProjection {

    private final CategoryRepository categoryRepository;

    public CategoryProjection(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @EventHandler
    public void on(CategoryCreatedEvent event) {
        CategoryEntity entity = new CategoryEntity(
                event.id(),
                event.name(),
                event.description());
        categoryRepository.save(entity);
    }

    @EventHandler
    public void on(CategoryUpdatedEvent event) {
        categoryRepository.findById(event.id()).ifPresent(entity -> {
            entity.setName(event.name());
            entity.setDescription(event.description());
            categoryRepository.save(entity);
        });
    }

    @QueryHandler
    public List<CategoryEntity> handle(GetAllCategoriesQuery query) {
        return categoryRepository.findAll();
    }

    @QueryHandler
    public CategoryEntity handle(GetCategoryByIdQuery query) {
        return categoryRepository.findById(query.id()).orElse(null);
    }
}
