package com.virtadity.manease.infrastructure.web.controller.producer;

import com.virtadity.manease.application.port.in.producer.*;
import com.virtadity.manease.infrastructure.web.assembler.ProducerAssembler;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProducerDTOMapper;
import com.virtadity.manease.infrastructure.web.controller.ProducerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProducerController.class)
public class TestProducerControllerGetAll {

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

    private List<ProducerResponseDTO> producerResponseDTOList;

    private CollectionModel<EntityModel<ProducerResponseDTO>> producerCollectionModel;

    @BeforeEach
    void setUp() {
        setUpProducerResponseDTOList();
        setUpProducerCollectionModel();
    }

    private void setUpProducerResponseDTOList() {
        producerResponseDTOList = List.of(
                new ProducerResponseDTO(UUID.randomUUID(), "Apple"),
                new ProducerResponseDTO(UUID.randomUUID(), "Microsoft"),
                new ProducerResponseDTO(UUID.randomUUID(), "Google"),
                new ProducerResponseDTO(UUID.randomUUID(), "Yahoo"),
                new ProducerResponseDTO(UUID.randomUUID(), "Amazon")
        );
    }

    private void setUpProducerCollectionModel() {
        var produceEntityModelList = this.producerResponseDTOList.stream().map(
                producerResponseDTO -> {
                    var producerId = producerResponseDTO.getId();
                    var selfLink = linkTo(methodOn(ProducerController.class).one(producerId)).withSelfRel();
                    var allLink = linkTo(methodOn(ProducerController.class).all()).withRel("producers");
                    return EntityModel.of(producerResponseDTO, selfLink, allLink);
                }).toList();

        producerCollectionModel = CollectionModel.of(produceEntityModelList);
    }

    @Test
    void testGetAll() throws Exception {
        when(producerAssembler.toCollectionModel(any())).thenReturn(producerCollectionModel);
        mockMvc.perform(get("/producers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
}
