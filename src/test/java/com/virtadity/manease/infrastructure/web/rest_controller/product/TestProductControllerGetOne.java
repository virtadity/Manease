package com.virtadity.manease.infrastructure.web.rest_controller.product;

import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.application.port.in.product.*;
import com.virtadity.manease.infrastructure.web.assembler.ProductAssembler;
import com.virtadity.manease.infrastructure.web.dto.product.ProductResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProductDTOMapper;
import com.virtadity.manease.infrastructure.web.rest_controller.ProducerController;
import com.virtadity.manease.infrastructure.web.rest_controller.ProductController;
import com.virtadity.manease.infrastructure.web.rest_controller.ProductTypeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class TestProductControllerGetOne {

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
    void testGetOne() throws Exception {
        var id = UUID.randomUUID();
        var name = "Test product name";
        var weight = BigDecimal.valueOf(100.0);
        var producerId = UUID.randomUUID();
        var productTypeId = UUID.randomUUID();
        var productResponse = new ProductResponse(id, name, weight, producerId, productTypeId);
        when(productGetOne.execute(any())).thenReturn(productResponse);

        var selfLink = linkTo(methodOn(ProductController.class).one(id)).withSelfRel();
        var allLink = linkTo(methodOn(ProductController.class).all()).withRel("products");
        var producerLink = linkTo(methodOn(ProducerController.class).one(producerId)).withRel("producer");
        var productTypeLink = linkTo(methodOn(ProductTypeController.class).one(productTypeId)).withRel("product_type");
        var productResponseDTO = new ProductResponseDTO(id, name, weight, producerId, productTypeId);
        var entityModel = EntityModel.of(productResponseDTO, selfLink, allLink, producerLink, productTypeLink);
        when(productAssembler.toModel(any())).thenReturn(entityModel);

        var path = String.format("/products/%s", id);
        mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.weight").value(weight))
                .andExpect(jsonPath("$.producer_id").value(producerId.toString()))
                .andExpect(jsonPath("$.product_type_id").value(productTypeId.toString()))
                .andExpect(jsonPath("$._links.self.href").value(selfLink.getHref()))
                .andExpect(jsonPath("$._links.products.href").value(allLink.getHref()))
                .andExpect(jsonPath("$._links.producer.href").value(producerLink.getHref()))
                .andExpect(jsonPath("$._links.product_type.href").value(productTypeLink.getHref()));
    }
}
