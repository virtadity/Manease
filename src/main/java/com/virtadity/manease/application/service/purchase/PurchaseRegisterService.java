package com.virtadity.manease.application.service.purchase;

import com.virtadity.manease.application.mapper.PurchaseMapper;
import com.virtadity.manease.application.model.purchase.PurchaseRequest;
import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.application.port.in.purchase.PurchaseRegisterInputBoundary;
import com.virtadity.manease.application.port.out.purchase.PurchaseCreateOutputBoundary;
import com.virtadity.manease.application.service.purchase.event.PurchaseRegisteredEvent;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseRegisterService implements PurchaseRegisterInputBoundary {
    private final PurchaseCreateOutputBoundary purchaseStorageRegister;
    private final PurchaseMapper purchaseMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public PurchaseResponse execute(PurchaseRequest purchaseRequest) {
        var purchase = purchaseMapper.toPurchase(purchaseRequest);
        var savedPurchase = purchaseStorageRegister.create(purchase);
        var event = new PurchaseRegisteredEvent(savedPurchase);
        eventPublisher.publishEvent(event);
        return purchaseMapper.toPurchaseResponse(savedPurchase);
    }
}
