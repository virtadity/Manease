package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.product_type.ProductTypeRequest;
import com.virtadity.manease.application.model.product_type.ProductTypeResponse;
import com.virtadity.manease.domain.model.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductTypeMapper {
    @Mapping(
            source = "productTypeId",
            target = "productTypeId",
            defaultExpression = "java(UUID.randomUUID())"
    )
    ProductType toProductType(ProductTypeRequest producerTypeRequest);

    ProductTypeResponse toProductTypeResponse(ProductType productType);
    List<ProductTypeResponse> toProductTypeResponseList(List<ProductType> productTypeList);
}
