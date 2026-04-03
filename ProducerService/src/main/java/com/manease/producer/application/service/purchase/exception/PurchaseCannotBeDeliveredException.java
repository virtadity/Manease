package com.manease.producer.application.service.purchase.exception;

import com.manease.producer.application.exception.ProducerServiceBusinessException;

import java.util.UUID;

public class PurchaseCannotBeDeliveredException extends ProducerServiceBusinessException {

    public PurchaseCannotBeDeliveredException(String message) {
        super(message);
    }

    public static PurchaseCannotBeDeliveredException withId(UUID purchaseId) {
        var message = String.format("The purchase with id = %s cannot be delivered", purchaseId);
        return new PurchaseCannotBeDeliveredException(message);
    }
}
