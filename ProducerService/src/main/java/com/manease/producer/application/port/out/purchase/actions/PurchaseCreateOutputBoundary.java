package com.manease.producer.application.port.out.purchase.actions;

import com.manease.producer.domain.entity.Purchase;

public interface PurchaseCreateOutputBoundary {
    Purchase createPurchase(Purchase purchase);
}
