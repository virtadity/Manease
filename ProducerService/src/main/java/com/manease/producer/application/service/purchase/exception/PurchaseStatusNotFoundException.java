package com.manease.producer.application.service.purchase.exception;

import com.manease.producer.application.exception.ProducerServiceBusinessException;

import java.util.UUID;

public class PurchaseStatusNotFoundException extends ProducerServiceBusinessException {
    public PurchaseStatusNotFoundException(String message) {
        super(message);
    }

    public static PurchaseStatusNotFoundException withId(UUID purchaseStatusId) {
        var message = String.format("The purchase status with id = %s not found", purchaseStatusId);
        return new PurchaseStatusNotFoundException(message);
    }
}
