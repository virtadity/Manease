package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.product.ProductRequest;
import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.infrastructure.web.dto.product.ProductRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.product.ProductResponseDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestProductDTOMapper {

    private final ProductDTOMapper productDTOMapper = new ProductDTOMapperImpl();

    @Test
    public void testMappingToProductRequest() {
        var id = UUID.randomUUID();
        var name = "Test product name";
        var weight = BigDecimal.valueOf(100.0);
        var producerId = UUID.randomUUID();
        var producerTypeId = UUID.randomUUID();

        var productRequestDTO = new ProductRequestDTO(id, name, weight, producerId, producerTypeId);
        var expectedProductRequest = new ProductRequest(id, name, weight, producerId, producerTypeId);
        var actualProductRequest = productDTOMapper.toProductRequest(productRequestDTO);

        assertThat(actualProductRequest).isNotNull().isEqualTo(expectedProductRequest);
    }

    @Test
    public void testMappingToProductResponseDTO() {
        var id = UUID.randomUUID();
        var name = "Test product name";
        var weight = BigDecimal.valueOf(100.0);
        var producerId = UUID.randomUUID();
        var producerTypeId = UUID.randomUUID();

        var productResponse = new ProductResponse(id, name, weight, producerId, producerTypeId);
        var expectedProductResponseDTO = new ProductResponseDTO(id, name, weight, producerId, producerTypeId);
        var actualProductResponseDTO = productDTOMapper.toProductResponseDTO(productResponse);

        assertThat(actualProductResponseDTO).isNotNull().isEqualTo(expectedProductResponseDTO);
    }
}
