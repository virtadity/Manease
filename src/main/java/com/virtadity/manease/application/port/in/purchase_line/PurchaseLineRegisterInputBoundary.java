package com.virtadity.manease.application.port.in.purchase_line;

import com.virtadity.manease.application.model.purchase_line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;

public interface PurchaseLineRegisterInputBoundary {
    PurchaseLineResponse execute(PurchaseLineRequest purchaseLineRequest);
}
