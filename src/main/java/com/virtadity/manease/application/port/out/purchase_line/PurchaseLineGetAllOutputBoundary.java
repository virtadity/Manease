package com.virtadity.manease.application.port.out.purchase_line;

import com.virtadity.manease.domain.model.PurchaseLine;

import java.util.List;

public interface PurchaseLineGetAllOutputBoundary {
    List<PurchaseLine> execute();
}
