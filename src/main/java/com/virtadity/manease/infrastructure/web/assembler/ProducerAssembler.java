package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.infrastructure.web.rest_controller.ProducerController;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProducerDTOMapper;
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

    private final ProducerDTOMapper producerDTOMapper;

    @Override
    public EntityModel<ProducerResponseDTO> toModel(ProducerResponse producerResponse) {
        var producerResponseDTO = producerDTOMapper.toProducerResponseDTO(producerResponse);

        return EntityModel.of(producerResponseDTO,
                linkTo(methodOn(ProducerController.class).all()).withRel("producers"),
                linkTo(methodOn(ProducerController.class).one(producerResponse.producerId())).withSelfRel());
    }
}
