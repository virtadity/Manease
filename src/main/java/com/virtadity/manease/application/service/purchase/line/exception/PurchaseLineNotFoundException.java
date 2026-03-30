package com.virtadity.manease.application.service.purchase.line.exception;

import java.util.UUID;

public class PurchaseLineNotFoundException extends RuntimeException {
    public PurchaseLineNotFoundException(String message) {
        super(message);
    }

    public static PurchaseLineNotFoundException withIds(UUID purchaseId, UUID productId) {
        var message = String.format(
                "The purchaseLine not found with such purchaseId = %s and such productId = %s", purchaseId, productId);
        return new PurchaseLineNotFoundException(message);
    }
}
