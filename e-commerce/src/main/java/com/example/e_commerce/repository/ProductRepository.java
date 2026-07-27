package com.example.e_commerce.repository;


import com.example.e_commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findDistinctByCategoriesIdIn(List<Long> ids);
    List<Product> findByIsInStock(Boolean isInStock);
    List<Product> findByName(String productName);
    //find by id is already in jpa repo.

}