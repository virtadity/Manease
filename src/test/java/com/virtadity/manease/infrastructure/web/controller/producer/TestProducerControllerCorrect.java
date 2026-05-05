package com.virtadity.manease.infrastructure.web.controller.producer;

import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.application.port.in.producer.*;
import com.virtadity.manease.infrastructure.web.assembler.ProducerAssembler;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProducerDTOMapper;
import com.virtadity.manease.infrastructure.web.controller.ProducerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProducerController.class)
public class TestProducerControllerCorrect {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProducerRegisterInputBoundary producerRegisterInputBoundary;

    @MockitoBean
    private ProducerGetAllInputBoundary producerGetAllInputBoundary;

    @MockitoBean
    private ProducerGetOneInputBoundary producerGetOneInputBoundary;

    @MockitoBean
    private ProducerCorrectInputBoundary producerCorrectInputBoundary;

    @MockitoBean
    private ProducerDeleteInputBoundary producerDeleteInputBoundary;

    @MockitoBean
    private ProducerAssembler producerAssembler;

    @MockitoBean
    private ProducerDTOMapper producerDTOMapper;

    @Test
    void testCorrect() throws Exception {
        var producerId = UUID.randomUUID();
        var producerName = "Test name";
        var producerResponse = new ProducerResponse(producerId, producerName);
        when(producerCorrectInputBoundary.execute(any(), any())).thenReturn(producerResponse);

        var producerResponseDTO = new ProducerResponseDTO(producerId, producerName);
        var selfLink = linkTo(methodOn(ProducerController.class).one(producerId)).withSelfRel();
        var allLink = linkTo(methodOn(ProducerController.class).all()).withRel("producers");
        var entityModel = EntityModel.of(producerResponseDTO, selfLink, allLink);
        when(producerAssembler.toModel(any())).thenReturn(entityModel);

        var request = String.format("{\"id\": \"%s\", \"name\": \"%s\"}", producerId, producerName);
        var path = String.format("/producers/%s", producerId);
        mockMvc.perform(put(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(producerId.toString()))
                .andExpect(jsonPath("$.name").value(producerName))
                .andExpect(jsonPath("$._links.self.href").value(selfLink.getHref()))
                .andExpect(jsonPath("$._links.producers.href").value(allLink.getHref()));
    }

}
