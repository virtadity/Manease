package com.manease.producer.application.service.purchase.actions;

import com.manease.producer.application.entity.response.PurchaseResponse;
import com.manease.producer.application.mapper.PurchaseMapper;
import com.manease.producer.application.port.in.purchase.actions.PurchaseDeleteInputBoundary;
import com.manease.producer.application.port.out.purchase.actions.PurchaseSetStatusOutputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.application.service.purchase.exception.PurchaseNotFoundException;
import com.manease.producer.application.service.purchase.status.handler.DeletedPurchaseStatusHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseDeleteService implements PurchaseDeleteInputBoundary {

    private final PurchaseMapper purchaseMapper;
    private final PurchaseGetOneOutputBoundary purchaseGetOne;
    private final PurchaseSetStatusOutputBoundary purchaseSetStatus;
    private final DeletedPurchaseStatusHandler deletedPurchaseStatusHandler;

    @Override
    public PurchaseResponse execute(UUID purchaseId, UUID producerId) {
        var purchase = purchaseGetOne
                .getOneById(purchaseId)
                .orElseThrow(() -> PurchaseNotFoundException.withId(purchaseId));

        var deletedStatusId = deletedPurchaseStatusHandler.get().id();
        var deletedPurchase = purchaseSetStatus.setStatusToPurchase(purchaseId, deletedStatusId);
        return purchaseMapper.toPurchaseResponse(deletedPurchase);
    }
}
