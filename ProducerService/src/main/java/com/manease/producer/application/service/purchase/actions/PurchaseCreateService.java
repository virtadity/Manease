package com.manease.producer.application.service.purchase.actions;

import com.manease.producer.application.entity.purchase.PurchaseRequest;
import com.manease.producer.application.entity.purchase.PurchaseResponse;
import com.manease.producer.application.mapper.purchase.PurchaseMapper;
import com.manease.producer.application.port.in.purchase.actions.PurchaseCreateInputBoundary;
import com.manease.producer.application.port.out.purchase.PurchaseCreateOutputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.application.service.purchase.exception.PurchaseAlreadyExistException;
import com.manease.producer.domain.entity.purchase.PurchaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PurchaseCreateService implements PurchaseCreateInputBoundary {

    private final PurchaseStatus purchaseStatusCreated;
    private final PurchaseCreateOutputBoundary purchaseCreate;
    private final PurchaseGetOneOutputBoundary purchaseGetOne;
    private final PurchaseMapper purchaseMapper;

    @Override
    public PurchaseResponse execute(PurchaseRequest purchaseRequest) {
        var purchase = purchaseMapper.toPurchase(purchaseRequest, purchaseStatusCreated.id());
        var purchaseId = purchaseRequest.id();
        purchaseGetOne.execute(purchaseId).ifPresent(
                (actualPurchase) -> { throw PurchaseAlreadyExistException.withId(purchaseId); }
        );
        var purchaseCreated = purchaseCreate.execute(purchase);
        return purchaseMapper.toPurchaseResponse(purchaseCreated);
    }

}
