package com.manease.producer.application.service.purchase.exception;

import com.manease.producer.application.exception.ProducerServiceBusinessException;

import java.util.UUID;

public class PurchaseAlreadyExistException extends ProducerServiceBusinessException {
    public PurchaseAlreadyExistException(String message) {
        super(message);
    }
    public static PurchaseAlreadyExistException withId(UUID id) {
        var message = String.format("The purchase already exist with id = %s", id);
        return new PurchaseAlreadyExistException(message);
    }
}
