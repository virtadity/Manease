package com.virtadity.manease.infrastructure.web.controller.product.type;

import com.virtadity.manease.application.port.in.product.type.*;
import com.virtadity.manease.infrastructure.web.assembler.ProductTypeAssembler;
import com.virtadity.manease.infrastructure.web.dto.product.type.ProductTypeResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ProductTypeDTOMapper;
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

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductTypeController.class)
public class TestProductTypeControllerGetAll {

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

    private List<ProductTypeResponseDTO> productTypeResponseDTOList;

    private CollectionModel<EntityModel<ProductTypeResponseDTO>> productTypeCollectionModel;

    @BeforeEach
    void setUp() {
        setUpProductTypeResponseDTOList();
        setUpProductTypeCollectionModel();
    }

    void setUpProductTypeResponseDTOList() {
        productTypeResponseDTOList = List.of(
                new ProductTypeResponseDTO(UUID.randomUUID(), "Apples"),
                new ProductTypeResponseDTO(UUID.randomUUID(), "Laptops"),
                new ProductTypeResponseDTO(UUID.randomUUID(), "Personal computes"),
                new ProductTypeResponseDTO(UUID.randomUUID(), "Strawberry"),
                new ProductTypeResponseDTO(UUID.randomUUID(), "Pear")
        );
    }

    void setUpProductTypeCollectionModel() {
        var productTypeEntityModelList = productTypeResponseDTOList.stream().map(
                productTypeDTO -> {
                    var productTypeId = productTypeDTO.getId();
                    var selfLink = linkTo(methodOn(ProductTypeController.class).one(productTypeId)).withSelfRel();
                    var allLink = linkTo(methodOn(ProductTypeController.class).all()).withRel("product_types");
                    return EntityModel.of(productTypeDTO, selfLink, allLink);
                }).toList();

        productTypeCollectionModel = CollectionModel.of(productTypeEntityModelList);
    }

    @Test
    void testGetAll() throws Exception {
        when(productTypeAssembler.toCollectionModel(any())).thenReturn(productTypeCollectionModel);
        mockMvc.perform(get("/product_types").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
}
