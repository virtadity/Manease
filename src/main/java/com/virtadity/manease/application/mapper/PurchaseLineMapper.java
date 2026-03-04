package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.purchase_line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;
import com.virtadity.manease.domain.model.PurchaseLine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseLineMapper {
    PurchaseLine toPurchaseLine(PurchaseLineRequest purchaseLineRequest);

    PurchaseLineResponse toPurchaseLineResponse(PurchaseLine purchaseLine);
    List<PurchaseLineResponse> toPurchaseLineResponseList(List<PurchaseLine> purchaseLineList);
}
