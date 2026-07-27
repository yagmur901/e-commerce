package com.example.e_commerce.service;

import com.example.e_commerce.dto.CategoryRequest;
import com.example.e_commerce.dto.CategoryResponse;
import com.example.e_commerce.entity.Category;
import com.example.e_commerce.exception.CategoryNotFoundException;
import com.example.e_commerce.exception.DuplicateCategoryException;
import com.example.e_commerce.mapper.CategoryMapper;
import com.example.e_commerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService { // all operations crud and find


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {

        this.categoryMapper = categoryMapper;
        this.categoryRepository =  categoryRepository;
    }

    public CategoryResponse createCategory(CategoryRequest request) throws DuplicateCategoryException { //create method

        if (categoryRepository.findByName(request.name()).isPresent()) {
            throw new DuplicateCategoryException("There is already a category with the name : " + request.name());
        }

        Category category = categoryMapper.toEntity(request); //mapper converts requuest to entity
        Category savedCategory = categoryRepository.save(category); //repo records to db

        return categoryMapper.toResponse(savedCategory); //convert to response dto = request-saved-response
    }

    public List<CategoryResponse> getAllCategories() { //read  method

        List<Category> categories = categoryRepository.findAll();

        List<CategoryResponse> responses = new ArrayList<>();
        for (Category category : categories) {
            responses.add(categoryMapper.toResponse(category)); //almost same with the product servcie
        }
        return responses;
    }


    public Category findById(Long id) { //used in mapper class, inner use only, client doest see this (returns entity)

       return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id : " + id));

    }

    public CategoryResponse updateCategory(Long id, CategoryRequest request) { //update method

        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id : " + id));

                if(!category.getName().equals(request.name()) && categoryRepository.findByName(request.name()).isPresent()) {
                    throw new DuplicateCategoryException("There is already a category with the name : " + request.name());
                }




        categoryMapper.updateEntityFromRequest(category, request); // inner func is in mapper class
        Category updatedCategory = categoryRepository.save(category); //repo overwrites = updates in the memory

        return categoryMapper.toResponse(updatedCategory);

    }


    public CategoryResponse getCategoryResponseById(Long id) { //will be given to the client
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id : " + id));

         return categoryMapper.toResponse(category);
    }




    public void deleteCategory(Long id) { //delete method

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id : " + id)); //first find then delete from repo.
        categoryRepository.delete(category);
    }


}
