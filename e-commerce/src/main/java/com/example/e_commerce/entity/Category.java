package com.example.e_commerce.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Category {


    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk- db auto increment
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 255)
    private String description;

    @ManyToMany(mappedBy = "categories") // = list in product class
    private List<Product> products;
}
