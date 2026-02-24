package com.virtadity.manease.application.port.out.purchase;

import com.virtadity.manease.domain.model.Purchase;

import java.util.Optional;
import java.util.UUID;

public interface PurchaseGetOneOutputBoundary {
    Optional<Purchase> execute(UUID PurchaseId);
}
