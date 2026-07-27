package com.example.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class Product {


    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String name;

    @JoinTable(name = "product_categories", //3rd tables name
    joinColumns = @JoinColumn(name = "product_id"), //product class key
    inverseJoinColumns = @JoinColumn(name = "category_id")) //category class key
    @ManyToMany//(fetch = FetchType.LAZY) = default setting in manytomay
    private List<Category> categories;

    @Column(nullable = false)
    private Boolean isInStock = true;


}
