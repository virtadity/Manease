package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.purchase_line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;
import com.virtadity.manease.infrastructure.web.dto.purchase_line.PurchaseLineRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.purchase_line.PurchaseLineResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseLineMapper {
    PurchaseLineRequest toPurchaseLineRequest(PurchaseLineRequestDTO purchaseLineRequestDTO);
    PurchaseLineResponseDTO toPurchaseLineResponseDTO(PurchaseLineResponse purchaseLineResponse);
}
