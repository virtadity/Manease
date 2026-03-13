package com.virtadity.manease.application.service.purchase_line.exception;

import com.virtadity.manease.application.exception.BusinessException;

import java.util.UUID;

public class PurchaseLineNotFoundException extends BusinessException {
    public PurchaseLineNotFoundException(String message) {
        super(message);
    }

    public static PurchaseLineNotFoundException withIds(UUID purchaseId, UUID productId) {
        var message = String.format(
                "The purchaseLine not found with such purchaseId = %s and such productId = %s", purchaseId, productId);
        return new PurchaseLineNotFoundException(message);
    }
}
