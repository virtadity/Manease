package com.virtadity.manease.application.service.product;

import com.virtadity.manease.application.mapper.ProductMapper;
import com.virtadity.manease.application.model.product.ProductRequest;
import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.application.port.out.product.ProductCorrectOutputBoundary;
import com.virtadity.manease.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestProductCorrectService {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductCorrectOutputBoundary productCorrectStorage;

    @InjectMocks
    private ProductCorrectService productCorrectService;

    @Test
    void testProductCorrect() {
        var productId = UUID.randomUUID();
        var productName = "Red sort apples";
        var productWeight = BigDecimal.valueOf(100.0);
        var producerId = UUID.randomUUID();
        var productTypeId = UUID.randomUUID();

        var productRequest = new ProductRequest(productId, productName, productWeight, producerId, productTypeId);
        var product = new Product(productId, productName, productWeight, producerId, productTypeId);
        var productResponse = new ProductResponse(productId, productName, productWeight, producerId, productTypeId);

        when(productMapper.toProductResponse(product)).thenReturn(productResponse);
        when(productMapper.toProduct(productRequest)).thenReturn(product);
        when(productCorrectStorage.correct(productId, product)).thenReturn(product);

        var actualProductResponse = productCorrectService.execute(productId, productRequest);
        assertThat(actualProductResponse)
                .isNotNull()
                .isEqualTo(productResponse);
    }
}
