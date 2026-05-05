package com.manease.producer.domain.exception;

import java.util.UUID;

public class PurchaseCannotBeDeclinedException extends BusinessException{
    public PurchaseCannotBeDeclinedException(String message) {
        super(message);
    }

    public static PurchaseCannotBeDeclinedException withIds(UUID purchaseId) {
        var message = String.format("The purchase with id = %s cannot be declined", purchaseId);
        return new PurchaseCannotBeDeclinedException(message);
    }
}
