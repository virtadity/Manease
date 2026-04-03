package com.manease.producer.application.service.purchase.exception;

import com.manease.producer.application.exception.ProducerServiceBusinessException;

import java.util.UUID;

public class PurchaseCannotBeApprovedException extends ProducerServiceBusinessException {

    public PurchaseCannotBeApprovedException(String message) {
        super(message);
    }

    public static PurchaseCannotBeApprovedException withId(UUID purchaseId) {
        var message = String.format("The purchase with id = %s cannot be approved", purchaseId);
        return new PurchaseCannotBeApprovedException(message);
    }

}
