package com.manease.producer.domain.service;

import com.manease.producer.domain.entity.Purchase;
import com.manease.producer.domain.exception.PurchaseCannotBeApprovedException;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class ApproveChecker {

    private final PurchaseGetOneChecker purchaseGetOneChecker = new PurchaseGetOneChecker();

    public void check(Purchase purchase, UUID producerId, Set<UUID> permittedStatusSet) {
        purchaseGetOneChecker.check(purchase, producerId);

        if (!permittedStatusSet.contains(purchase.id())) {
            throw PurchaseCannotBeApprovedException.withId(purchase.statusId());
        }
    }
}
