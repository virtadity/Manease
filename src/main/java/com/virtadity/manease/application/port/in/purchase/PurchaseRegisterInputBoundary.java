package com.virtadity.manease.application.port.in.purchase;

import com.virtadity.manease.application.model.purchase.PurchaseRequest;
import com.virtadity.manease.application.model.purchase.PurchaseResponse;

public interface PurchaseRegisterInputBoundary {
    PurchaseResponse execute(PurchaseRequest purchaseRequest);
}
