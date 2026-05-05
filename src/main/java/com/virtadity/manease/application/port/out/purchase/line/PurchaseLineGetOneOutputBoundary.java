package com.virtadity.manease.application.port.out.purchase.line;

import com.virtadity.manease.domain.model.PurchaseLine;

import java.util.Optional;
import java.util.UUID;

public interface PurchaseLineGetOneOutputBoundary {
    Optional<PurchaseLine> getOne(UUID purchaseId, UUID productId);
}
