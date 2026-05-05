package com.manease.producer.application.service.purchase.actions;

import com.manease.producer.application.entity.response.PurchaseResponse;
import com.manease.producer.application.mapper.PurchaseMapper;
import com.manease.producer.application.port.in.purchase.actions.PurchaseApproveInputBoundary;
import com.manease.producer.application.port.out.purchase.actions.PurchaseSetStatusOutputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.application.service.purchase.exception.PurchaseNotFoundException;
import com.manease.producer.application.service.purchase.status.handler.ApprovedPurchaseStatusHandler;
import com.manease.producer.application.service.purchase.status.handler.CreatedPurchaseStatusHandler;
import com.manease.producer.domain.service.ApproveChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseApproveService implements PurchaseApproveInputBoundary {

    private final PurchaseGetOneOutputBoundary purchaseGetOne;
    private final CreatedPurchaseStatusHandler createdPurchaseStatusHandler;
    private final ApprovedPurchaseStatusHandler approvedPurchaseStatusHandler;
    private final PurchaseSetStatusOutputBoundary purchaseSetStatus;
    private final PurchaseMapper purchaseMapper;
    private final ApproveChecker approveChecker = new ApproveChecker();

    @Override
    public PurchaseResponse execute(UUID purchaseId, UUID producerId) {
        var purchase = purchaseGetOne
                .getOneById(purchaseId)
                .orElseThrow(() -> PurchaseNotFoundException.withId(purchaseId));

        var permittedStatuses = Set.of(createdPurchaseStatusHandler.get().id());

        approveChecker.check(purchase, producerId, permittedStatuses);

        var approvedStatus = purchaseSetStatus
                .setStatusToPurchase(purchaseId, approvedPurchaseStatusHandler.get().id());

        return purchaseMapper.toPurchaseResponse(approvedStatus);
    }
}
