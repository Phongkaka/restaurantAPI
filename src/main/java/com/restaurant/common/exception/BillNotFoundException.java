package com.restaurant.common.exception;

public class BillNotFoundException extends RuntimeException {

    public BillNotFoundException(String exception) {
        super(exception);
    }
}
