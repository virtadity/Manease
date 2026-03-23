package com.virtadity.manease.infrastructure.web.rest_controller;

import com.virtadity.manease.application.model.product_type.ProductTypeResponse;
import com.virtadity.manease.application.port.in.product_type.*;
import com.virtadity.manease.infrastructure.web.dto.product_type.ProductTypeRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.product_type.ProductTypeResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProductTypeDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product_types")
public class ProductTypeController {

    private final ProductTypeDTOMapper productTypeDTOMapper;
    private final ProductTypeGetAllInputBoundary getAllAction;
    private final ProductTypeGetOneInputBoundary getOneAction;
    private final ProductTypeRegisterInputBoundary registerAction;
    private final ProductTypeCorrectInputBoundary correctAction;
    private final ProductTypeDeleteInputBoundary deleteAction;
    private final RepresentationModelAssembler<ProductTypeResponse, EntityModel<ProductTypeResponseDTO>>
            productTypeAssembler;

    @GetMapping
    public CollectionModel<EntityModel<ProductTypeResponseDTO>> all() {
        var productTypeResponseList = getAllAction.execute();
        return productTypeAssembler.toCollectionModel(productTypeResponseList);
    }

    @GetMapping("/{id}")
    public EntityModel<ProductTypeResponseDTO> one(@PathVariable("id") UUID productTypeId) {
        var productTypeResponse = getOneAction.execute(productTypeId);
        return productTypeAssembler.toModel(productTypeResponse);
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody ProductTypeRequestDTO productTypeRequestDTO) {
        var productTypeRequest = productTypeDTOMapper.toProductTypeRequest(productTypeRequestDTO);
        var productTypeResponse = registerAction.execute(productTypeRequest);
        var productTypeResponseModel = productTypeAssembler.toModel(productTypeResponse);
        var createdLocation = productTypeResponseModel.getRequiredLink(IanaLinkRelations.SELF).toUri();
        return ResponseEntity.created(createdLocation).body(productTypeResponseModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> correct(
            @PathVariable("id") UUID productTypeId,
            @RequestBody ProductTypeRequestDTO productTypeRequestDTO
    ) {
        var productTypeRequest = productTypeDTOMapper.toProductTypeRequest(productTypeRequestDTO);
        var productTypeResponse = correctAction.execute(productTypeId, productTypeRequest);
        var productTypeResponseModel = productTypeAssembler.toModel(productTypeResponse);
        var createdLocation = productTypeResponseModel.getRequiredLink(IanaLinkRelations.SELF).toUri();
        return ResponseEntity.created(createdLocation).body(productTypeResponseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID productTypeId) {
        deleteAction.execute(productTypeId);
        return ResponseEntity.noContent().build();
    }
}
