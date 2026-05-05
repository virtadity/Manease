package com.manease.producer.infrastructure.database.dao.exception;

import com.manease.producer.domain.exception.BusinessException;

import java.util.UUID;

public class PurchaseNotFoundException extends BusinessException {
    public PurchaseNotFoundException(String message) {
        super(message);
    }

    public static PurchaseNotFoundException withId(UUID purchaseId) {
        var message = String.format("The purchase not found with id = %s", purchaseId);
        return new PurchaseNotFoundException(message);
    }
}
