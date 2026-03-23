package com.virtadity.manease.infrastructure.web.rest_controller.product;

import com.virtadity.manease.application.port.in.product.*;
import com.virtadity.manease.infrastructure.web.assembler.ProductAssembler;
import com.virtadity.manease.infrastructure.web.mapper.ProductDTOMapper;
import com.virtadity.manease.infrastructure.web.rest_controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class TestProductControllerDelete {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductGetOneInputBoundary productGetOne;

    @MockitoBean
    private ProductGetAllInputBoundary productGetAll;

    @MockitoBean
    private ProductRegisterInputBoundary productRegister;

    @MockitoBean
    private ProductCorrectInputBoundary productCorrect;

    @MockitoBean
    private ProductDeleteInputBoundary productDelete;

    @MockitoBean
    private ProductDTOMapper productDTOMapper;

    @MockitoBean
    private ProductAssembler productAssembler;

    @Test
    void testDelete() throws Exception {
        var id = UUID.randomUUID();
        var request = String.format("/products/%s", id);

        mockMvc.perform(delete(request)).andExpect(status().isNoContent());
    }
}
