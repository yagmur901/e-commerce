package com.example.e_commerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest( //likea a form client fills

    @NotBlank(message = "category name cannot be left blank.")
    @Size(min = 2, max = 50, message = "category name must be between 2 and 50 characters.")
    String name,

    @Size(max = 255, message = "description cannot be more than 255 characters.")
    String description
    ) {}

