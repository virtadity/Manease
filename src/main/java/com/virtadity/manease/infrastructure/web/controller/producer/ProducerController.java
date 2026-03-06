package com.virtadity.manease.infrastructure.web.controller.producer;

import com.virtadity.manease.application.port.in.producer.ProducerGetAllInputBoundary;
import com.virtadity.manease.application.port.in.producer.ProducerGetOneInputBoundary;
import com.virtadity.manease.application.port.in.producer.ProducerRegisterInputBoundary;
import com.virtadity.manease.infrastructure.web.assembler.producer.ProducerAssembler;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/products/")
public class ProducerController {

    private final ProducerAssembler producerAssembler;
    private final ProducerRegisterInputBoundary producerRegisterAction;
    private final ProducerGetOneInputBoundary producerGetOneAction;
    private final ProducerGetAllInputBoundary producerGetAllAction;

    @GetMapping
    public CollectionModel<EntityModel<ProducerRequestDTO>> all() {
        return null;
    }

    @GetMapping("{id}")
    public EntityModel<ProducerRequestDTO> one(@PathVariable("id") UUID producerId) {
        producerGetOneAction.execute(producerId);
        return null;
    }
}
