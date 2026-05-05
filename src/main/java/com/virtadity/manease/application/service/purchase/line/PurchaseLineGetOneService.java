package com.virtadity.manease.application.service.purchase.line;

import com.virtadity.manease.application.mapper.PurchaseLineMapper;
import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;
import com.virtadity.manease.application.port.in.purchase.line.PurchaseLineGetOneInputBoundary;
import com.virtadity.manease.application.port.out.purchase.line.PurchaseLineGetOneOutputBoundary;
import com.virtadity.manease.application.service.purchase.line.exception.PurchaseLineNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseLineGetOneService implements PurchaseLineGetOneInputBoundary {
    private final PurchaseLineGetOneOutputBoundary purchaseLineStorageGetOne;
    private final PurchaseLineMapper purchaseLineMapper;

    @Override
    public PurchaseLineResponse execute(UUID purchaseId, UUID productId) {
        var purchaseLine = purchaseLineStorageGetOne
                .getOne(purchaseId, productId)
                .orElseThrow(() -> PurchaseLineNotFoundException.withIds(purchaseId, productId));
        return purchaseLineMapper.toPurchaseLineResponse(purchaseLine);
    }
}
