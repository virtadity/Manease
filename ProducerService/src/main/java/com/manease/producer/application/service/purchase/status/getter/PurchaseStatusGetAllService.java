package com.manease.producer.application.service.purchase.status.getter;

import com.manease.producer.application.entity.response.PurchaseStatusResponse;
import com.manease.producer.application.mapper.PurchaseStatusMapper;
import com.manease.producer.application.port.in.purchase.status.getters.PurchaseStatusGetAllInputBoundary;
import com.manease.producer.application.port.out.purchase.status.getters.PurchaseStatusGetAllOutputBoundary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseStatusGetAllService implements PurchaseStatusGetAllInputBoundary {

    private final PurchaseStatusGetAllOutputBoundary purchaseStatusGetAll;
    private final PurchaseStatusMapper purchaseStatusMapper;

    @Override
    public List<PurchaseStatusResponse> execute() {
        return purchaseStatusGetAll
                .getAll()
                .stream()
                .map(purchaseStatusMapper::toPurchaseStatusResponse)
                .toList();
    }
}
