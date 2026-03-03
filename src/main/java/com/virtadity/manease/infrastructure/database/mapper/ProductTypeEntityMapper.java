package com.virtadity.manease.infrastructure.database.mapper;

import com.virtadity.manease.domain.model.ProductType;
import com.virtadity.manease.infrastructure.database.entity.ProductTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductTypeEntityMapper {

    @Mapping(source = "productTypeId", target = "id")
    ProductTypeEntity toProductTypeEntity(ProductType productType);

    @Mapping(source = "id", target = "productTypeId")
    ProductType toProductType(ProductTypeEntity productTypeEntity);

    List<ProductType> toProductTypeList(List<ProductTypeEntity> productTypeEntityList);

    @Mapping(source = "productTypeId", target = "id")
    void updateFromProductType(@MappingTarget ProductTypeEntity productTypeEntity, ProductType productType);
}
