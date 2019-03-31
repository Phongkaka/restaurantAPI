package com.restaurant.common.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String exception) {
        super(exception);
    }
}
