package com.virtadity.manease.application.port.out.purchase_line;

import java.util.UUID;

public interface PurchaseLineDeleteOutputBoundary {
    void delete(UUID purchaseId, UUID productId);
}
