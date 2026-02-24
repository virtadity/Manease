package com.virtadity.manease.application.port.in.purchase_line;

import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;

import java.util.List;

public interface PurchaseLineGetAllInputBoundary {
    List<PurchaseLineResponse> execute();
}
