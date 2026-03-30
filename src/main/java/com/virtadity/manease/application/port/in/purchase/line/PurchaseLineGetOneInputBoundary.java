package com.virtadity.manease.application.port.in.purchase.line;

import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;

import java.util.UUID;

public interface PurchaseLineGetOneInputBoundary {
    PurchaseLineResponse execute(UUID purchaseId, UUID productId);
}
