package com.virtadity.manease.application.service.product.type;

import com.virtadity.manease.application.mapper.ProductTypeMapper;
import com.virtadity.manease.application.model.product.type.ProductTypeResponse;
import com.virtadity.manease.application.port.out.product.type.ProductTypeGetAllOutputBoundary;
import com.virtadity.manease.domain.model.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestProductTypeGetAllService {
    @Mock
    private ProductTypeGetAllOutputBoundary productTypeGetAllStorage;

    @Mock
    private ProductTypeMapper productTypeMapper;

    @InjectMocks
    private ProductTypeGetAllService productTypeGetAllService;

    private final List<UUID> productTypeIds = List.of(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
    );

    private final List<String> productTypeNames = List.of(
            "Яблоки",
            "Мандарины",
            "Апельсины",
            "Бананы",
            "Вишня"
    );

    private List<ProductType> productTypes;
    private List<ProductTypeResponse> productTypeResponses;

    private List<ProductType> setUpProductTypes(List<UUID> productTypeIds, List<String> productTypeNames) {
        var productTypes = new ArrayList<ProductType>(productTypeIds.size());
        for (int i = 0; i < productTypeIds.size(); i++) {
            productTypes.add(new ProductType(productTypeIds.get(i), productTypeNames.get(i)));
        }
        return productTypes;
    }

    private List<ProductTypeResponse> setUpProductTypeResponses(
            List<UUID> productTypeIds,
            List<String> productTypeNames) {

        var productTypeResponses = new ArrayList<ProductTypeResponse>(productTypeIds.size());
        for (int i = 0; i < productTypeIds.size(); i++ ){
            productTypeResponses.add(new ProductTypeResponse(productTypeIds.get(i), productTypeNames.get(i)));
        }
        return productTypeResponses;
    }

    @BeforeEach
    void setUp() {
        productTypes = setUpProductTypes(productTypeIds, productTypeNames);
        productTypeResponses = setUpProductTypeResponses(productTypeIds, productTypeNames);
    }

    @Test
    void testProductTypeGetAll() {
        when(productTypeMapper.toProductTypeResponseList(productTypes)).thenReturn(productTypeResponses);
        when(productTypeGetAllStorage.getAll()).thenReturn(productTypes);

        var actualProductTypeResponseList = productTypeGetAllService.execute();
        assertThat(actualProductTypeResponseList)
                .isNotNull()
                .isEqualTo(productTypeResponses);
    }
}
