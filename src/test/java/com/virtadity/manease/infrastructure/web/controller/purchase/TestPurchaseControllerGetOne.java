package com.virtadity.manease.infrastructure.web.controller.purchase;

import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.application.port.in.purchase.*;
import com.virtadity.manease.infrastructure.web.assembler.PurchaseAssembler;
import com.virtadity.manease.infrastructure.web.dto.purchase.PurchaseResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseDTOMapper;
import com.virtadity.manease.infrastructure.web.controller.ProducerController;
import com.virtadity.manease.infrastructure.web.controller.PurchaseController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PurchaseController.class)
public class TestPurchaseControllerGetOne {
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

    @Test
    void testGetOne() throws Exception {
        var id = UUID.randomUUID();
        var description = "Test description";
        var creationDate = LocalDateTime.now();
        var deliveryDate = LocalDateTime.now();
        var producerId = UUID.randomUUID();
        var purchaseResponse = new PurchaseResponse(id, description, creationDate, deliveryDate, producerId);
        when(purchaseGetOne.execute(id)).thenReturn(purchaseResponse);

        var purchaseResponseDTO = new PurchaseResponseDTO(id, description, creationDate, deliveryDate, producerId);
        var selfLink = linkTo(methodOn(PurchaseController.class).one(id)).withSelfRel();
        var allLink = linkTo(methodOn(PurchaseController.class).all()).withRel("purchases");
        var producerLink = linkTo(methodOn(ProducerController.class).one(producerId)).withRel("producer");
        var entityModel = EntityModel.of(purchaseResponseDTO, selfLink, allLink, producerLink);
        when(purchaseAssembler.toModel(any())).thenReturn(entityModel);

        var template = "{" +
                "\"description\": \"%s\", " +
                "\"creation_date\": \"%s\", " +
                "\"delivery_date\": \"%s\", " +
                "\"producer_id\": \"%s\"" +
                "}";
        var requestBody = String.format(template, description, creationDate, deliveryDate, producerId);

        var regex = "0+$";
        var creationDateClipped = creationDate.toString().replaceAll(regex, "");
        var deliveryDateClipped = deliveryDate.toString().replaceAll(regex, "");

        var request = String.format("/purchases/%s", id);
        mockMvc.perform(get(request).contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.description").value(description))
                .andExpect(jsonPath("$.creation_date").value(creationDateClipped))
                .andExpect(jsonPath("$.delivery_date").value(deliveryDateClipped))
                .andExpect(jsonPath("$.producer_id").value(producerId.toString()))
                .andExpect(jsonPath("$._links.self.href").value(selfLink.getHref()))
                .andExpect(jsonPath("$._links.purchases.href").value(allLink.getHref()));
    }
}
