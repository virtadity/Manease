package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.purchase.PurchaseRequest;
import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.infrastructure.web.dto.purchase.PurchaseRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.purchase.PurchaseResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseDTOMapper {
    @Mapping(source = "id", target = "purchaseId")
    PurchaseRequest toPurchaseRequest(PurchaseRequestDTO purchaseRequestDTO);
    @Mapping(source = "purchaseId", target = "id")
    PurchaseResponseDTO toPurchaseResponseDTO(PurchaseResponse purchaseResponse);
}
