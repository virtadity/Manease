package com.virtadity.manease.application.port.out.purchase_line;

import com.virtadity.manease.domain.model.PurchaseLine;

import java.util.UUID;

public interface PurchaseLineCorrectOutputBoundary {
    PurchaseLine execute(UUID purchaseId, UUID productId, PurchaseLine purchaseLine);
}
