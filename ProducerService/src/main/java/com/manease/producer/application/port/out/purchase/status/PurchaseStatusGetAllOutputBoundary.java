package com.manease.producer.application.port.out.purchase.status;

import com.manease.producer.domain.entity.purchase.PurchaseStatus;

import java.util.List;

public interface PurchaseStatusGetAllOutputBoundary {
    List<PurchaseStatus> getAll();
}
