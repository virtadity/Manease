package com.virtadity.manease.application.port.in.purchase;

import com.virtadity.manease.application.model.purchase.PurchaseRequest;
import com.virtadity.manease.application.model.purchase.PurchaseResponse;

import java.util.UUID;

public interface PurchaseCorrectInputBoundary {
    PurchaseResponse execute(UUID purchaseId, PurchaseRequest purchaseRequest);
}
