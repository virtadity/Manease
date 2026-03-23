package com.virtadity.manease.infrastructure.web.rest_controller.product_type;

import com.virtadity.manease.application.model.product_type.ProductTypeResponse;
import com.virtadity.manease.application.port.in.product_type.*;
import com.virtadity.manease.infrastructure.web.assembler.ProductTypeAssembler;
import com.virtadity.manease.infrastructure.web.dto.product_type.ProductTypeResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProductTypeDTOMapper;
import com.virtadity.manease.infrastructure.web.rest_controller.ProductTypeController;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductTypeController.class)
public class TestProductTypeControllerGetOne {

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
    void testGetOne() throws Exception {
        var productTypeId = UUID.randomUUID();
        var productTypeName = "Test product type name";
        var productTypeResponse = new ProductTypeResponse(productTypeId, productTypeName);
        when(productTypeGetOne.execute(productTypeId)).thenReturn(productTypeResponse);

        var productTypeDTO = new ProductTypeResponseDTO(productTypeId, productTypeName);
        var selfLink = linkTo(methodOn(ProductTypeController.class).one(productTypeId)).withSelfRel();
        var allLink = linkTo(methodOn(ProductTypeController.class).all()).withRel("product_types");
        var entityModel = EntityModel.of(productTypeDTO, selfLink, allLink);
        when(productTypeAssembler.toModel(any())).thenReturn(entityModel);

        var path = String.format("/product_types/%s", productTypeId);
        mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(productTypeId.toString()))
                .andExpect(jsonPath("$.name").value(productTypeName))
                .andExpect(jsonPath("$._links.self.href").value(selfLink.getHref()))
                .andExpect(jsonPath("$._links.product_types.href").value(allLink.getHref()));
    }
}
