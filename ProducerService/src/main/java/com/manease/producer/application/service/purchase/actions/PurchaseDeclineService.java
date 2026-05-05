package com.manease.producer.application.service.purchase.actions;

import com.manease.producer.application.entity.response.PurchaseResponse;
import com.manease.producer.application.mapper.PurchaseMapper;
import com.manease.producer.application.port.in.purchase.actions.PurchaseDeclineInputBoundary;
import com.manease.producer.application.port.out.purchase.actions.PurchaseSetStatusOutputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.application.service.purchase.exception.PurchaseNotFoundException;
import com.manease.producer.application.service.purchase.status.handler.ApprovedPurchaseStatusHandler;
import com.manease.producer.application.service.purchase.status.handler.CreatedPurchaseStatusHandler;
import com.manease.producer.application.service.purchase.status.handler.DeclinedPurchaseStatusHandler;
import com.manease.producer.domain.service.DeclineChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseDeclineService implements PurchaseDeclineInputBoundary {

    private final DeclineChecker purchaseDeclineChecker = new DeclineChecker();
    private final CreatedPurchaseStatusHandler createdPurchaseStatusHandler;
    private final ApprovedPurchaseStatusHandler approvedPurchaseStatusHandler;
    private final PurchaseSetStatusOutputBoundary purchaseSetStatus;
    private final DeclinedPurchaseStatusHandler purchaseStatusHandler;
    private final PurchaseGetOneOutputBoundary purchaseGetOne;
    private final PurchaseMapper purchaseMapper;

    @Override
    public PurchaseResponse execute(UUID purchaseId, UUID producerId) {
        var purchase = purchaseGetOne
                .getOneById(purchaseId)
                .orElseThrow(() -> PurchaseNotFoundException.withId(purchaseId));

        var permittedStatuses = Set.of(
                approvedPurchaseStatusHandler.get().id(),
                createdPurchaseStatusHandler.get().id()
        );

        purchaseDeclineChecker.check(purchase, producerId, permittedStatuses);
        var declinedPurchase = purchaseSetStatus.setStatusToPurchase(purchaseId, purchaseStatusHandler.get().id());
        return purchaseMapper.toPurchaseResponse(declinedPurchase);
    }
}
