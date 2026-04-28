package com.manease.producer.domain.exception;

import java.util.UUID;

public class PurchaseCannotBeApprovedException extends BusinessException{
    public PurchaseCannotBeApprovedException(String message) {
        super(message);
    }

    public static PurchaseCannotBeApprovedException withId(UUID purchaseId) {
        var message = String.format("The purchase with id = %s cannot be approved", purchaseId);
        return new PurchaseCannotBeApprovedException(message);
    }
}
