package com.virtadity.manease.application.port.in.purchase.line;

import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;

import java.util.List;

public interface PurchaseLineGetAllInputBoundary {
    List<PurchaseLineResponse> execute();
}
