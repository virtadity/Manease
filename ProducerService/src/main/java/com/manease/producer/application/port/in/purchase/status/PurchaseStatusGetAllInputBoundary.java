package com.manease.producer.application.port.in.purchase.status;

import com.manease.producer.application.entity.purchase.status.PurchaseStatusResponse;

import java.util.List;

public interface PurchaseStatusGetAllInputBoundary {
    List<PurchaseStatusResponse> execute();
}
