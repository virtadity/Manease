package com.manease.producer.application.port.in.purchase.actions;

import com.manease.producer.application.entity.request.PurchaseRequest;
import com.manease.producer.application.entity.response.PurchaseResponse;

public interface PurchaseRegisterInputBoundary {
    PurchaseResponse execute(PurchaseRequest purchaseRequest);
}
