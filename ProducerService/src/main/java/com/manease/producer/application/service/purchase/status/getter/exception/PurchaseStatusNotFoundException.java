package com.manease.producer.application.service.purchase.status.getter.exception;

import com.manease.producer.domain.exception.BusinessException;

import java.util.UUID;

public class PurchaseStatusNotFoundException extends BusinessException {
    public PurchaseStatusNotFoundException(String message) {
        super(message);
    }

    public static PurchaseStatusNotFoundException withId(UUID purchaseStatusId) {
        var message = String.format("The purchase status with id = %s not found", purchaseStatusId);
        return new PurchaseStatusNotFoundException(message);
    }
}
