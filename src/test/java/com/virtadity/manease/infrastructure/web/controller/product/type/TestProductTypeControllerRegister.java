package com.virtadity.manease.infrastructure.web.controller.product.type;

import com.virtadity.manease.application.model.product.type.ProductTypeResponse;
import com.virtadity.manease.application.port.in.product.type.*;
import com.virtadity.manease.infrastructure.web.assembler.ProductTypeAssembler;
import com.virtadity.manease.infrastructure.web.dto.product.type.ProductTypeResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProductTypeDTOMapper;
import com.virtadity.manease.infrastructure.web.controller.ProductTypeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductTypeController.class)
public class TestProductTypeControllerRegister {

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
    private ProductTypeDTOMapper productTypeDTOMapper;

    @MockitoBean
    private ProductTypeAssembler productTypeAssembler;

    @Test
    void testRegister() throws Exception {
        var productTypeId = UUID.randomUUID();
        var productTypeName = "Test product type name";
        var productTypeResponse = new ProductTypeResponse(productTypeId, productTypeName);
        when(productTypeRegister.execute(any())).thenReturn(productTypeResponse);

        var productTypeDTO = new ProductTypeResponseDTO(productTypeId, productTypeName);
        var selfLink = linkTo(methodOn(ProductTypeController.class).one(productTypeId)).withSelfRel();
        var allLink = linkTo(methodOn(ProductTypeController.class).all()).withRel("product_types");
        var entityModel = EntityModel.of(productTypeDTO, selfLink, allLink);
        when(productTypeAssembler.toModel(productTypeResponse)).thenReturn(entityModel);

        var requestBody = String.format("{\"name\": \"%s\"}", productTypeName);
        mockMvc.perform(
                post("/product_types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(productTypeName))
                .andExpect(jsonPath("$._links.self.href").value(selfLink.getHref()))
                .andExpect(jsonPath("$._links.product_types.href").value(allLink.getHref()));
    }
}
