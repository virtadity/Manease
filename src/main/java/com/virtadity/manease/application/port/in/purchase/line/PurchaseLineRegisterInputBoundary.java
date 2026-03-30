package com.virtadity.manease.application.port.in.purchase.line;

import com.virtadity.manease.application.model.purchase.line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;

public interface PurchaseLineRegisterInputBoundary {
    PurchaseLineResponse execute(PurchaseLineRequest purchaseLineRequest);
}
