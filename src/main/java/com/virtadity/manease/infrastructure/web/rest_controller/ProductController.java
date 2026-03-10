package com.virtadity.manease.infrastructure.web.rest_controller;

import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.application.port.in.product.*;
import com.virtadity.manease.infrastructure.web.dto.product.ProductRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.product.ProductResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProductMapper;
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
@RequestMapping("/products")
public class ProductController {
    private final RepresentationModelAssembler<ProductResponse, EntityModel<ProductResponseDTO>> productAssembler;
    private final ProductMapper productMapper;
    private final ProductGetAllInputBoundary getAllAction;
    private final ProductGetOneInputBoundary getOneAction;
    private final ProductRegisterInputBoundary registerAction;
    private final ProductCorrectInputBoundary correctAction;
    private final ProductDeleteInputBoundary deleteAction;

    @GetMapping
    public CollectionModel<EntityModel<ProductResponseDTO>> all() {
        var productResponseList = getAllAction.execute();
        return productAssembler.toCollectionModel(productResponseList);
    }

    @GetMapping("/{id}")
    public EntityModel<ProductResponseDTO> one(@PathVariable("id") UUID id) {
        var productResponse = getOneAction.execute(id);
        return productAssembler.toModel(productResponse);
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody ProductRequestDTO productRequestDTO) {
        var productRequest = productMapper.toProductRequest(productRequestDTO);
        var productResponse = registerAction.execute(productRequest);
        var productModel = productAssembler.toModel(productResponse);
        var createdLocation = productModel.getRequiredLink(IanaLinkRelations.SELF).toUri();
        return ResponseEntity.created(createdLocation).body(productModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> correct(@PathVariable("id") UUID id, @RequestBody ProductRequestDTO productRequestDTO) {
        var productRequest = productMapper.toProductRequest(productRequestDTO);
        var productResponse = correctAction.execute(id, productRequest);
        var productModel = productAssembler.toModel(productResponse);
        var createdLocation = productModel.getRequiredLink(IanaLinkRelations.SELF).toUri();
        return ResponseEntity.created(createdLocation).body(productModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        deleteAction.execute(id);
        return ResponseEntity.noContent().build();
    }
}
