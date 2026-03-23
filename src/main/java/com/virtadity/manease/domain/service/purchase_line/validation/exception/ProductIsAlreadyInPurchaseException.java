package com.virtadity.manease.domain.service.purchase_line.validation.exception;

import java.util.UUID;

public class ProductIsAlreadyInPurchaseException extends RuntimeException {
    public ProductIsAlreadyInPurchaseException(String message) {
        super(message);
    }

    public static ProductIsAlreadyInPurchaseException withIds(UUID productId, UUID purchaseId) {
        var message = String.format(
                "The product with productId = %s is already included in purchase with purchaseId = %s",
                productId,
                purchaseId
        );
        return new ProductIsAlreadyInPurchaseException(message);
    }
}
