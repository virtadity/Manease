package com.virtadity.manease.application.exception;

import java.util.UUID;

public class PurchaseDoesNotExistException extends RuntimeException {
    public PurchaseDoesNotExistException(String message) {
        super(message);
    }

    public static PurchaseDoesNotExistException withId(UUID purchaseId) {
        var message = String.format("Purchase does not exist with such id = %s", purchaseId);
        return new PurchaseDoesNotExistException(message);
    }
}
