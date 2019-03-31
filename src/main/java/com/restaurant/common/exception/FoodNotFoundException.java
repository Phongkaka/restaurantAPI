package com.restaurant.common.exception;

public class FoodNotFoundException extends RuntimeException {

    public FoodNotFoundException(String exception) {
        super(exception);
    }
}
