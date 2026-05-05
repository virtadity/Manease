package com.virtadity.manease.infrastructure.web.controller.global.exception.handler;

import com.virtadity.manease.application.exception.ProductDoesNotExistException;
import com.virtadity.manease.application.exception.PurchaseDoesNotExistException;
import com.virtadity.manease.application.service.producer.exception.ProducerNotFoundException;
import com.virtadity.manease.application.service.product.exception.ProductNotFoundException;
import com.virtadity.manease.application.service.product.type.exception.ProductTypeNotFoundException;
import com.virtadity.manease.application.service.purchase.exception.PurchaseNotFoundException;
import com.virtadity.manease.application.service.purchase.line.exception.PurchaseLineNotFoundException;
import com.virtadity.manease.domain.service.purchase.validation.exception.ProducerIsDifferentException;
import com.virtadity.manease.domain.service.purchaseline.validation.exception.ProductIsAlreadyInPurchaseException;
import com.virtadity.manease.infrastructure.web.controller.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GlobalExceptionHandler.class)
public class TestGlobalExceptionHandler {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProducerController producerController;

    @MockitoBean
    private ProductController productController;

    @MockitoBean
    private ProductTypeController productTypeController;

    @MockitoBean
    private PurchaseController purchaseController;

    @MockitoBean
    private PurchaseLineController purchaseLineController;

    @Test
    void testProducerNotFoundException() throws Exception {
        var producerId = UUID.randomUUID();
        when(producerController.one(producerId)).thenThrow(ProducerNotFoundException.withId(producerId));
        mockMvc.perform(get("/producers/{producerId}", producerId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("404"));
    }

    @Test
    void testProductNotFoundException() throws Exception {
        var productId = UUID.randomUUID();
        when(productController.one(productId)).thenThrow(ProductNotFoundException.withId(productId));
        mockMvc.perform(get("/products/{productId}", productId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("404"));
    }

    @Test
    void testProductTypeNotFoundException() throws Exception {
        var productTypeId = UUID.randomUUID();
        when(productTypeController.one(productTypeId)).thenThrow(ProductTypeNotFoundException.withId(productTypeId));
        mockMvc.perform(get("/product_types/{product_type_id}", productTypeId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("404"));
    }

    @Test
    void testPurchaseNotFoundException() throws Exception {
        var purchaseId = UUID.randomUUID();
        when(purchaseController.one(purchaseId)).thenThrow(PurchaseNotFoundException.withId(purchaseId));
        mockMvc.perform(get("/purchases/{purchaseId}", purchaseId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("404"));
    }

    @Test
    void testPurchaseLineNotFoundException() throws Exception {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        when(purchaseLineController.getOne(purchaseId, productId))
                .thenThrow(PurchaseLineNotFoundException.withIds(purchaseId, productId));
        mockMvc.perform(
                get(
                        "/purchase_lines/purchase_line?purchase_id={purchaseId}&product_id={productId}",
                        purchaseId,
                        productId
                )
        ).andExpect(status().isNotFound()).andExpect(jsonPath("$.status").value("404"));
    }

    @Test
    void testProducerIsDifferent() throws Exception {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        var price = BigDecimal.valueOf(100.0);
        var quantity = BigInteger.valueOf(100);
        when(purchaseLineController.register(any()))
                .thenThrow(ProducerIsDifferentException.withIds(purchaseId, productId));

        var requestBody = String.format(
                "{\"purchase_id\": \"%s\", \"product_id\": \"%s\", \"price\": \"%s\", \"quantity\": \"%s\" }",
                purchaseId,
                productId,
                price,
                quantity
        );
        mockMvc.perform(post("/purchase_lines").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isUnprocessableContent())
                .andExpect(jsonPath("$.status").value("422"));
    }

    @Test
    void testProductIsAlreadyInPurchase() throws Exception {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        var price = BigDecimal.valueOf(100.0);
        var quantity = BigInteger.valueOf(100);
        when(purchaseLineController.register(any()))
                .thenThrow(ProductIsAlreadyInPurchaseException.withIds(purchaseId, productId));

        var requestBody = String.format(
                "{\"purchase_id\": \"%s\", \"product_id\": \"%s\", \"price\": \"%s\", \"quantity\": \"%s\" }",
                purchaseId,
                productId,
                price,
                quantity
        );

        mockMvc.perform(post("/purchase_lines").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isUnprocessableContent())
                .andExpect(jsonPath("$.status").value("422"));
    }

    @Test
    void testProductDoesNotExist() throws Exception {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        var price = BigDecimal.valueOf(100.0);
        var quantity = BigInteger.valueOf(100);
        when(purchaseLineController.register(any()))
                .thenThrow(ProductDoesNotExistException.withProductId(productId));

        var requestBody = String.format(
                "{\"purchase_id\": \"%s\", \"product_id\": \"%s\", \"price\": \"%s\", \"quantity\": \"%s\" }",
                purchaseId,
                productId,
                price,
                quantity
        );

        mockMvc.perform(post("/purchase_lines").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isUnprocessableContent())
                .andExpect(jsonPath("$.status").value("422"));
    }

    @Test
    void testPurchaseDoesNotExist() throws Exception {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        var price = BigDecimal.valueOf(100.0);
        var quantity = BigInteger.valueOf(100);
        when(purchaseLineController.register(any()))
                .thenThrow(PurchaseDoesNotExistException.withId(purchaseId));

        var requestBody = String.format(
                "{\"purchase_id\": \"%s\", \"product_id\": \"%s\", \"price\": \"%s\", \"quantity\": \"%s\" }",
                purchaseId,
                productId,
                price,
                quantity
        );

        mockMvc.perform(post("/purchase_lines").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isUnprocessableContent())
                .andExpect(jsonPath("$.status").value("422"));
    }
}
