package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.purchase.PurchaseRequest;
import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.domain.model.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseMapper {
    Purchase toPurchase(PurchaseRequest purchaseRequest);
    List<Purchase> toPurchaseList(List<PurchaseRequest> purchaseRequestList);

    PurchaseResponse toPurchaseResponse(Purchase purchase);
    List<PurchaseResponse> toPurchaseResponseList(List<Purchase> purchaseList);
}
