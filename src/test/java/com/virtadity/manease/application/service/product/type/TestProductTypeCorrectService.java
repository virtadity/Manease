package com.virtadity.manease.application.service.product.type;

import com.virtadity.manease.application.mapper.ProductTypeMapper;
import com.virtadity.manease.application.model.product.type.ProductTypeRequest;
import com.virtadity.manease.application.model.product.type.ProductTypeResponse;
import com.virtadity.manease.application.port.out.product.type.ProductTypeCorrectOutputBoundary;
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
public class TestProductTypeCorrectService {

    @Mock
    private ProductTypeMapper productTypeMapper;

    @Mock
    private ProductTypeCorrectOutputBoundary productTypeCorrectStorage;

    @InjectMocks
    private ProductTypeCorrectService productTypeCorrectService;

    @Test
    void testProductTypeCorrect() {
        var productTypeId = UUID.randomUUID();
        var productTypeUpdatedName = "Вишня";

        var productTypeRequest = new ProductTypeRequest(productTypeId, productTypeUpdatedName);
        var productType = new ProductType(productTypeId, productTypeUpdatedName);
        var expectedProductTypeResponse = new ProductTypeResponse(productTypeId, productTypeUpdatedName);

        when(productTypeMapper.toProductType(productTypeRequest)).thenReturn(productType);
        when(productTypeMapper.toProductTypeResponse(productType)).thenReturn(expectedProductTypeResponse);
        when(productTypeCorrectStorage.correct(productTypeId, productType)).thenReturn(productType);

        var actualProductTypeResponse = productTypeCorrectService.execute(productTypeId, productTypeRequest);
        assertThat(actualProductTypeResponse)
                .isNotNull()
                .isEqualTo(expectedProductTypeResponse);
    }
}
