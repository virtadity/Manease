package com.manease.producer.application.service.purchase.getters;

import com.manease.producer.application.entity.response.PurchaseResponse;
import com.manease.producer.application.mapper.PurchaseMapper;
import com.manease.producer.application.port.in.purchase.getters.PurchaseGetOneInputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.domain.service.PurchaseGetOneChecker;
import com.manease.producer.infrastructure.database.dao.exception.PurchaseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseGetOneService implements PurchaseGetOneInputBoundary {

    private final PurchaseGetOneOutputBoundary purchaseGetOne;
    private final PurchaseMapper purchaseMapper;
    private final PurchaseGetOneChecker purchaseGetOneChecker = new PurchaseGetOneChecker();

    @Override
    public PurchaseResponse execute(UUID purchaseId, UUID producerId) {
        var purchase = purchaseGetOne
                .getOneById(purchaseId)
                .orElseThrow(() -> PurchaseNotFoundException.withId(purchaseId));

        purchaseGetOneChecker.check(purchase, producerId);

        return purchaseMapper.toPurchaseResponse(purchase);
    }
}
