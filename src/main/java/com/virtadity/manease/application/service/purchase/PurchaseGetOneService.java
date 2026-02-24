package com.virtadity.manease.application.service.purchase;

import com.virtadity.manease.application.mapper.PurchaseMapper;
import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.application.port.in.purchase.PurchaseGetOneInputBoundary;
import com.virtadity.manease.application.port.out.purchase.PurchaseGetOneOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseGetOneService implements PurchaseGetOneInputBoundary {
    private final PurchaseGetOneOutputBoundary purchaseStorageGetOne;
    private final PurchaseMapper purchaseMapper;

    @Override
    public Optional<PurchaseResponse> execute(UUID purchaseId) {
        var purchase = purchaseStorageGetOne.execute(purchaseId);
        return purchase.map(purchaseMapper::toPurchaseResponse);
    }
}
