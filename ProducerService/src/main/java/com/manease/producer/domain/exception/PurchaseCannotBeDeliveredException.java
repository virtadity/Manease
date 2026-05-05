package com.manease.producer.domain.exception;

import java.util.UUID;

public class PurchaseCannotBeDeliveredException extends BusinessException {
    public PurchaseCannotBeDeliveredException(String message) {
        super(message);
    }

    public static PurchaseCannotBeDeliveredException withId(UUID purchaseId) {
        var message = String.format("The purchase with id = %s cannot be delivered", purchaseId);
        return new PurchaseCannotBeDeliveredException(message);
    }
}
