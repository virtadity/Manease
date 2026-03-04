package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.purchase.PurchaseRequest;
import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.domain.model.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseMapper {
    @Mapping(
            source = "purchaseId",
            target = "purchaseId",
            defaultExpression = "java(java(UUID.randomUUID())"
    )
    Purchase toPurchase(PurchaseRequest purchaseRequest);

    PurchaseResponse toPurchaseResponse(Purchase purchase);
    List<PurchaseResponse> toPurchaseResponseList(List<Purchase> purchaseList);
}
