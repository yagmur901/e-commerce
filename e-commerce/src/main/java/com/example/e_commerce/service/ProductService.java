package com.example.e_commerce.service;

import com.example.e_commerce.dto.ProductRequest;
import com.example.e_commerce.dto.ProductResponse;
import com.example.e_commerce.entity.Product;
import com.example.e_commerce.exception.ProductNotFoundException;
import com.example.e_commerce.mapper.ProductMapper;
import com.example.e_commerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }


    public ProductResponse createProduct(ProductRequest request) { //create

        Product product = productMapper.toEntity(request); // request to product entity
        Product savedProduct = productRepository.save(product); // db saved
        return productMapper.toResponse(savedProduct);
        // request to entity to response
    }

    public List<ProductResponse> getAllProducts() { //read

        List<Product> products = productRepository.findAll(); // get all entities from repo

        List<ProductResponse> responses = new ArrayList<>();

        for (Product product: products) { //all product entities
            responses.add(productMapper.toResponse(product)); //add them to the  list of responses that will go to client so = response
        }
        return responses;
    }



    public List<ProductResponse> getProductsByCategories(List<Long> categoryIds) {

        List<Product> products = productRepository.findDistinctByCategoriesIdIn(categoryIds); //id list from repo
        List<ProductResponse> responses = new ArrayList<>();// will be given to the client

        for(Product product: products) { // add the found products to the responses list (ofc convert them first using mapper)
            responses.add(productMapper.toResponse(product)); //product entity to response
        }
        return responses; //list filled with product responses for client
    }


    public ProductResponse getProductById(Long id) { // read only one product

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return productMapper.toResponse(product);
    }




    public List<ProductResponse> getProductsByName(String name) {

        List<Product> products = productRepository.findByName(name); //find from repo

        List<ProductResponse> responses = new ArrayList<>();
        for (Product product: products) {

            responses.add(productMapper.toResponse(product)); //entity found from repo to response list
        }
        return responses;
    }


    public List<ProductResponse> getProductsByStock(Boolean inStock) {
        List<Product> products = productRepository.findByIsInStock(inStock);

        List<ProductResponse> responses = new ArrayList<>();

        for(Product product: products) { //turn entities to responses, same as before
            responses.add(productMapper.toResponse(product));
        }
        return responses;
    }





    public ProductResponse updateProduct(Long id, ProductRequest request) {


        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        productMapper.updateEntityFromRequest(product, request);
        Product updatedProduct = productRepository.save(product);

        return productMapper.toResponse(updatedProduct);
    }

    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        //check if it exists then delete

        productRepository.delete(product);
    }









}
