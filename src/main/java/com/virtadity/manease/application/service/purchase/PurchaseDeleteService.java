package com.virtadity.manease.application.service.purchase;

import com.virtadity.manease.application.port.in.purchase.PurchaseDeleteInputBoundary;
import com.virtadity.manease.application.port.out.purchase.PurchaseDeleteOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseDeleteService implements PurchaseDeleteInputBoundary {

    private final PurchaseDeleteOutputBoundary purchaseStorageDelete;

    @Override
    public void execute(UUID purchaseId) {
        purchaseStorageDelete.delete(purchaseId);
    }
}
