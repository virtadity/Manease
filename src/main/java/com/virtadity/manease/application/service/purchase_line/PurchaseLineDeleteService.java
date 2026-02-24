package com.virtadity.manease.application.service.purchase_line;

import com.virtadity.manease.application.port.in.purchase_line.PurchaseLineDeleteInputBoundary;
import com.virtadity.manease.application.port.out.purchase_line.PurchaseLineDeleteOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseLineDeleteService implements PurchaseLineDeleteInputBoundary {

    private final PurchaseLineDeleteOutputBoundary purchaseLineStorageDelete;

    @Override
    public void execute(UUID purchaseId, UUID productId) {
        purchaseLineStorageDelete.execute(purchaseId, productId);
    }
}
