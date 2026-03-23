package com.virtadity.manease.application.port.in.purchase_line;

import com.virtadity.manease.application.model.purchase_line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;

import java.util.UUID;

public interface PurchaseLineCorrectInputBoundary {
    PurchaseLineResponse execute(UUID purchaseId, UUID productId, PurchaseLineRequest purchaseLineRequest);
}
