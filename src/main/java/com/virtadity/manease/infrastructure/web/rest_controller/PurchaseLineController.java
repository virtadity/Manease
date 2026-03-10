package com.virtadity.manease.infrastructure.web.rest_controller;

import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;
import com.virtadity.manease.application.port.in.purchase_line.*;
import com.virtadity.manease.infrastructure.web.dto.purchase_line.PurchaseLineRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.purchase_line.PurchaseLineResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseLineMapper;
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
@RequestMapping("/purchase_lines")
public class PurchaseLineController {
    private final RepresentationModelAssembler<
            PurchaseLineResponse,
            EntityModel<PurchaseLineResponseDTO>
            > purchaseLineAssembler;

    private final PurchaseLineMapper purchaseLineMapper;
    private final PurchaseLineGetOneInputBoundary getOneAction;
    private final PurchaseLineGetAllInputBoundary getAllAction;
    private final PurchaseLineRegisterInputBoundary registerAction;
    private final PurchaseLineCorrectInputBoundary correctAction;
    private final PurchaseLineDeleteInputBoundary deleteAction;

    @GetMapping
    public CollectionModel<EntityModel<PurchaseLineResponseDTO>> getAll() {
        var purchaseLineResponseList = getAllAction.execute();
        return purchaseLineAssembler.toCollectionModel(purchaseLineResponseList);
    }

    @GetMapping("/purchaseLine")
    public EntityModel<PurchaseLineResponseDTO> getOne(
            @RequestParam("purchase_id") UUID purchaseId,
            @RequestParam("product_id") UUID productId
    ) {
        var purchaseLineResponse = getOneAction.execute(purchaseId, productId);
        return purchaseLineAssembler.toModel(purchaseLineResponse);
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody PurchaseLineRequestDTO purchaseLineRequestDTO) {
        var purchaseLineRequest = purchaseLineMapper.toPurchaseLineRequest(purchaseLineRequestDTO);
        var purchaseLineResponse = registerAction.execute(purchaseLineRequest);
        var purchaseLineModel = purchaseLineAssembler.toModel(purchaseLineResponse);
        var creationLocation = purchaseLineModel.getRequiredLink(IanaLinkRelations.SELF).toUri();
        return ResponseEntity.created(creationLocation).body(purchaseLineModel);
    }

    @PutMapping
    public ResponseEntity<?> correct(
            @RequestParam("purchase_id") UUID purchaseId,
            @RequestParam("product_id") UUID productId,
            @RequestBody PurchaseLineRequestDTO purchaseLineRequestDTO
    ) {
        var purchaseLineRequest = purchaseLineMapper.toPurchaseLineRequest(purchaseLineRequestDTO);
        var purchaseLineResponse = correctAction.execute(purchaseId, productId, purchaseLineRequest);
        var purchaseLineModel = purchaseLineAssembler.toModel(purchaseLineResponse);
        var creationLocation = purchaseLineModel.getRequiredLink(IanaLinkRelations.SELF).toUri();
        return ResponseEntity.created(creationLocation).body(purchaseLineModel);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(
            @RequestParam("purchase_id") UUID purchaseId,
            @RequestParam("product_id") UUID productId
    ) {
        deleteAction.execute(purchaseId, productId);
        return ResponseEntity.noContent().build();
    }
}
