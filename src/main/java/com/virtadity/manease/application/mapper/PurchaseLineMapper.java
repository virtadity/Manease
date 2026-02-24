package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.purchase_line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;
import com.virtadity.manease.domain.model.PurchaseLine;

import java.util.List;

public interface PurchaseLineMapper {
    PurchaseLine toPurchaseLine(PurchaseLineRequest purchaseLineRequest);
    List<PurchaseLine> toPurchaseLineList(List<PurchaseLineRequest> purchaseLineRequestList);

    PurchaseLineResponse toPurchaseLineResponse(PurchaseLine purchaseLine);
    List<PurchaseLineResponse> toPurchaseLineResponseList(List<PurchaseLine> purchaseLineList);
}
