package com.virtadity.manease.infrastructure.database.dao.exception;

import com.virtadity.manease.application.exception.BusinessException;

import java.util.UUID;

public class PurchaseEntityNotFoundException extends BusinessException {
    public PurchaseEntityNotFoundException(String message) {
        super(message);
    }

    public static PurchaseEntityNotFoundException withSuchId(UUID purchaseId) {
        var message = String.format("Purchase entity not found with such id = %s", purchaseId);
        return new PurchaseEntityNotFoundException(message);
    }
}
