package com.virtadity.manease.domain.service.purchase.validation.exception;

import java.util.UUID;

public class ProducerIsDifferentException extends RuntimeException {
    public ProducerIsDifferentException(String message) {
        super(message);
    }

    public static ProducerIsDifferentException withIds(UUID purchaseId, UUID productId) {
        var message = String.format(
                "The purchase with purchaseId = %s and the product with productId = %s have different producers",
                purchaseId,
                productId
        );
        return new ProducerIsDifferentException(message);
    }
}
