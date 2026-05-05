package com.manease.producer.infrastructure.database.dao.exception;

import com.manease.producer.domain.exception.BusinessException;

import java.util.UUID;

public class PurchaseStatusNotFoundException extends BusinessException {
    public PurchaseStatusNotFoundException(String message) {
        super(message);
    }

    public static PurchaseStatusNotFoundException withId(UUID purchaseStatusId) {
        var message = String.format("The purchase status not found with id = %s", purchaseStatusId);
        return new PurchaseStatusNotFoundException(message);
    }
}
