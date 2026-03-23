package com.virtadity.manease.infrastructure.web.rest_controller.producer;

import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.application.port.in.producer.*;
import com.virtadity.manease.infrastructure.web.assembler.ProducerAssembler;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProducerDTOMapper;
import com.virtadity.manease.infrastructure.web.rest_controller.ProducerController;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProducerController.class)
public class TestProducerControllerGetOne {

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
    void testGetOne() throws Exception {
        var producerId = UUID.randomUUID();
        var producerName = "Test name";
        var producerResponse = new ProducerResponse(producerId, producerName);
        when(producerGetOneInputBoundary.execute(producerId)).thenReturn(producerResponse);

        var selfLink = linkTo(methodOn(ProducerController.class).one(producerId)).withSelfRel();
        var allLink = linkTo(methodOn(ProducerController.class).all()).withRel("producers");
        var producerResponseDTO = new ProducerResponseDTO(producerId, producerName);
        var entityModel = EntityModel.of(producerResponseDTO, selfLink, allLink);
        when(producerAssembler.toModel(any())).thenReturn(entityModel);

        var path = String.format("/producers/%s", producerId);
        mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(producerId.toString()))
                .andExpect(jsonPath("$.name").value(producerName))
                .andExpect(jsonPath("$._links.self.href").value(selfLink.getHref()))
                .andExpect(jsonPath("$._links.producers.href").value(allLink.getHref()));
    }
}
