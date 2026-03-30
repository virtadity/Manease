package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.product.type.ProductTypeRequest;
import com.virtadity.manease.application.model.product.type.ProductTypeResponse;
import com.virtadity.manease.infrastructure.web.dto.product.type.ProductTypeRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.product.type.ProductTypeResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestProductTypeDTOMapper {
    private final ProductTypeDTOMapper productTypeDTOMapper = new ProductTypeDTOMapperImpl();

    @Test
    void testMappingToProductTypeRequest() {
        var id = UUID.randomUUID();
        var name = "Test product type name";

        var productTypeRequestDTO = new ProductTypeRequestDTO(id, name);
        var expectedProductTypeRequest = new ProductTypeRequest(id, name);
        var actualProductTypeRequest = productTypeDTOMapper.toProductTypeRequest(productTypeRequestDTO);

        assertThat(actualProductTypeRequest).isNotNull().isEqualTo(expectedProductTypeRequest);
    }

    @Test
    void testMappingToProductTypeResponseDTO() {
        var id = UUID.randomUUID();
        var name = "Test product type name";

        var productTypeResponse = new ProductTypeResponse(id, name);
        var expectedProductTypeResponseDTO = new ProductTypeResponseDTO(id, name);
        var actualProductTypeResponseDTO = productTypeDTOMapper.toProductTypeResponseDTO(productTypeResponse);

        assertThat(actualProductTypeResponseDTO).isNotNull().isEqualTo(expectedProductTypeResponseDTO);
    }
}
