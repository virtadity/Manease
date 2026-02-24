package com.virtadity.manease.application.service.purchase_line;

import com.virtadity.manease.application.mapper.PurchaseLineMapper;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;
import com.virtadity.manease.application.port.in.purchase_line.PurchaseLineGetOneInputBoundary;
import com.virtadity.manease.application.port.out.purchase_line.PurchaseLineGetOneOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseLineGetOneService implements PurchaseLineGetOneInputBoundary {
    private final PurchaseLineGetOneOutputBoundary purchaseLineStorageGetOne;
    private final PurchaseLineMapper purchaseLineMapper;

    @Override
    public Optional<PurchaseLineResponse> execute(UUID purchaseId, UUID productId) {
        var purchaseLine = purchaseLineStorageGetOne.execute(purchaseId, productId);
        return purchaseLine.map(purchaseLineMapper::toPurchaseLineResponse);
    }
}
