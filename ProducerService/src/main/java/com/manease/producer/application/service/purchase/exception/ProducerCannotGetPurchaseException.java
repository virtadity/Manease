package com.manease.producer.application.service.purchase.exception;

import com.manease.producer.application.exception.ProducerServiceBusinessException;

import java.util.UUID;

public class ProducerCannotGetPurchaseException extends ProducerServiceBusinessException {
    public ProducerCannotGetPurchaseException(String message) {
        super(message);
    }

    public static ProducerCannotGetPurchaseException withIds(UUID producerId, UUID purchaseId) {
        var message = String.format(
                "The producer with id = %s cannot get purchase with id = %s",
                producerId,
                purchaseId
        );
        return new ProducerCannotGetPurchaseException(message);
    }
}
