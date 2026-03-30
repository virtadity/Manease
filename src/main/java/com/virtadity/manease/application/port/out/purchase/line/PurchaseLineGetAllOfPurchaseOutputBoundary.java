package com.virtadity.manease.application.port.out.purchase.line;

import com.virtadity.manease.domain.model.PurchaseLine;

import java.util.List;
import java.util.UUID;

public interface PurchaseLineGetAllOfPurchaseOutputBoundary {
    List<PurchaseLine> getAllOfPurchase(UUID purchaseId);
}
