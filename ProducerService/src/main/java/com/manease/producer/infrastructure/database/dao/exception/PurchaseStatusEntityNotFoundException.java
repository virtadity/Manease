package com.manease.producer.infrastructure.database.dao.exception;

import com.manease.producer.application.exception.ProducerServiceBusinessException;
import com.manease.producer.application.service.purchase.exception.PurchaseStatusNotFoundException;

import java.util.UUID;

public class PurchaseStatusEntityNotFoundException extends ProducerServiceBusinessException {
    public PurchaseStatusEntityNotFoundException(String message) {
        super(message);
    }

    public static PurchaseStatusNotFoundException withId(UUID purchaseStatusId) {
        var message = String.format("The purchase status not found with id = %s", purchaseStatusId);
        return new PurchaseStatusNotFoundException(message);
    }
}
