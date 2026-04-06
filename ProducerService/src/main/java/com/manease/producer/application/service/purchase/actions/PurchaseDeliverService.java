package com.manease.producer.application.service.purchase.actions;

import com.manease.producer.application.entity.purchase.PurchaseResponse;
import com.manease.producer.application.mapper.purchase.PurchaseMapper;
import com.manease.producer.application.port.in.purchase.actions.PurchaseDeliverInputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.application.port.out.purchase.PurchaseSetStatusOutputBoundary;
import com.manease.producer.application.service.purchase.exception.ProducerCannotDeliverPurchaseException;
import com.manease.producer.application.service.purchase.exception.PurchaseCannotBeDeliveredException;
import com.manease.producer.application.service.purchase.exception.PurchaseDoesNotExistException;
import com.manease.producer.domain.entity.purchase.PurchaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseDeliverService implements PurchaseDeliverInputBoundary {

    private final PurchaseStatus purchaseStatusApproved;
    private final PurchaseStatus purchaseStatusDelivered;
    private final PurchaseGetOneOutputBoundary purchaseGetOne;
    private final PurchaseSetStatusOutputBoundary purchaseSetStatus;
    private final PurchaseMapper purchaseMapper;

    @Override
    public PurchaseResponse execute(UUID purchaseId, UUID producerId) {
        var purchase = purchaseGetOne
                .getOne(purchaseId)
                .orElseThrow(() -> PurchaseDoesNotExistException.withId(purchaseId));

        if (!purchase.producerId().equals(producerId)) {
            throw ProducerCannotDeliverPurchaseException.withIds(purchaseId, producerId);
        }

        if (!purchase.purchaseStatusId().equals(purchaseStatusApproved.id())) {
            throw PurchaseCannotBeDeliveredException.withId(purchaseId);
        }

        var purchaseDelivered = purchaseSetStatus.setStatus(purchaseId, purchaseStatusDelivered.id());
        return purchaseMapper.toPurchaseResponse(purchaseDelivered);
    }
}
