package com.virtadity.manease.application.service.product_type;

import com.virtadity.manease.application.mapper.ProductTypeMapper;
import com.virtadity.manease.application.model.product_type.ProductTypeResponse;
import com.virtadity.manease.application.port.out.product_type.ProductTypeGetOneOutputBoundary;
import com.virtadity.manease.application.service.product_type.exception.ProductTypeNotFoundException;
import com.virtadity.manease.domain.model.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestProductTypeGetOneService {

    @Mock
    private ProductTypeGetOneOutputBoundary productTypeGetOneStorage;

    @Mock
    private ProductTypeMapper productTypeMapper;

    @InjectMocks
    private ProductTypeGetOneService productTypeGetOneService;

    @Test
    void testProductTypeGetOne() {
        var productTypeId = UUID.randomUUID();
        var productTypeName = "Магнит";
        var productType = new ProductType(productTypeId, productTypeName);
        var productTypeResponse = new ProductTypeResponse(productTypeId, productTypeName);

        var otherProductType = UUID.randomUUID();
        when(productTypeMapper.toProductTypeResponse(productType)).thenReturn(productTypeResponse);
        when(productTypeGetOneStorage.getOne(productTypeId)).thenReturn(Optional.of(productType));
        when(productTypeGetOneStorage.getOne(otherProductType)).thenReturn(Optional.empty());

        var actualProductTypeResponse = productTypeGetOneService.execute(productTypeId);
        assertThat(actualProductTypeResponse).isNotNull().isEqualTo(productTypeResponse);


        Assertions.assertThrows(ProductTypeNotFoundException.class,
                () -> productTypeGetOneService.execute(otherProductType));
    }
}
