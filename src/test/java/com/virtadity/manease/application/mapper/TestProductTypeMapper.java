package com.virtadity.manease.application.mapper;


import com.virtadity.manease.application.model.product.type.ProductTypeRequest;
import com.virtadity.manease.application.model.product.type.ProductTypeResponse;
import com.virtadity.manease.domain.model.ProductType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestProductTypeMapper {
    private final ProductTypeMapper productTypeMapper = new ProductTypeMapperImpl();

    @Test
    void testProductTypeRequestToProductType() {
        var productTypeId = UUID.randomUUID();
        var productTypeName = "Яблоки";
        var productTypeRequest = new ProductTypeRequest(productTypeId, productTypeName);
        var expectedProductType = new ProductType(productTypeId, productTypeName);
        var actualProductType = productTypeMapper.toProductType(productTypeRequest);
        assertThat(actualProductType)
                .isNotNull()
                .isEqualTo(expectedProductType);
    }

    @Test
    void testProductTypeRequestToProductTypeWithNullId() {
        var productTypeName = "Яблоки";
        var productTypeRequest = new ProductTypeRequest(null, productTypeName);
        var actualProductType = productTypeMapper.toProductType(productTypeRequest);

        assertThat(actualProductType).isNotNull();
        assertThat(actualProductType.productTypeId()).isNotNull();
        assertThat(actualProductType.name()).isNotNull().isEqualTo(productTypeName);
    }

    @Test
    void testProductTypeToResponse() {
        var productTypeId = UUID.randomUUID();
        var productTypeName = "Яблоки";
        var productType = new ProductType(productTypeId, productTypeName);
        var expectedProductTypeResponse = new ProductTypeResponse(productTypeId, productTypeName);
        var actualProductTypeResponse = productTypeMapper.toProductTypeResponse(productType);

        assertThat(actualProductTypeResponse).isNotNull().isEqualTo(expectedProductTypeResponse);
    }
}
