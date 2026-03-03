package com.virtadity.manease.application.port.out.purchase;

import com.virtadity.manease.domain.model.Purchase;

import java.util.UUID;

public interface PurchaseCorrectOutputBoundary {
    Purchase correct(UUID purchaseId, Purchase purchase);
}
