package com.virtadity.manease.application.service.purchase.line;

import com.virtadity.manease.application.port.in.purchase.line.PurchaseLineDeleteInputBoundary;
import com.virtadity.manease.application.port.out.purchase.line.PurchaseLineDeleteOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseLineDeleteService implements PurchaseLineDeleteInputBoundary {

    private final PurchaseLineDeleteOutputBoundary purchaseLineStorageDelete;

    @Override
    public void execute(UUID purchaseId, UUID productId) {
        purchaseLineStorageDelete.delete(purchaseId, productId);
    }
}
