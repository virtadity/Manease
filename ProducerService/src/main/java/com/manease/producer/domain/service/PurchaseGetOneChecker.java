package com.manease.producer.domain.service;

import com.manease.producer.domain.exception.ProducerHasNoAccessToPurchaseException;
import com.manease.producer.domain.entity.Purchase;

import java.util.UUID;

public class PurchaseGetOneChecker {
    public void check(Purchase purchase, UUID producerId) {
        if (!purchase.producerId().equals(producerId)) {
            throw ProducerHasNoAccessToPurchaseException.withId(producerId, purchase.id());
        }
    }
}
