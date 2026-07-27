package com.example.e_commerce.mapper;

import com.example.e_commerce.dto.CategoryRequest;
import com.example.e_commerce.dto.CategoryResponse;
import com.example.e_commerce.entity.Category;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequest request) { //dto to entity (category request -> category entity)

        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        return category;
        //only name and descrition bc id will be automatically given
    }

    public CategoryResponse toResponse(Category category) { //entitiy to dto (category entity -> category response)


        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        ); //will include all the fields
    }

    public void updateEntityFromRequest(Category category, CategoryRequest request) {
        category.setName(request.name());
        category.setDescription(request.description());
    }
}
