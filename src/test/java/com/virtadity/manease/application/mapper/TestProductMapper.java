package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.product.ProductRequest;
import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestProductMapper {

    private final ProductMapper productMapper = new ProductMapperImpl();

    @Test
    void testProductRequestToProduct() {
        var productId = UUID.randomUUID();
        var productName = "Golden sort";
        var productWeight =  BigDecimal.valueOf(100.0);
        var producerId = UUID.randomUUID();
        var productTypeId = UUID.randomUUID();

        var productRequest = new ProductRequest(productId, productName, productWeight, producerId, productTypeId);
        var expectedProduct = new Product(productId, productName, productWeight, producerId, productTypeId);
        var actualProduct = productMapper.toProduct(productRequest);

        assertThat(actualProduct)
                .isNotNull()
                .isEqualTo(expectedProduct);
    }

    @Test
    void testProductRequestToProductWithNullId() {
        var productName = "Golden sort";
        var productWeight =  BigDecimal.valueOf(100.0);
        var producerId = UUID.randomUUID();
        var productTypeId = UUID.randomUUID();

        var productRequest = new ProductRequest(null, productName, productWeight, producerId, productTypeId);
        var actualProduct = productMapper.toProduct(productRequest);

        assertThat(actualProduct)
                .isNotNull();

        var actualProductId = actualProduct.productId();
        var actualName = actualProduct.name();
        var actualWeight = actualProduct.weight();
        var actualProducerId = actualProduct.producerId();
        var actualProductTypeId = actualProduct.productTypeId();

        assertThat(actualProductId).isNotNull();
        assertThat(actualName).isEqualTo(productName);
        assertThat(actualWeight).isEqualTo(productWeight);
        assertThat(actualProducerId).isEqualTo(producerId);
        assertThat(actualProductTypeId).isEqualTo(productTypeId);
    }

    @Test
    void testProductToProductResponse() {
        var productId = UUID.randomUUID();
        var productName = "Golden sort";
        var productWeight =  BigDecimal.valueOf(100.0);
        var producerId = UUID.randomUUID();
        var productTypeId = UUID.randomUUID();

        var product = new Product(productId, productName, productWeight, producerId, productTypeId);
        var expectedProductResponse = new ProductResponse(
                productId,
                productName,
                productWeight,
                producerId,
                productTypeId
        );

        var actualProductResponse = productMapper.toProductResponse(product);

        assertThat(actualProductResponse)
                .isNotNull()
                .isEqualTo(expectedProductResponse);
    }
}
