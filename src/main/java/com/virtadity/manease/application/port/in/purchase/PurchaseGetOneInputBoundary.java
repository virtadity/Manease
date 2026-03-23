package com.virtadity.manease.application.port.in.purchase;

import com.virtadity.manease.application.model.purchase.PurchaseResponse;

import java.util.Optional;
import java.util.UUID;

public interface PurchaseGetOneInputBoundary {
    PurchaseResponse execute(UUID purchaseId);
}
