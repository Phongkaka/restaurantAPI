package com.restaurant.common.exception;

public class OrderDetailNotFoundException extends RuntimeException {

    public OrderDetailNotFoundException(String exception) {
        super(exception);
    }
}
