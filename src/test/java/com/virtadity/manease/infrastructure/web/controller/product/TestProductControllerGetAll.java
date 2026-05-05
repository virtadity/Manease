package com.virtadity.manease.infrastructure.web.controller.product;

import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.application.port.in.product.*;
import com.virtadity.manease.infrastructure.web.assembler.ProductAssembler;
import com.virtadity.manease.infrastructure.web.dto.product.ProductResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProductDTOMapper;
import com.virtadity.manease.infrastructure.web.controller.ProducerController;
import com.virtadity.manease.infrastructure.web.controller.ProductController;
import com.virtadity.manease.infrastructure.web.controller.ProductTypeController;
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
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class TestProductControllerGetAll {
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

    private List<ProductResponse> productResponseList;
    private CollectionModel<EntityModel<ProductResponseDTO>> productCollectionModel;

    @BeforeEach
    void setUp() {
        setUpProductResponseList();
        setUpProductCollectionModel();
    }

    void setUpProductResponseList() {
        productResponseList = List.of(
                new ProductResponse(
                        UUID.randomUUID(),
                        "Test product name 1",
                        BigDecimal.valueOf(100.0),
                        UUID.randomUUID(),
                        UUID.randomUUID()
                ),

                new ProductResponse(
                        UUID.randomUUID(),
                        "Test product name 2",
                        BigDecimal.valueOf(100.0),
                        UUID.randomUUID(),
                        UUID.randomUUID()
                ),

                new ProductResponse(
                        UUID.randomUUID(),
                        "Test product name 3",
                        BigDecimal.valueOf(100.0),
                        UUID.randomUUID(),
                        UUID.randomUUID()
                ),

                new ProductResponse(
                        UUID.randomUUID(),
                        "Test product name 4",
                        BigDecimal.valueOf(100.0),
                        UUID.randomUUID(),
                        UUID.randomUUID()
                ),

                new ProductResponse(
                        UUID.randomUUID(),
                        "Test product name 5",
                        BigDecimal.valueOf(100.0),
                        UUID.randomUUID(),
                        UUID.randomUUID()
                )
        );
    }

    void setUpProductCollectionModel() {
        var productEntityModelList = this.productResponseList.stream().map(productResponse -> {
            var productId = productResponse.productId();
            var name = productResponse.name();
            var weight = productResponse.weight();
            var producerId = productResponse.producerId();
            var productTypeId = productResponse.productTypeId();
            var productDTO = new ProductResponseDTO(productId, name, weight, producerId, productTypeId);
            var selfLink = linkTo(methodOn(ProductController.class).one(productId)).withSelfRel();
            var allLink = linkTo(methodOn(ProductController.class).all()).withRel("products");
            var producerLink = linkTo(methodOn(ProducerController.class).one(producerId)).withRel("producer");
            var productTypeLink = linkTo(methodOn(ProductTypeController.class).one(productTypeId))
                    .withRel("productType");
            return EntityModel.of(productDTO, selfLink, allLink, producerLink, productTypeLink);
        }).toList();
    }

    @Test
    void testGetAll() throws Exception{

        when(productGetAll.execute()).thenReturn(productResponseList);
        when(productAssembler.toCollectionModel(any())).thenReturn(productCollectionModel);

        mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
}
