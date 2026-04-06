package com.manease.producer.application.service.purchase.getter;

import com.manease.producer.application.entity.purchase.PurchaseResponse;
import com.manease.producer.application.mapper.purchase.PurchaseMapper;
import com.manease.producer.application.port.in.purchase.getters.PurchaseGetAllDeclinedInputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetAllWithStatusOutputBoundary;
import com.manease.producer.domain.entity.purchase.PurchaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseGetAllDeclinedService implements PurchaseGetAllDeclinedInputBoundary {

    private final PurchaseStatus purchaseStatusDeclined;
    private final PurchaseGetAllWithStatusOutputBoundary purchaseGetAllWithStatus;
    private final PurchaseMapper purchaseMapper;


    @Override
    public List<PurchaseResponse> execute(UUID producerId) {
        return purchaseMapper.toPurchaseResponseList(
                purchaseGetAllWithStatus.getAllWith(producerId, purchaseStatusDeclined.id())
        );
    }
}
