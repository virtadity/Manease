package com.manease.producer.application.service.purchase.getter;

import com.manease.producer.application.entity.purchase.PurchaseResponse;
import com.manease.producer.application.mapper.purchase.PurchaseMapper;
import com.manease.producer.application.port.in.purchase.getters.PurchaseGetOneInputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.application.service.purchase.exception.ProducerCannotGetPurchaseException;
import com.manease.producer.application.service.purchase.exception.PurchaseDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseGetOneService  implements PurchaseGetOneInputBoundary {

    private final PurchaseGetOneOutputBoundary purchaseGetOne;
    private final PurchaseMapper purchaseMapper;

    @Override
    public PurchaseResponse execute(UUID id, UUID producerId) {
        var purchase = purchaseGetOne.execute(id).orElseThrow(() -> PurchaseDoesNotExistException.withId(id));

        if (!purchase.producerId().equals(producerId)) {
            throw ProducerCannotGetPurchaseException.withIds(producerId, id);
        }

        return purchaseMapper.toPurchaseResponse(purchase);
    }
}
