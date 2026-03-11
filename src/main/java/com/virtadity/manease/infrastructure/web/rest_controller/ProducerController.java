package com.virtadity.manease.infrastructure.web.rest_controller;

import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.application.port.in.producer.*;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProducerDTOMapper;
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
public class ProducerController {

    private final RepresentationModelAssembler<ProducerResponse, EntityModel<ProducerResponseDTO>> producerAssembler;
    private final ProducerDTOMapper producerDTOMapper;
    private final ProducerRegisterInputBoundary producerRegisterAction;
    private final ProducerGetOneInputBoundary getOneAction;
    private final ProducerGetAllInputBoundary getAllAction;
    private final ProducerCorrectInputBoundary correctAction;
    private final ProducerDeleteInputBoundary deleteAction;


    @GetMapping
    public CollectionModel<EntityModel<ProducerResponseDTO>> all() {
        var producerResponseList = getAllAction.execute();
        return producerAssembler.toCollectionModel(producerResponseList);
    }

    @GetMapping("/{id}")
    public EntityModel<ProducerResponseDTO> one(@PathVariable("id") UUID producerId) {
        var producerResponse = getOneAction.execute(producerId);
        return producerAssembler.toModel(producerResponse);
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody ProducerRequestDTO producerRequestDTO) {
        var producerRequest = producerDTOMapper.toProducerRequest(producerRequestDTO);
        var producerResponse = producerRegisterAction.execute(producerRequest);
        var producerResponseModel = producerAssembler.toModel(producerResponse);
        var createdLocation = producerResponseModel.getRequiredLink(IanaLinkRelations.SELF).toUri();
        return ResponseEntity.created(createdLocation).body(producerResponseModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> correct(
            @PathVariable("id") UUID producerId,
            @RequestBody ProducerRequestDTO producerRequestDTO
    ) {
        var producerRequest = producerDTOMapper.toProducerRequest(producerRequestDTO);
        var producerResponse = correctAction.execute(producerId, producerRequest);
        var producerResponseModel = producerAssembler.toModel(producerResponse);
        var createdLocation = producerResponseModel.getRequiredLink(IanaLinkRelations.SELF).toUri();
        return ResponseEntity.created(createdLocation).body(producerResponseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID producerId) {
        deleteAction.execute(producerId);
        return ResponseEntity.noContent().build();
    }
}
