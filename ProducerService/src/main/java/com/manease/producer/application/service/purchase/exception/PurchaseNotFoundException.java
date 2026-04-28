package com.manease.producer.application.service.purchase.exception;

import com.manease.producer.domain.exception.BusinessException;

import java.util.UUID;

public class PurchaseNotFoundException extends BusinessException {
    public PurchaseNotFoundException(String message) {
        super(message);
    }

    public static PurchaseNotFoundException withId(UUID purchaseId) {
        var message = String.format("The purchase with id = %s not found", purchaseId);
        return new PurchaseNotFoundException(message);
    }
}
