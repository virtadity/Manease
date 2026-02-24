package com.virtadity.manease.application.port.out.purchase_line;

import com.virtadity.manease.domain.model.PurchaseLine;

import java.util.Optional;
import java.util.UUID;

public interface PurchaseLineGetOneOutputBoundary {
    Optional<PurchaseLine> execute(UUID purchaseId, UUID productId);
}
