package com.restaurant.common.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String exception) {
        super(exception);
    }
}
