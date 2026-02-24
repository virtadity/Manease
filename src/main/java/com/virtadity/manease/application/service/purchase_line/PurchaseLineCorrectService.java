package com.virtadity.manease.application.service.purchase_line;

import com.virtadity.manease.application.mapper.PurchaseLineMapper;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;
import com.virtadity.manease.application.port.in.purchase_line.PurchaseLineCorrectInputBoundary;
import com.virtadity.manease.application.port.out.purchase_line.PurchaseLineCorrectOutputBoundary;
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
        var correctedPurchaseLine = purchaseLineStorageCorrect.execute(purchaseId, productId, purchaseLine);
        return purchaseLineMapper.toPurchaseLineResponse(correctedPurchaseLine);
    }
}
