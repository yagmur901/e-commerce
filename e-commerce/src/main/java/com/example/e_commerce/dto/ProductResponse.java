package com.example.e_commerce.dto;

import java.util.List;

public record ProductResponse (
        Long id,
        String name,
        String description,
        Boolean isInStock,
        List<String> categoryNames // we will not show id, we will show the category name  to the client
){
}
