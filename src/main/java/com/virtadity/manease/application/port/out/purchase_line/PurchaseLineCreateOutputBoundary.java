package com.virtadity.manease.application.port.out.purchase_line;

import com.virtadity.manease.domain.model.PurchaseLine;

public interface PurchaseLineCreateOutputBoundary {
    PurchaseLine create(PurchaseLine purchaseLine);
}
