package com.virtadity.manease.application.service.purchase_line;

import com.virtadity.manease.application.mapper.PurchaseLineMapper;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;
import com.virtadity.manease.application.port.in.purchase_line.PurchaseLineRegisterInputBoundary;
import com.virtadity.manease.application.port.out.purchase_line.PurchaseLineCreateOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseLineRegisterService implements PurchaseLineRegisterInputBoundary {
    private final PurchaseLineCreateOutputBoundary purchaseLineStorageRegister;
    private final PurchaseLineMapper purchaseLineMapper;

    @Override
    public PurchaseLineResponse execute(PurchaseLineRequest purchaseLineRequest) {
        var purchaseLine = purchaseLineMapper.toPurchaseLine(purchaseLineRequest);
        var savedPurchaseLine = purchaseLineStorageRegister.execute(purchaseLine);
        return purchaseLineMapper.toPurchaseLineResponse(savedPurchaseLine);
    }
}
