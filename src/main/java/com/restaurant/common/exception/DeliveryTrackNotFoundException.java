package com.restaurant.common.exception;

public class DeliveryTrackNotFoundException extends RuntimeException {

    public DeliveryTrackNotFoundException(String exception) {
        super(exception);
    }
}
