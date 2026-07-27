package com.example.e_commerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProductRequest (

        @NotBlank(message = "product name cannot be left blank.")
       String name,

        String description,

        @NotNull(message = "stock situation must be specified.")
       Boolean isInStock,

        @NotNull(message = "product must belong to a category.")
        List<Long> categoryIds // we dont want the object itself, only its id.

){
}
