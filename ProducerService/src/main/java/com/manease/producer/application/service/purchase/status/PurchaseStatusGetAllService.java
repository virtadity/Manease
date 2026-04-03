package com.manease.producer.application.service.purchase.status;

import com.manease.producer.application.entity.purchase.status.PurchaseStatusResponse;
import com.manease.producer.application.mapper.purchase.status.PurchaseStatusMapper;
import com.manease.producer.application.port.in.purchase.status.PurchaseStatusGetAllInputBoundary;
import com.manease.producer.application.port.out.purchase.status.PurchaseStatusGetAllOutputBoundary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseStatusGetAllService implements PurchaseStatusGetAllInputBoundary {

    private final PurchaseStatusGetAllOutputBoundary purchaseStatusGetAll;
    private final PurchaseStatusMapper purchaseStatusMapper;

    @Override
    public List<PurchaseStatusResponse> execute() {
        return purchaseStatusMapper.toPurchaseStatusResponseList(purchaseStatusGetAll.execute());
    }
}
