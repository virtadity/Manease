package com.manease.producer.application.port.out.purchase.status.getters;

import com.manease.producer.domain.entity.PurchaseStatus;

import java.util.List;

public interface PurchaseStatusGetAllOutputBoundary {
    List<PurchaseStatus> getAll();
}
