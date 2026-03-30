package com.virtadity.manease.infrastructure.web.controller.purchase;

import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.application.port.in.purchase.*;
import com.virtadity.manease.infrastructure.web.assembler.PurchaseAssembler;
import com.virtadity.manease.infrastructure.web.dto.purchase.PurchaseResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseDTOMapper;
import com.virtadity.manease.infrastructure.web.controller.ProducerController;
import com.virtadity.manease.infrastructure.web.controller.PurchaseController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PurchaseController.class)
public class TestPurchaseControllerGetAll {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PurchaseRegisterInputBoundary purchaseRegister;

    @MockitoBean
    private PurchaseGetOneInputBoundary purchaseGetOne;

    @MockitoBean
    private PurchaseGetAllInputBoundary purchaseGetAll;

    @MockitoBean
    private PurchaseCorrectInputBoundary purchaseCorrect;

    @MockitoBean
    private PurchaseDeleteInputBoundary purchaseDelete;

    @MockitoBean
    private PurchaseDTOMapper purchaseDTOMapper;

    @MockitoBean
    private PurchaseAssembler purchaseAssembler;

    private List<PurchaseResponse> purchaseResponseList;
    private CollectionModel<EntityModel<PurchaseResponseDTO>> purchaseCollectionModel;

    @BeforeEach
    void setUp() {
        setUpPurchaseResponseList();
        setUpPurchaseCollectionModel();
    }

    void setUpPurchaseResponseList() {
        setUpPurchaseResponseList(10);
    }

    void setUpPurchaseResponseList(int size) {
        purchaseResponseList = new ArrayList<>(size);
        for (int index = 0; index < size; index++ ) {
            var purchaseId = UUID.randomUUID();
            var description = String.format("Test description: %s", size);
            var creationDate = LocalDateTime.now();
            var deliveryDate = LocalDateTime.now();
            var producerId = UUID.randomUUID();
            var purchaseResponse = new PurchaseResponse(
                    purchaseId,
                    description,
                    creationDate,
                    deliveryDate,
                    producerId
            );
            purchaseResponseList.add(purchaseResponse);
        }
    }

    void setUpPurchaseCollectionModel() {
        var purchaseEntityModelList = purchaseResponseList.stream().map( purchaseResponse -> {
            var purchaseId = purchaseResponse.purchaseId();
            var description = purchaseResponse.description();
            var creationDate = purchaseResponse.creationDate();
            var deliveryDate = purchaseResponse.deliveryDate();
            var producerId = purchaseResponse.producerId();
            var purchaseDTO = new PurchaseResponseDTO(purchaseId, description, creationDate, deliveryDate, producerId);
            var selfLink = linkTo(methodOn(PurchaseController.class).one(purchaseId)).withSelfRel();
            var allLink = linkTo(methodOn(PurchaseController.class).all()).withRel("purchases");
            var producerLink = linkTo(methodOn(ProducerController.class).one(producerId)).withRel("producer");
            return EntityModel.of(purchaseDTO, selfLink, allLink, producerLink);
        }
        ).toList();
        purchaseCollectionModel = CollectionModel.of(purchaseEntityModelList);
    }

    @Test
    void testGetAll() throws Exception {
        when(purchaseGetAll.execute()).thenReturn(purchaseResponseList);
        when(purchaseAssembler.toCollectionModel(any())).thenReturn(purchaseCollectionModel);
        mockMvc.perform(get("/purchases").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
}
