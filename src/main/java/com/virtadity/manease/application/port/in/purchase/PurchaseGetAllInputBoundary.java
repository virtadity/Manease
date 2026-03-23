package com.virtadity.manease.application.port.in.purchase;

import com.virtadity.manease.application.model.purchase.PurchaseResponse;

import java.util.List;

public interface PurchaseGetAllInputBoundary {
    List<PurchaseResponse> execute();
}
