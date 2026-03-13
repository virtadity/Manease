package com.virtadity.manease.application.service.purchase.exception;

import com.virtadity.manease.application.exception.BusinessException;

import java.util.UUID;

public class PurchaseNotFoundException extends BusinessException {
    public PurchaseNotFoundException(String message) {
        super(message);
    }

    public static PurchaseNotFoundException withId(UUID purchaseId) {
        var message = String.format("Purchase not found with id = %s", purchaseId);
        return new PurchaseNotFoundException(message);
    }
}
