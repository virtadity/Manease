package com.virtadity.manease.application.service.purchase.line;

import com.virtadity.manease.application.mapper.PurchaseLineMapper;
import com.virtadity.manease.application.model.purchase.line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;
import com.virtadity.manease.application.port.in.purchase.line.PurchaseLineCorrectInputBoundary;
import com.virtadity.manease.application.port.out.purchase.line.PurchaseLineCorrectOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseLineCorrectService implements PurchaseLineCorrectInputBoundary {
    private final PurchaseLineCorrectOutputBoundary purchaseLineStorageCorrect;
    private final PurchaseLineMapper purchaseLineMapper;

    @Override
    public PurchaseLineResponse execute(UUID purchaseId, UUID productId, PurchaseLineRequest purchaseLineRequest) {
        var purchaseLine = purchaseLineMapper.toPurchaseLine(purchaseLineRequest);
        var correctedPurchaseLine = purchaseLineStorageCorrect.correct(purchaseId, productId, purchaseLine);
        return purchaseLineMapper.toPurchaseLineResponse(correctedPurchaseLine);
    }
}
