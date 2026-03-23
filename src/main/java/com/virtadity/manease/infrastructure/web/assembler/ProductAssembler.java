package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.infrastructure.web.dto.product.ProductResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProductDTOMapper;
import com.virtadity.manease.infrastructure.web.rest_controller.ProducerController;
import com.virtadity.manease.infrastructure.web.rest_controller.ProductController;
import com.virtadity.manease.infrastructure.web.rest_controller.ProductTypeController;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class ProductAssembler implements
        RepresentationModelAssembler<ProductResponse, EntityModel<ProductResponseDTO>> {

    private final ProductDTOMapper productDTOMapper;

    @Override
    public EntityModel<ProductResponseDTO> toModel(ProductResponse productResponse) {
        var productResponseDTO = productDTOMapper.toProductResponseDTO(productResponse);
        return EntityModel.of(
                productResponseDTO,
                linkTo(methodOn(ProductController.class)
                        .all())
                        .withRel("products"),

                linkTo(methodOn(ProductController.class)
                        .one(productResponse.productId()))
                        .withSelfRel(),

                linkTo(methodOn(ProducerController.class)
                        .one(productResponse.producerId()))
                        .withRel("producer"),

                linkTo(methodOn(ProductTypeController.class)
                        .one(productResponse.productTypeId()))
                        .withRel("product_type")
        );
    }
}
