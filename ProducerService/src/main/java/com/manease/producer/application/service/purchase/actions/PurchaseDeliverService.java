package com.manease.producer.application.service.purchase.actions;

import com.manease.producer.application.entity.response.PurchaseResponse;
import com.manease.producer.application.mapper.PurchaseMapper;
import com.manease.producer.application.port.in.purchase.actions.PurchaseDeliverInputBoundary;
import com.manease.producer.application.port.out.purchase.actions.PurchaseSetStatusOutputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.application.service.purchase.exception.PurchaseNotFoundException;
import com.manease.producer.application.service.purchase.status.handler.ApprovedPurchaseStatusHandler;
import com.manease.producer.application.service.purchase.status.handler.DeliveredPurchaseStatusHandler;
import com.manease.producer.domain.service.DeliverChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseDeliverService implements PurchaseDeliverInputBoundary {

    private final PurchaseSetStatusOutputBoundary purchaseSetStatus;
    private final PurchaseMapper purchaseMapper;
    private final ApprovedPurchaseStatusHandler approvedPurchaseStatusHandler;
    private final DeliveredPurchaseStatusHandler purchaseStatusHandler;
    private final PurchaseGetOneOutputBoundary purchaseGetOne;
    private final DeliverChecker deliverChecker = new DeliverChecker();

    @Override
    public PurchaseResponse execute(UUID purchaseId, UUID producerId) {

        var purchase = purchaseGetOne
                .getOneById(purchaseId)
                .orElseThrow(() -> PurchaseNotFoundException.withId(purchaseId));

        var permittedStatuses = Set.of(
                approvedPurchaseStatusHandler.get().id()
        );

        deliverChecker.check(purchase, producerId, permittedStatuses);
        var deliveredPurchase = purchaseSetStatus.setStatusToPurchase(purchaseId, purchaseStatusHandler.get().id());
        return purchaseMapper.toPurchaseResponse(deliveredPurchase);
    }
}
