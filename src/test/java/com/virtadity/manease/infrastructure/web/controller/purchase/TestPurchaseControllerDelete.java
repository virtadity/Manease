package com.virtadity.manease.infrastructure.web.controller.purchase;

import com.virtadity.manease.application.port.in.purchase.*;
import com.virtadity.manease.infrastructure.web.assembler.PurchaseAssembler;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseDTOMapper;
import com.virtadity.manease.infrastructure.web.controller.PurchaseController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PurchaseController.class)
public class TestPurchaseControllerDelete {
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
    void testDelete() throws Exception {
        var id = UUID.randomUUID();
        mockMvc.perform(delete("/purchases/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
