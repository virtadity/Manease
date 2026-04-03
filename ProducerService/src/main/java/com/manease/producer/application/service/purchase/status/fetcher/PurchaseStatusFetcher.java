package com.manease.producer.application.service.purchase.status.fetcher;

import com.manease.producer.domain.entity.purchase.PurchaseStatus;

public interface PurchaseStatusFetcher {
    PurchaseStatus fetchOrCreatePurchaseStatus(PurchaseStatus purchaseStatus);
}
