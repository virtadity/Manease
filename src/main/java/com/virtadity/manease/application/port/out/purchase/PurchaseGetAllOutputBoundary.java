package com.virtadity.manease.application.port.out.purchase;

import com.virtadity.manease.domain.model.Purchase;

import java.util.List;

public interface PurchaseGetAllOutputBoundary {
    List<Purchase> execute();
}
