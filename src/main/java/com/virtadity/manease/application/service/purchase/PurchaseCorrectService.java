package com.virtadity.manease.application.service.purchase;

import com.virtadity.manease.application.mapper.PurchaseMapper;
import com.virtadity.manease.application.model.purchase.PurchaseRequest;
import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.application.port.in.purchase.PurchaseCorrectInputBoundary;
import com.virtadity.manease.application.port.out.purchase.PurchaseCorrectOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseCorrectService implements PurchaseCorrectInputBoundary {

    private final PurchaseCorrectOutputBoundary purchaseStorageCorrect;
    private final PurchaseMapper purchaseMapper;

    @Override
    public PurchaseResponse execute(UUID purchaseId, PurchaseRequest purchaseRequest) {
        var purchase = purchaseMapper.toPurchase(purchaseRequest);
        var correctedPurchase = purchaseStorageCorrect.correct(purchaseId, purchase);
        return purchaseMapper.toPurchaseResponse(purchase);
    }
}
