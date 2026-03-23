package com.virtadity.manease.infrastructure.database.mapper;

import com.virtadity.manease.domain.model.Product;
import com.virtadity.manease.infrastructure.database.entity.ProducerEntity;
import com.virtadity.manease.infrastructure.database.entity.ProductEntity;
import com.virtadity.manease.infrastructure.database.entity.ProductTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductEntityMapper {

    @Mapping(source = "id", target = "productId")
    @Mapping(target = "producerId", expression = "java(productEntity.getProducer().getId())")
    @Mapping(target = "productTypeId", expression = "java(productEntity.getProductType().getId())")
    Product toProduct(ProductEntity productEntity);

    @Mapping(target = "id", expression = "java(product.productId())")
    @Mapping(source = "productTypeEntity", target = "productType")
    @Mapping(source = "producerEntity", target = "producer")
    @Mapping(target = "name", expression = "java(product.name())")
    ProductEntity toProductEntity(Product product, ProductTypeEntity productTypeEntity, ProducerEntity producerEntity);

    List<Product> toProductList(List<ProductEntity> productEntityList);

    @Mapping(target = "id", expression = "java(product.productId())")
    @Mapping(target = "name", expression = "java(product.name())")
    @Mapping(source = "productTypeEntity", target = "productType")
    @Mapping(source = "producerEntity", target = "producer")
    void updateFromProduct(
            @MappingTarget ProductEntity productEntity,
            Product product,
            ProductTypeEntity productTypeEntity,
            ProducerEntity producerEntity
    );
}
