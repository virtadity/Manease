package com.manease.producer.application.service.purchase.status.getter;

import com.manease.producer.application.entity.response.PurchaseStatusResponse;
import com.manease.producer.application.mapper.PurchaseStatusMapper;
import com.manease.producer.application.port.in.purchase.status.getters.PurchaseStatusGetOneInputBoundary;
import com.manease.producer.application.port.out.purchase.status.getters.PurchaseStatusGetOneOutputBoundary;
import com.manease.producer.application.service.purchase.status.getter.exception.PurchaseStatusNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseStatusGetOneService implements PurchaseStatusGetOneInputBoundary {

    private final PurchaseStatusGetOneOutputBoundary purchaseStatusGetOne;
    private final PurchaseStatusMapper purchaseStatusMapper;

    @Override
    public PurchaseStatusResponse execute(UUID statusId) {
        return purchaseStatusMapper.toPurchaseStatusResponse(
                purchaseStatusGetOne
                        .getOneById(statusId)
                        .orElseThrow(() -> PurchaseStatusNotFoundException.withId(statusId))
        );
    }
}
