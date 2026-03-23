package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.producer.ProducerRequest;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class TestProducerDTOMapper {

    private final ProducerDTOMapper producerDTOMapper = new ProducerDTOMapperImpl();

    @Test
    public void testMappingToProducerRequest() {
        var id = UUID.randomUUID();
        var name = "Test producer name";
        var producerRequestDTO = new ProducerRequestDTO(id, name);
        var expectedProducerRequest = new ProducerRequest(id, name);
        var actualProducerRequest = producerDTOMapper.toProducerRequest(producerRequestDTO);
        assertThat(actualProducerRequest).isNotNull().isEqualTo(expectedProducerRequest);
    }

    @Test
    public void testMappingToProducerResponseDTO() {
        var id = UUID.randomUUID();
        var name = "Test producer name";
        var producerResponse = new ProducerResponse(id, name);
        var expectedProducerResponseDTO = new ProducerResponseDTO(id, name);
        var actualProducerResponseDTO = producerDTOMapper.toProducerResponseDTO(producerResponse);
        assertThat(actualProducerResponseDTO).isNotNull().isEqualTo(expectedProducerResponseDTO);
    }
}
