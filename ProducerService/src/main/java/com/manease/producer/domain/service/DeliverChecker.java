package com.manease.producer.domain.service;

import com.manease.producer.domain.entity.Purchase;
import com.manease.producer.domain.exception.PurchaseCannotBeDeliveredException;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class DeliverChecker {

    private final PurchaseGetOneChecker purchaseGetOneChecker = new PurchaseGetOneChecker();

    public void check(Purchase purchase, UUID producerId, Set<UUID> statuses) {
        purchaseGetOneChecker.check(purchase, producerId);

        if (!statuses.contains(purchase.statusId())) {
            throw PurchaseCannotBeDeliveredException.withId(purchase.id());
        }
    }
}
