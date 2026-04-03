package com.manease.producer.application.service.purchase.exception;

import com.manease.producer.application.exception.ProducerServiceBusinessException;

import java.util.UUID;

public class PurchaseDoesNotExistException extends ProducerServiceBusinessException {

    public PurchaseDoesNotExistException(String message) {
        super(message);
    }

    public static PurchaseDoesNotExistException withId(UUID id) {
        var message = String.format("A purchase does not exist with id = %s", id);
        return new PurchaseDoesNotExistException(message);
    }
}
