package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.infrastructure.web.dto.purchase.PurchaseResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseMapper;
import com.virtadity.manease.infrastructure.web.rest_controller.ProducerController;
import com.virtadity.manease.infrastructure.web.rest_controller.PurchaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class PurchaseAssembler implements
        RepresentationModelAssembler<PurchaseResponse, EntityModel<PurchaseResponseDTO>> {

    private final PurchaseMapper purchaseMapper;

    @Override
    public EntityModel<PurchaseResponseDTO> toModel(PurchaseResponse purchaseResponse) {
        var purchaseResponseDTO = purchaseMapper.toPurchaseResponseDTO(purchaseResponse);
        return EntityModel.of(
                purchaseResponseDTO,
                linkTo(methodOn(PurchaseController.class).one(purchaseResponse.purchaseId())).withSelfRel(),
                linkTo(methodOn(PurchaseController.class).all()).withRel("purchases"),
                linkTo(methodOn(ProducerController.class).one(purchaseResponse.producerId())).withRel("producer"));
    }
}
