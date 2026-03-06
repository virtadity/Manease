package com.virtadity.manease.infrastructure.web.assembler.producer;

import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.infrastructure.web.controller.producer.ProducerController;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProducerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
@RequiredArgsConstructor
public class ProducerAssembler implements
        RepresentationModelAssembler<ProducerResponse, EntityModel<ProducerResponseDTO>> {

    private final ProducerMapper producerMapper;

    @Override
    public EntityModel<ProducerResponseDTO> toModel(ProducerResponse producerResponse) {
        var producerResponseDTO = producerMapper.toProducerResponseDTO(producerResponse);
        var model = EntityModel.of(producerResponseDTO,
                linkTo(methodOn(ProducerController.class).all()).withRel("producers"));

        return model;
    }
}
