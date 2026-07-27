package com.example.e_commerce.mapper;

import com.example.e_commerce.dto.ProductRequest;
import com.example.e_commerce.dto.ProductResponse;
import com.example.e_commerce.entity.Category;
import com.example.e_commerce.entity.Product;
import com.example.e_commerce.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductMapper {

    private final CategoryService categoryService; //for safety we make it immutable from now

    public ProductMapper(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    public Product toEntity(ProductRequest request) { // dto to entity (product request -> product entity)

        Product product = new Product(); //for new entity

        product.setName(request.name());
        product.setDescription(request.description());
        product.setIsInStock(request.isInStock()); // all dto to entity
        // product.setId() not included bc its auto gşven

        if (request.categoryIds() != null && !request.categoryIds().isEmpty()) { //for safety, ids came from client

            List<Category> categories = new ArrayList<>();

            for (Long id: request.categoryIds()) {

                Category foundCategory = categoryService.findById(id); // id to service, then service finds category by id
                categories.add(foundCategory); //add the found category onj to the list
            }
            product.setCategories(categories); // set the categorylist to product
        }

        return product;

    }

    public ProductResponse toResponse(Product product) { // entity to dto (product entity -> product response)
        List<String> categoryNames = new ArrayList<>();

        if (product.getCategories() != null) { // category list from db

            for (Category category : product.getCategories()) { // for each category of that product
                categoryNames.add(category.getName());
            }
        }

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getIsInStock(),

                categoryNames //pnly the name of the category will be shown to the client
                );
    }

    public void updateEntityFromRequest(Product product, ProductRequest request) { //updating (again translating between entity and dto)

        product.setName(request.name());
        product.setDescription(request.description());
        product.setIsInStock(request.isInStock());

        // nearly the same with toEntity but toEntity = new Product(); updateEntity takes already existing one
        if(request.categoryIds() != null && !request.categoryIds().isEmpty()) { //for safety

            List<Category> categories = new ArrayList<>(); //list only for the spesific product

            for (Long id: request.categoryIds()) {
                Category foundCategory = categoryService.findById(id);
                categories.add(foundCategory);
            }
            product.setCategories(categories);
        }

    }


}
