package com.virtadity.manease.application.port.out.purchase.line;

import com.virtadity.manease.domain.model.PurchaseLine;

import java.util.UUID;

public interface PurchaseLineCorrectOutputBoundary {
    PurchaseLine correct(UUID purchaseId, UUID productId, PurchaseLine purchaseLine);
}
