package com.virtadity.manease.infrastructure.database.dao.exception;

import java.util.UUID;

public class PurchaseNotFoundException extends RuntimeException {
    public PurchaseNotFoundException(String message) {
        super(message);
    }

    public static PurchaseNotFoundException withSuchId(UUID purchaseId) {
        var message = String.format("Purchase not found with such id = %s", purchaseId);
        return new PurchaseNotFoundException(message);
    }
}
