package com.example.e_commerce.controller;


import com.example.e_commerce.dto.ProductRequest;
import com.example.e_commerce.dto.ProductResponse;
import com.example.e_commerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService; //controller only knows service

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping //post
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        // requestbody for client json to request, valid for rule check

        ProductResponse response = productService.createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // return new response entity with http code


    }


    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        List<ProductResponse> responses = productService.getAllProducts(); // service does the work, the ouput is response
        return ResponseEntity.ok(responses); //http code ok
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        //path var. takes the number \num from urls end and puts in id var.

        ProductResponse response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter") //http://localhost:8080/api/v1/products/filter?categoryIds= ...
    public ResponseEntity<List<ProductResponse>> getProductsByCategories(@RequestParam List<Long> categoryIds) {
        //request param = categoryIds== after ? and turn it into a list
        List<ProductResponse> responses = productService.getProductsByCategories(categoryIds);
        return ResponseEntity.ok(responses); //same as before

    }



    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> getProductsByName(@RequestParam String name) {
        //requestparam gets name in url and gives to the service
        List<ProductResponse> responses = productService.getProductsByName(name);
        return ResponseEntity.ok(responses);
    }


    @GetMapping("/stock")
    public ResponseEntity<List<ProductResponse>> getProductsByStock(@RequestParam Boolean inStock) {

        List<ProductResponse> responses = productService.getProductsByStock(inStock);
        return ResponseEntity.ok(responses);
    }







    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request
    ) {

        ProductResponse response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build(); // no content code
    }










}
