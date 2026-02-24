package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.product_type.ProductTypeRequest;
import com.virtadity.manease.application.model.product_type.ProductTypeResponse;
import com.virtadity.manease.domain.model.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductTypeMapper {
    ProductType toProductType(ProductTypeRequest producerTypeRequest);
    List<ProductType> toProductTypeList(List<ProductTypeRequest> productTypeRequestList);

    ProductTypeResponse toProductTypeResponse(ProductType productType);
    List<ProductTypeResponse> toProductTypeResponseList(List<ProductType> productTypeList);
}
