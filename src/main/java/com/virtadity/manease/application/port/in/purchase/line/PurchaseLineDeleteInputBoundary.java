package com.virtadity.manease.application.port.in.purchase.line;

import java.util.UUID;

public interface PurchaseLineDeleteInputBoundary {
    void execute(UUID purchaseId, UUID productId);
}
