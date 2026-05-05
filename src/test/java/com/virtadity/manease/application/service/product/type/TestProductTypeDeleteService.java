package com.virtadity.manease.application.service.product.type;

import com.virtadity.manease.application.port.out.product.type.ProductTypeDeleteOutputBoundary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestProductTypeDeleteService {
    @Mock
    private ProductTypeDeleteOutputBoundary productTypeDeleteStorage;

    @InjectMocks
    private ProductTypeDeleteService productTypeDeleteService;

    @Test
    void testProductTypeDelete() {
        var productTypeId = UUID.randomUUID();
        productTypeDeleteService.execute(productTypeId);
        verify(productTypeDeleteStorage, Mockito.times(1)).delete(productTypeId);
    }
}
