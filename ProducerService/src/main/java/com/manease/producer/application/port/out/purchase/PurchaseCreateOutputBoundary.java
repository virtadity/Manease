package com.manease.producer.application.port.out.purchase;

import com.manease.producer.domain.entity.purchase.Purchase;

public interface PurchaseCreateOutputBoundary {
    Purchase create(Purchase purchase);
}
