package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.product.type.ProductTypeRequest;
import com.virtadity.manease.application.model.product.type.ProductTypeResponse;
import com.virtadity.manease.infrastructure.web.dto.product.type.ProductTypeRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.product.type.ProductTypeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductTypeDTOMapper {
    @Mapping(source = "id", target = "productTypeId")
    ProductTypeRequest toProductTypeRequest(ProductTypeRequestDTO productTypeRequestDTO);

    @Mapping(source = "productTypeId", target = "id")
    ProductTypeResponseDTO toProductTypeResponseDTO(ProductTypeResponse productTypeResponse);
}
