package com.virtadity.manease.infrastructure.web.controller.purchase.line;

import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;
import com.virtadity.manease.application.port.in.purchase.line.*;
import com.virtadity.manease.infrastructure.web.assembler.PurchaseLineAssembler;
import com.virtadity.manease.infrastructure.web.dto.purchase.line.PurchaseLineResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseLineDTOMapper;
import com.virtadity.manease.infrastructure.web.controller.ProductController;
import com.virtadity.manease.infrastructure.web.controller.PurchaseController;
import com.virtadity.manease.infrastructure.web.controller.PurchaseLineController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PurchaseLineController.class)
public class TestPurchaseLineControllerCorrect {
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

    @Test
    void testCorrect() throws Exception {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        var price = BigDecimal.valueOf(100.0);
        var quantity = BigInteger.valueOf(100);
        var purchaseLineResponse = new PurchaseLineResponse(purchaseId, productId, price, quantity);
        when(register.execute(any())).thenReturn(purchaseLineResponse);

        var purchaseLineDTO = new PurchaseLineResponseDTO(purchaseId, productId, price, quantity);
        var selfLink = linkTo(methodOn(PurchaseLineController.class).getOne(purchaseId, productId)).withSelfRel();
        var allLink = linkTo(methodOn(PurchaseLineController.class).getAll()).withRel("purchase_lines");
        var purchaseLink = linkTo(methodOn(PurchaseController.class).one(purchaseId)).withRel("purchase");
        var productLink = linkTo(methodOn(ProductController.class).one(productId)).withRel("product");
        var purchaseLineEntityModel = EntityModel.of(purchaseLineDTO, selfLink, allLink, purchaseLink, productLink);
        when(purchaseLineAssembler.toModel(any())).thenReturn(purchaseLineEntityModel);

        var template = "{" +
                "\"purchase_id\": \"%s\", " +
                "\"product_id\": \"%s\", " +
                "\"price\": \"%s\", " +
                "\"quantity\": \"%s\"" +
                "}";
        var requestBody = String.format(template, purchaseId, productId, price, quantity);
        mockMvc.perform(put(
                "/purchase_lines?purchase_id={purchaseId}&product_id={productId}",
                        purchaseId,
                        productId
                ).contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.purchase_id").value(purchaseId.toString()))
                .andExpect(jsonPath("$.product_id").value(productId.toString()))
                .andExpect(jsonPath("$.price").value(price))
                .andExpect(jsonPath("$.quantity").value(quantity))
                .andExpect(jsonPath("$._links.self.href").value(selfLink.getHref()))
                .andExpect(jsonPath("$._links.purchase_lines.href").value(allLink.getHref()))
                .andExpect(jsonPath("$._links.purchase.href").value(purchaseLink.getHref()))
                .andExpect(jsonPath("$._links.product.href").value(productLink.getHref()));
    }
}
