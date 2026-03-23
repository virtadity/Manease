package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.infrastructure.web.mapper.ProductDTOMapper;
import com.virtadity.manease.infrastructure.web.mapper.ProductDTOMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestProductAssembler {

    private ProductAssembler productAssembler;
    private final ProductDTOMapper productDTOMapper = new ProductDTOMapperImpl();

    @BeforeEach
    public void setUp() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        productAssembler = new ProductAssembler(productDTOMapper);
    }

    @Test
    public void testProductAssemblingToModel() {
        var productId = UUID.randomUUID();
        var name = "Test product";
        var weight = BigDecimal.valueOf(100.0);
        var producerId = UUID.randomUUID();
        var productTypeId = UUID.randomUUID();

        var productResponse = new ProductResponse(productId, name, weight, producerId, productTypeId);
        var productModel = productAssembler.toModel(productResponse);

        assertThat(productModel.getContent().getId()).isNotNull().isEqualTo(productId);
        assertThat(productModel.getContent().getName()).isNotNull().isEqualTo(name);
        assertThat(productModel.getContent().getWeight()).isNotNull().isEqualTo(weight);
        assertThat(productModel.getContent().getProducerId()).isNotNull().isEqualTo(producerId);
        assertThat(productModel.getContent().getProductTypeId()).isNotNull().isEqualTo(productTypeId);

        assertThat(productModel.getLink("self")).isPresent();
        assertThat(productModel.getLink("products")).isPresent();

        var path = String.format("/products/%s", productId);
        assertThat(productModel.getRequiredLink("self").getHref().contains(path));

        var pathToProducer = String.format("/producers/%s", producerId);
        assertThat(productModel.getRequiredLink("producer").getHref())
                .isNotNull()
                .contains(pathToProducer);

        var pathToProductType = String.format("/product_types/%s", productTypeId);
        assertThat(productModel.getRequiredLink("product_type").getHref())
                .isNotNull()
                .contains(pathToProductType);
    }

    @AfterEach
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }
}
