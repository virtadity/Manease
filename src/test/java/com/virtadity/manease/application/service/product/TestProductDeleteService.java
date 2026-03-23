package com.virtadity.manease.application.service.product;

import com.virtadity.manease.application.port.out.product.ProductDeleteOutputBoundary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestProductDeleteService {
    @Mock
    private ProductDeleteOutputBoundary productDeleteStorage;

    @InjectMocks
    private ProductDeleteService productDeleteService;

    @Test
    void testDeleteProduct() {
        var productId = UUID.randomUUID();
        productDeleteService.execute(productId);
        verify(productDeleteStorage, Mockito.times(1)).delete(productId);
    }
}
