package com.manease.producer.domain.exception;

import java.util.UUID;

public class ProducerHasNoAccessToPurchaseException extends BusinessException {
    public ProducerHasNoAccessToPurchaseException(String message) {
        super(message);
    }

    public static ProducerHasNoAccessToPurchaseException withId(UUID producerId, UUID purchaseId) {
        var message = String.format(
                "The producer with id = %s has no access to the purchase with id = %s",
                producerId,
                purchaseId
        );
        return new ProducerHasNoAccessToPurchaseException(message);
    }
}
