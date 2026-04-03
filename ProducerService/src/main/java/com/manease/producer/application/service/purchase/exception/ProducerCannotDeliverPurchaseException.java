package com.manease.producer.application.service.purchase.exception;

import com.manease.producer.application.exception.ProducerServiceBusinessException;

import java.util.UUID;

public class ProducerCannotDeliverPurchaseException extends ProducerServiceBusinessException {
    public ProducerCannotDeliverPurchaseException(String message) {
        super(message);
    }

    public static ProducerCannotDeliverPurchaseException withIds(UUID producerId, UUID purchaseId) {
        var message = String.format(
                "The producer with id = %s cannot deliver the purchase with id = %s",
                producerId,
                purchaseId
        );
        return new ProducerCannotDeliverPurchaseException(message);
    }
}
