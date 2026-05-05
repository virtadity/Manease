package com.virtadity.manease.application.port.out.purchase.line;

import com.virtadity.manease.domain.model.PurchaseLine;

import java.util.List;

public interface PurchaseLineGetAllOutputBoundary {
    List<PurchaseLine> getAll();
}
