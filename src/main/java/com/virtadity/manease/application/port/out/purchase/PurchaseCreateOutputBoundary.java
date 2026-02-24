package com.virtadity.manease.application.port.out.purchase;

import com.virtadity.manease.domain.model.Purchase;

public interface PurchaseCreateOutputBoundary {
    Purchase execute(Purchase purchase);
}
