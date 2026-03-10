package com.virtadity.manease.infrastructure.web.rest_controller;

import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.application.port.in.purchase.*;
import com.virtadity.manease.infrastructure.web.dto.purchase.PurchaseRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.purchase.PurchaseResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseMapper;
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
@RequestMapping("/purchases")
public class PurchaseController {
    private final RepresentationModelAssembler<PurchaseResponse, EntityModel<PurchaseResponseDTO>> purchaseAssembler;
    private final PurchaseMapper purchaseMapper;
    private final PurchaseGetAllInputBoundary getAllAction;
    private final PurchaseGetOneInputBoundary getOneAction;
    private final PurchaseRegisterInputBoundary registerAction;
    private final PurchaseCorrectInputBoundary correctAction;
    private final PurchaseDeleteInputBoundary deleteAction;

    @GetMapping
    public CollectionModel<EntityModel<PurchaseResponseDTO>> all() {
        var purchaseResponseList = getAllAction.execute();
        return purchaseAssembler.toCollectionModel(purchaseResponseList);
    }

    @GetMapping("/{id}")
    public EntityModel<PurchaseResponseDTO> one(@PathVariable("id") UUID purchaseId) {
        var purchaseResponse = getOneAction.execute(purchaseId);
        return purchaseAssembler.toModel(purchaseResponse);
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody PurchaseRequestDTO purchaseRequestDTO) {
        var purchaseRequest = purchaseMapper.toPurchaseRequest(purchaseRequestDTO);
        var purchaseResponse = registerAction.execute(purchaseRequest);
        var purchaseModel = purchaseAssembler.toModel(purchaseResponse);
        var createdLocation = purchaseModel.getRequiredLink(IanaLinkRelations.SELF).toUri();
        return ResponseEntity.created(createdLocation).body(purchaseModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> correct(
            @PathVariable("id") UUID purchaseId,
            @RequestBody PurchaseRequestDTO purchaseRequestDTO
    ) {
        var purchaseRequest = purchaseMapper.toPurchaseRequest(purchaseRequestDTO);
        var purchaseResponse = correctAction.execute(purchaseId, purchaseRequest);
        var purchaseModel = purchaseAssembler.toModel(purchaseResponse);
        var createdLocation = purchaseModel.getRequiredLink(IanaLinkRelations.SELF).toUri();
        return ResponseEntity.created(createdLocation).body(purchaseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID purchaseId) {
        deleteAction.execute(purchaseId);
        return ResponseEntity.noContent().build();
    }
}
