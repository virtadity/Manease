package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.product_type.ProductTypeResponse;
import com.virtadity.manease.infrastructure.web.mapper.ProductTypeDTOMapper;
import com.virtadity.manease.infrastructure.web.mapper.ProductTypeDTOMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestProductTypeAssembler {

    private ProductTypeAssembler productTypeAssembler;
    private final ProductTypeDTOMapper productTypeDTOMapper = new ProductTypeDTOMapperImpl();

    @BeforeEach
    public void setUp() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        productTypeAssembler = new ProductTypeAssembler(productTypeDTOMapper);
    }

    @Test
    public void testProductTypeAssemblingToModel() {
        var productTypeId = UUID.randomUUID();
        var productTypeName = "Test product-type name";
        var productTypeResponse = new ProductTypeResponse(productTypeId, productTypeName);
        var productTypeModel = productTypeAssembler.toModel(productTypeResponse);

        assertThat(productTypeModel.getContent().getId()).isNotNull().isEqualTo(productTypeId);
        assertThat(productTypeModel.getContent().getName()).isNotNull().isEqualTo(productTypeName);

        assertThat(productTypeModel.getLink("self").isPresent());
        assertThat(productTypeModel.getLink("product_types").isPresent());

        var path = String.format("/product_types/%s", productTypeId);
        assertThat(productTypeModel.getRequiredLink("self").getHref()).contains(path);
    }

    @AfterEach
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

}
