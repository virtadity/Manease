package com.manease.producer.application.service.purchase.exception;

import com.manease.producer.application.exception.ProducerServiceBusinessException;

import java.util.UUID;

public class ProducerCannotApprovePurchaseException extends ProducerServiceBusinessException {

    public ProducerCannotApprovePurchaseException(String message) {
        super(message);
    }

    public static ProducerCannotApprovePurchaseException withIds(UUID producerId, UUID purchaseId) {
        var message = String.format(
                "The producer with id = %s cannot approve the purchase with id = %s",
                producerId,
                purchaseId
        );
        return new ProducerCannotApprovePurchaseException(message);
    }

}
