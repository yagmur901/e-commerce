package com.example.e_commerce.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) //overlapping names exception
public class DuplicateCategoryException extends RuntimeException {

    public DuplicateCategoryException(String message) {
        super(message);
    }
}