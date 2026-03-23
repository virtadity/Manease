package com.virtadity.manease.infrastructure.web.rest_controller.purchase_line;

import com.virtadity.manease.application.port.in.purchase_line.*;
import com.virtadity.manease.infrastructure.web.assembler.PurchaseLineAssembler;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseLineDTOMapper;
import com.virtadity.manease.infrastructure.web.rest_controller.PurchaseLineController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PurchaseLineController.class)
public class TestPurchaseLineControllerDelete {
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
    private PurchaseLineDeleteInputBoundary deletePurchaseLine;

    @MockitoBean
    private PurchaseLineAssembler purchaseLineAssembler;

    @MockitoBean
    private PurchaseLineDTOMapper purchaseLineDTOMapper;

    @Test
    void testDelete() throws Exception {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        mockMvc.perform(
                delete(
                        "/purchase_lines?purchase_id={purchasedId}&product_id={productId}",
                        purchaseId,
                        productId
                ).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }
}
