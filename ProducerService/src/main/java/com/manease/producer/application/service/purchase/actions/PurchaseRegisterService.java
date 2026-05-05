package com.manease.producer.application.service.purchase.actions;

import com.manease.producer.application.entity.request.PurchaseRequest;
import com.manease.producer.application.entity.response.PurchaseResponse;
import com.manease.producer.application.mapper.PurchaseMapper;
import com.manease.producer.application.port.in.purchase.actions.PurchaseRegisterInputBoundary;
import com.manease.producer.application.port.out.purchase.actions.PurchaseCreateOutputBoundary;
import com.manease.producer.application.service.purchase.status.handler.CreatedPurchaseStatusHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseRegisterService implements PurchaseRegisterInputBoundary {

    private final PurchaseCreateOutputBoundary purchaseCreate;
    private final CreatedPurchaseStatusHandler purchaseStatusHandler;
    private final PurchaseMapper purchaseMapper;

    @Override
    public PurchaseResponse execute(PurchaseRequest purchaseRequest) {
        var createdStatusId = purchaseStatusHandler.get().id();
        var purchase = purchaseMapper.toPurchase(purchaseRequest, createdStatusId);
        var createdPurchase = purchaseCreate.createPurchase(purchase);
        return purchaseMapper.toPurchaseResponse(createdPurchase);
    }
}
