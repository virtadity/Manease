package com.manease.producer.application.service.purchase.actions;

import com.manease.producer.application.entity.purchase.PurchaseResponse;
import com.manease.producer.application.mapper.purchase.PurchaseMapper;
import com.manease.producer.application.port.in.purchase.actions.PurchaseApproveInputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.application.port.out.purchase.PurchaseSetStatusOutputBoundary;
import com.manease.producer.application.service.purchase.exception.ProducerCannotApprovePurchaseException;
import com.manease.producer.application.service.purchase.exception.PurchaseCannotBeApprovedException;
import com.manease.producer.application.service.purchase.exception.PurchaseDoesNotExistException;
import com.manease.producer.domain.entity.purchase.PurchaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseApproveService implements PurchaseApproveInputBoundary {

    private final PurchaseStatus purchaseStatusApproved;
    private final PurchaseStatus purchaseStatusCreated;
    private final PurchaseGetOneOutputBoundary purchaseGetOne;
    private final PurchaseSetStatusOutputBoundary purchaseSetStatus;
    private final PurchaseMapper purchaseMapper;

    @Override
    public PurchaseResponse execute(UUID purchaseId, UUID producerId) {
        var purchase = purchaseGetOne
                .execute(purchaseId)
                .orElseThrow(() -> PurchaseDoesNotExistException.withId(purchaseId));

        if (!purchase.producerId().equals(producerId)) {
            throw ProducerCannotApprovePurchaseException.withIds(purchaseId, producerId);
        }

        if (!purchase.purchaseStatusId().equals(purchaseStatusCreated.id())) {
            throw PurchaseCannotBeApprovedException.withId(purchaseId);
        }

        var approvedPurchase = purchaseSetStatus.execute(purchaseId, purchaseStatusApproved.id());
        return purchaseMapper.toPurchaseResponse(approvedPurchase);
    }

}
