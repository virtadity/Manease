package com.virtadity.manease.application.port.in.purchase;

import java.util.UUID;

public interface PurchaseDeleteInputBoundary {
    void execute(UUID purchaseId);
}
