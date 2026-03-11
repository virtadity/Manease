package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.product_type.ProductTypeResponse;
import com.virtadity.manease.infrastructure.web.dto.product_type.ProductTypeResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProductTypeDTOMapper;
import com.virtadity.manease.infrastructure.web.rest_controller.ProductTypeController;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class ProductTypeAssembler implements
        RepresentationModelAssembler<ProductTypeResponse, EntityModel<ProductTypeResponseDTO>> {

    private ProductTypeDTOMapper productTypeDTOMapper;

    @Override
    public EntityModel<ProductTypeResponseDTO> toModel(ProductTypeResponse productTypeResponse) {
        var productTypeResponseDTO = productTypeDTOMapper.toProductTypeResponseDTO(productTypeResponse);

        return EntityModel.of(productTypeResponseDTO,
                linkTo(methodOn(ProductTypeController.class).all()).withRel("product_types"),
                linkTo(methodOn(ProductTypeController.class).one(productTypeResponse.productTypeId())).withSelfRel());
    }
}
