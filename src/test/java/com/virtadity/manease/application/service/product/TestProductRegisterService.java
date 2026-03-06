package com.virtadity.manease.application.service.product;

import com.virtadity.manease.application.mapper.ProductMapper;
import com.virtadity.manease.application.model.product.ProductRequest;
import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.application.port.out.product.ProductCreateOutputBoundary;
import com.virtadity.manease.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestProductRegisterService {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductCreateOutputBoundary productCreateStorage;

    @InjectMocks
    private ProductRegisterService productRegisterService;

    @Test
    void testProductRegister() {
        var productId = UUID.randomUUID();
        var productName = "Red sort apples";
        var productWeight = BigDecimal.valueOf(100.0);
        var producerId = UUID.randomUUID();
        var productTypeId = UUID.randomUUID();

        var productRequest = new ProductRequest(productId, productName, productWeight, producerId, productTypeId);
        var product = new Product(productId, productName, productWeight, producerId, productTypeId);
        var productResponse = new ProductResponse(productId, productName, productWeight, producerId, productTypeId);

        when(productMapper.toProduct(productRequest)).thenReturn(product);
        when(productMapper.toProductResponse(product)).thenReturn(productResponse);
        when(productCreateStorage.create(product)).thenReturn(product);

        var actualProductResponse = productRegisterService.execute(productRequest);
        assertThat(actualProductResponse)
                .isNotNull()
                .isEqualTo(productResponse);
    }
}
