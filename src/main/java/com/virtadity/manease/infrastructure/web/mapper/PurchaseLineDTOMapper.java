package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.purchase.line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;
import com.virtadity.manease.infrastructure.web.dto.purchase.line.PurchaseLineRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.purchase.line.PurchaseLineResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseLineDTOMapper {
    PurchaseLineRequest toPurchaseLineRequest(PurchaseLineRequestDTO purchaseLineRequestDTO);
    PurchaseLineResponseDTO toPurchaseLineResponseDTO(PurchaseLineResponse purchaseLineResponse);
}
