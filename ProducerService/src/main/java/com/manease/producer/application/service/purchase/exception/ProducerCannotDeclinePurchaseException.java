package com.manease.producer.application.service.purchase.exception;

import com.manease.producer.application.exception.ProducerServiceBusinessException;

import java.util.UUID;

public class ProducerCannotDeclinePurchaseException extends ProducerServiceBusinessException {

    public ProducerCannotDeclinePurchaseException(String message) {
        super(message);
    }

    public static ProducerCannotDeclinePurchaseException withIds(UUID producerId, UUID purchaseId) {
        var message = String.format(
                "The producer with id = %s cannot decline the purchase with id = %s",
                producerId,
                purchaseId
        );
        return new ProducerCannotDeclinePurchaseException(message);
    }
}
