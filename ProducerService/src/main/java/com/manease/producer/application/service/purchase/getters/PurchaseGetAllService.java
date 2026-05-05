package com.manease.producer.application.service.purchase.getters;

import com.manease.producer.application.entity.response.PurchaseResponse;
import com.manease.producer.application.mapper.PurchaseMapper;
import com.manease.producer.application.port.in.purchase.getters.PurchaseGetAllInputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetAllOutputBoundary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseGetAllService implements PurchaseGetAllInputBoundary {

    private final PurchaseGetAllOutputBoundary purchaseGetAll;
    private final PurchaseMapper purchaseMapper;

    @Override
    public List<PurchaseResponse> execute(UUID producerId) {
        return purchaseGetAll
                .getAll(producerId)
                .stream()
                .map(purchaseMapper::toPurchaseResponse)
                .toList();
    }
}
