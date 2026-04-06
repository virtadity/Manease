package com.manease.producer.application.service.purchase.status;

import com.manease.producer.application.entity.purchase.status.PurchaseStatusResponse;
import com.manease.producer.application.mapper.purchase.status.PurchaseStatusMapper;
import com.manease.producer.application.port.in.purchase.status.PurchaseStatusGetOneInputBoundary;
import com.manease.producer.application.port.out.purchase.status.PurchaseStatusGetOneOutputBoundary;
import com.manease.producer.application.service.purchase.exception.PurchaseStatusNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseStatusGetOneService implements PurchaseStatusGetOneInputBoundary {

    private PurchaseStatusGetOneOutputBoundary purchaseStatusGetOne;
    private PurchaseStatusMapper purchaseStatusMapper;

    @Override
    public PurchaseStatusResponse execute(UUID id) {
        var purchaseStatus = purchaseStatusGetOne
                .getOne(id)
                .orElseThrow(() -> PurchaseStatusNotFoundException.withId(id));
        return purchaseStatusMapper.toPurchaseStatusResponse(purchaseStatus);
    }
}
