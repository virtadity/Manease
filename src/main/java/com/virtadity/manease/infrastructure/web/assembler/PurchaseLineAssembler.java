package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;
import com.virtadity.manease.infrastructure.web.dto.purchase.line.PurchaseLineResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseLineDTOMapper;
import com.virtadity.manease.infrastructure.web.controller.ProductController;
import com.virtadity.manease.infrastructure.web.controller.PurchaseController;
import com.virtadity.manease.infrastructure.web.controller.PurchaseLineController;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@Component
public class PurchaseLineAssembler
        implements RepresentationModelAssembler<PurchaseLineResponse, EntityModel<PurchaseLineResponseDTO>> {

    private final PurchaseLineDTOMapper purchaseLineDTOMapper;

    @Override
    public EntityModel<PurchaseLineResponseDTO> toModel(PurchaseLineResponse purchaseLineResponse) {
        var purchaseLineResponseDTO = purchaseLineDTOMapper.toPurchaseLineResponseDTO(purchaseLineResponse);
        return EntityModel.of(purchaseLineResponseDTO,
                linkTo(methodOn(PurchaseLineController.class).getAll()).withRel("purchase_lines"),
                linkTo(methodOn(PurchaseLineController.class)
                        .getOne(purchaseLineResponse.purchaseId(), purchaseLineResponse.productId())).withSelfRel(),
                linkTo(methodOn(PurchaseController.class).one(purchaseLineResponse.purchaseId())).withRel("purchase"),
                linkTo(methodOn(ProductController.class).one(purchaseLineResponse.productId())).withRel("product")
        );
    }
}
