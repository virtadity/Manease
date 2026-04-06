package com.manease.producer.infrastructure.database.dao.exception;

import com.manease.producer.application.exception.ProducerServiceBusinessException;

import java.util.UUID;

public class PurchaseEntityNotFoundException extends ProducerServiceBusinessException {
    public PurchaseEntityNotFoundException(String message) {
        super(message);
    }

    public static PurchaseEntityNotFoundException withId(UUID id) {
        var message = String.format("The purchase entity not found with id = %s", id);
        return new PurchaseEntityNotFoundException(message);
    }
}
