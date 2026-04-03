package com.manease.producer.application.mapper.purchase.status;

import com.manease.producer.application.entity.purchase.status.PurchaseStatusResponse;
import com.manease.producer.domain.entity.purchase.PurchaseStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseStatusMapper {
    PurchaseStatusResponse toPurchaseStatusResponse(PurchaseStatus purchaseStatus);
    List<PurchaseStatusResponse> toPurchaseStatusResponseList(List<PurchaseStatus> purchaseStatusList);
}
