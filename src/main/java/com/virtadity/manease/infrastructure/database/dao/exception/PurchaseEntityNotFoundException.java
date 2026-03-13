package com.virtadity.manease.infrastructure.database.dao.exception;

import java.util.UUID;

public class PurchaseEntityNotFoundException extends RuntimeException {
    public PurchaseEntityNotFoundException(String message) {
        super(message);
    }

    public static PurchaseEntityNotFoundException withSuchId(UUID purchaseId) {
        var message = String.format("Purchase entity not found with such id = %s", purchaseId);
        return new PurchaseEntityNotFoundException(message);
    }
}
