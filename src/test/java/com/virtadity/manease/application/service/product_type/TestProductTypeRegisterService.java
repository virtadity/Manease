package com.virtadity.manease.application.service.product_type;

import com.virtadity.manease.application.mapper.ProductTypeMapper;
import com.virtadity.manease.application.model.product_type.ProductTypeRequest;
import com.virtadity.manease.application.model.product_type.ProductTypeResponse;
import com.virtadity.manease.application.port.out.product_type.ProductTypeCreateOutputBoundary;
import com.virtadity.manease.domain.model.ProductType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestProductTypeRegisterService {

    @Mock
    private ProductTypeMapper productTypeMapper;

    @Mock
    private ProductTypeCreateOutputBoundary productTypeCreateStorage;

    @InjectMocks
    private ProductTypeRegisterService productTypeRegisterService;

    @Test
    void testProductTypeRegister() {
        var productTypeId = UUID.randomUUID();
        var productTypeName = "Яблоки";

        var productTypeRequest = new ProductTypeRequest(productTypeId, productTypeName);
        var productType = new ProductType(productTypeId, productTypeName);
        var expectedProductTypeResponse = new ProductTypeResponse(productTypeId, productTypeName);

        when(productTypeMapper.toProductType(productTypeRequest)).thenReturn(productType);
        when(productTypeMapper.toProductTypeResponse(productType)).thenReturn(expectedProductTypeResponse);
        when(productTypeCreateStorage.create(productType)).thenReturn(productType);

        var actualProductTypeResponse = productTypeRegisterService.execute(productTypeRequest);
        assertThat(actualProductTypeResponse)
                .isNotNull()
                .isEqualTo(expectedProductTypeResponse);
    }

    @Test
    void testProductTypeRegisterWithNullId() {
        var productTypeId = UUID.randomUUID();
        var productTypeName = "Яблоки";

        var productTypeRequest = new ProductTypeRequest(null, productTypeName);
        var productType = new ProductType(productTypeId, productTypeName);
        var expectedProductTypeResponse = new ProductTypeResponse(productTypeId, productTypeName);

        when(productTypeMapper.toProductType(productTypeRequest)).thenReturn(productType);
        when(productTypeMapper.toProductTypeResponse(productType)).thenReturn(expectedProductTypeResponse);
        when(productTypeCreateStorage.create(productType)).thenReturn(productType);

        var actualProductTypeResponse = productTypeRegisterService.execute(productTypeRequest);
        assertThat(actualProductTypeResponse)
                .isNotNull()
                .isEqualTo(expectedProductTypeResponse);
    }
}
