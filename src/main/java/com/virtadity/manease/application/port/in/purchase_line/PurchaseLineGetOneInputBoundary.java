package com.virtadity.manease.application.port.in.purchase_line;

import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;

import java.util.Optional;
import java.util.UUID;

public interface PurchaseLineGetOneInputBoundary {
    PurchaseLineResponse execute(UUID purchaseId, UUID productId);
}
