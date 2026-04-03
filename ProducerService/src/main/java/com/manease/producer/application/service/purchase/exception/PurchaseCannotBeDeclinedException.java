package com.manease.producer.application.service.purchase.exception;

import com.manease.producer.application.exception.ProducerServiceBusinessException;

import java.util.UUID;

public class PurchaseCannotBeDeclinedException extends ProducerServiceBusinessException {

    public PurchaseCannotBeDeclinedException(String message) {
        super(message);
    }

    public static PurchaseCannotBeDeclinedException withId(UUID purchaseId) {
        var message = String.format("The purchase with id = %s cannot be declined", purchaseId);
        return new PurchaseCannotBeDeclinedException(message);
    }

}
