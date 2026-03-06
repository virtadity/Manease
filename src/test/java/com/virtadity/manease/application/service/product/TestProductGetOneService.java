package com.virtadity.manease.application.service.product;

import com.virtadity.manease.application.mapper.ProductMapper;
import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.application.port.out.product.ProductGetOneOutputBoundary;
import com.virtadity.manease.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestProductGetOneService {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductGetOneOutputBoundary productGetOneStorage;

    @InjectMocks
    private ProductGetOneService productGetOneService;

    @Test
    void testProductGetOne() {
        var productId = UUID.randomUUID();
        var productName = "Red sort apples";
        var productWeight = BigDecimal.valueOf(100.0);
        var producerId = UUID.randomUUID();
        var productTypeId = UUID.randomUUID();
        var otherProductTypeId = UUID.randomUUID();

        var product = new Product(productId, productName, productWeight, producerId, productTypeId);
        var productResponse = new ProductResponse(productId, productName, productWeight, producerId, productTypeId);

        when(productMapper.toProductResponse(product)).thenReturn(productResponse);
        when(productGetOneStorage.getOne(productId)).thenReturn(Optional.of(product));
        when(productGetOneStorage.getOne(otherProductTypeId)).thenReturn(Optional.empty());

        var actualProductResponse = productGetOneService.execute(productId);
        assertThat(actualProductResponse).isNotNull().isNotEmpty().isEqualTo(Optional.of(productResponse));

        var emptyProductResponse = productGetOneService.execute(otherProductTypeId);
        assertThat(emptyProductResponse).isNotNull().isEmpty();
    }
}
