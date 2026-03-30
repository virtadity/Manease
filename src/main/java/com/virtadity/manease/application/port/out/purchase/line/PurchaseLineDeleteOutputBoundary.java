package com.virtadity.manease.application.port.out.purchase.line;

import java.util.UUID;

public interface PurchaseLineDeleteOutputBoundary {
    void delete(UUID purchaseId, UUID productId);
}
