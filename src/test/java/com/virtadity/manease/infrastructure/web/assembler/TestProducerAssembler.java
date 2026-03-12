package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.infrastructure.web.mapper.ProducerDTOMapper;
import com.virtadity.manease.infrastructure.web.mapper.ProducerDTOMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestProducerAssembler {

    private ProducerAssembler producerAssembler;
    private final ProducerDTOMapper producerDTOMapper = new ProducerDTOMapperImpl();

    @BeforeEach
    public void setUp() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        producerAssembler = new ProducerAssembler(producerDTOMapper);
    }

    @Test
    public void testProducerAssemblingToModel() {
        var producerId = UUID.randomUUID();
        var producerName = "Apple";
        var producerResponse = new ProducerResponse(producerId, producerName);
        var producerModel = producerAssembler.toModel(producerResponse);

        assertThat(producerModel.getContent().getId()).isNotNull().isEqualTo(producerId);
        assertThat(producerModel.getContent().getName()).isNotNull().isEqualTo(producerName);

        assertThat(producerModel.getLink("self")).isPresent();
        assertThat(producerModel.getLink("producers")).isPresent();

        var path = String.format("/producers/%s", producerId);
        assertThat(producerModel.getRequiredLink("self").getHref()).contains(path);
    }

    @AfterEach
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }
}
