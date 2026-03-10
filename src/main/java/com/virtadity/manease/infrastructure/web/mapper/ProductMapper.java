package com.virtadity.manease.infrastructure.web.mapper;


import com.virtadity.manease.application.model.product.ProductRequest;
import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.infrastructure.web.dto.product.ProductRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.product.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(source = "id", target = "productId")
    @Mapping(source = "name", target = "productName")
    ProductRequest toProductRequest(ProductRequestDTO productRequestDTO);

    @Mapping(source = "productId", target = "id")
    @Mapping(source = "productName", target = "name")
    ProductResponseDTO toProductResponseDTO(ProductResponse productResponse);
}
