package com.manease.producer.application.service.purchase.status.fetcher;

import com.manease.producer.application.port.out.purchase.status.PurchaseStatusCreateOutputBoundary;
import com.manease.producer.application.port.out.purchase.status.PurchaseStatusGetByNameOutputBoundary;
import com.manease.producer.domain.entity.purchase.PurchaseStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseStatusFetcherImpl implements PurchaseStatusFetcher {

    private final PurchaseStatusGetByNameOutputBoundary getStatusByName;
    private final PurchaseStatusCreateOutputBoundary createPurchaseStatus;

    @Override
    @Transactional
    public PurchaseStatus fetchOrCreatePurchaseStatus(PurchaseStatus purchaseStatus) {
        return getStatusByName
                .execute(purchaseStatus.name())
                .orElseGet(() -> createPurchaseStatus.create(purchaseStatus));
    }
}
