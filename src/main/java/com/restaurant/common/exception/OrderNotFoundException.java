package com.restaurant.common.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String exception) {
        super(exception);
    }
}
