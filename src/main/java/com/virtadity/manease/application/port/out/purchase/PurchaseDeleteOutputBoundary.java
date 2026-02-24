package com.virtadity.manease.application.port.out.purchase;

import java.util.UUID;

public interface PurchaseDeleteOutputBoundary {
    void execute(UUID purchaseId);
}
