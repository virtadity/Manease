package com.virtadity.manease.infrastructure.web.controller.purchase.line;

import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;
import com.virtadity.manease.application.port.in.purchase.line.*;
import com.virtadity.manease.infrastructure.web.assembler.PurchaseLineAssembler;
import com.virtadity.manease.infrastructure.web.dto.purchase.line.PurchaseLineResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseLineDTOMapper;
import com.virtadity.manease.infrastructure.web.controller.ProductController;
import com.virtadity.manease.infrastructure.web.controller.PurchaseController;
import com.virtadity.manease.infrastructure.web.controller.PurchaseLineController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PurchaseLineController.class)
public class TestPurchaseLineControllerGetAll {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PurchaseLineGetOneInputBoundary getOne;

    @MockitoBean
    private PurchaseLineGetAllInputBoundary getAll;

    @MockitoBean
    private PurchaseLineRegisterInputBoundary register;

    @MockitoBean
    private PurchaseLineCorrectInputBoundary correct;

    @MockitoBean
    private PurchaseLineDeleteInputBoundary delete;

    @MockitoBean
    private PurchaseLineAssembler purchaseLineAssembler;

    @MockitoBean
    private PurchaseLineDTOMapper purchaseLineDTOMapper;

    private List<PurchaseLineResponse> purchaseLineResponseList;
    private CollectionModel<EntityModel<PurchaseLineResponseDTO>> purchaseLineCollectionModel;

    @BeforeEach
    void setUp() {
        setUpPurchaseLineResponseList();
        setUpPurchaseLineCollectionModel();
    }

    private void setUpPurchaseLineResponseList() {
        setUpPurchaseLineResponseList(10);
    }

    private void setUpPurchaseLineResponseList(int size) {
        purchaseLineResponseList = new ArrayList<>();

        var random = new Random();
        var minimumPrice = 100.0;
        var maximumPrice = 1000.0;
        var minimumQuantity = 100;
        var maximumQuantity = 1000;

        for (int index = 0; index < size; index++ ) {
            var purchaseId = UUID.randomUUID();
            var productId = UUID.randomUUID();
            var priceGeneratedValue = minimumPrice + random.nextDouble() * (maximumPrice - minimumPrice);
            var quantityGeneratedValue = minimumQuantity + random.nextInt() * (maximumQuantity - minimumQuantity);
            var price = BigDecimal.valueOf(priceGeneratedValue);
            var quantity = BigInteger.valueOf(quantityGeneratedValue);
            var purchaseLineResponse = new PurchaseLineResponse(purchaseId, productId, price, quantity);
            purchaseLineResponseList.add(purchaseLineResponse);
        }
    }

    private void setUpPurchaseLineCollectionModel() {
        var purchaseLineEntityList = purchaseLineResponseList.stream().map(purchaseLineResponse -> {
            var purchaseId = purchaseLineResponse.purchaseId();
            var productId = purchaseLineResponse.productId();
            var price = purchaseLineResponse.price();
            var quantity = purchaseLineResponse.quantity();
            var purchaseLineDTO = new PurchaseLineResponseDTO(purchaseId, productId, price, quantity);
            var selfLink = linkTo(methodOn(PurchaseLineController.class).getOne(purchaseId, productId)).withSelfRel();
            var allLink = linkTo(methodOn(PurchaseLineController.class).getAll()).withRel("purchase_lines");
            var purchaseLink = linkTo(methodOn(PurchaseController.class).one(purchaseId)).withRel("purchase");
            var productLink = linkTo(methodOn(ProductController.class).one(productId)).withRel("product");
            return EntityModel.of(purchaseLineDTO, selfLink, allLink, purchaseLink, productLink);
            }
        ).toList();
        purchaseLineCollectionModel = CollectionModel.of(purchaseLineEntityList);
    }

    @Test
    void testGetAll() throws Exception {
        when(getAll.execute()).thenReturn(purchaseLineResponseList);
        when(purchaseLineAssembler.toCollectionModel(purchaseLineResponseList)).thenReturn(purchaseLineCollectionModel);
        mockMvc.perform(get("/purchase_lines").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
