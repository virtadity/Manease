package com.manease.producer.application.port.in.purchase.status.getters;

import com.manease.producer.application.entity.response.PurchaseStatusResponse;

import java.util.UUID;

public interface PurchaseStatusGetOneInputBoundary {
    PurchaseStatusResponse execute(UUID statusId);
}
