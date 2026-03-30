package com.virtadity.manease.infrastructure.web.controller.product.type;

import com.virtadity.manease.application.port.in.product.type.*;
import com.virtadity.manease.infrastructure.web.assembler.ProductTypeAssembler;
import com.virtadity.manease.infrastructure.web.mapper.ProductTypeDTOMapper;
import com.virtadity.manease.infrastructure.web.controller.ProductTypeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductTypeController.class)
public class TestProductTypeControllerDelete {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductTypeRegisterInputBoundary productTypeRegister;

    @MockitoBean
    private ProductTypeGetOneInputBoundary productTypeGetOne;

    @MockitoBean
    private ProductTypeGetAllInputBoundary productTypeGetAll;

    @MockitoBean
    private ProductTypeCorrectInputBoundary productTypeCorrect;

    @MockitoBean
    private ProductTypeDeleteInputBoundary productTypeDelete;

    @MockitoBean
    private ProductTypeAssembler productTypeAssembler;

    @MockitoBean
    private ProductTypeDTOMapper productTypeDTOMapper;

    @Test
    void testDelete() throws Exception {
        var productTypeId = UUID.randomUUID();
        var deletePath = String.format("/product_types/%s", productTypeId);

        mockMvc.perform(delete(deletePath).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
