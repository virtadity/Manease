package com.manease.producer.application.port.in.purchase.actions;

import com.manease.producer.application.entity.purchase.PurchaseRequest;
import com.manease.producer.application.entity.purchase.PurchaseResponse;

public interface PurchaseCreateInputBoundary {
    PurchaseResponse execute(PurchaseRequest purchaseRequest);
}
