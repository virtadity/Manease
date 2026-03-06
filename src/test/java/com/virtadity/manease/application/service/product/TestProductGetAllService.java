package com.virtadity.manease.application.service.product;

import com.virtadity.manease.application.mapper.ProductMapper;
import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.application.port.out.product.ProductGetAllOutputBoundary;
import com.virtadity.manease.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestProductGetAllService {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductGetAllOutputBoundary productGetAllStorage;

    @InjectMocks
    private ProductGetAllService productGetAllService;

    private final List<UUID> productIdList = List.of(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
    );

    private final List<String> productNameList = List.of(
            "Red sort apples",
            "Yellow sort apples",
            "Green sort apples",
            "Gold sort apples",
            "Blue sort apples"
    );

    private final List<BigDecimal> productWeightList = List.of(
            BigDecimal.valueOf(100.0),
            BigDecimal.valueOf(110.0),
            BigDecimal.valueOf(120.0),
            BigDecimal.valueOf(130.0),
            BigDecimal.valueOf(140.0)
    );

    private final List<UUID> producerIdList = List.of(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
    );

    private final List<UUID> productTypeIdList = List.of(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
    );

    private List<Product> productList;
    private List<ProductResponse> productResponseList;

    private void setUpProductList() {
        this.productList = new ArrayList<>(productIdList.size());
        for (int index = 0; index < productIdList.size(); index++) {
            var product = new Product(
                    productIdList.get(index),
                    productNameList.get(index),
                    productWeightList.get(index),
                    producerIdList.get(index),
                    productTypeIdList.get(index)
            );
            productList.add(product);
        }
    }

    private void setUpProductResponseList() {
        this.productResponseList = new ArrayList<>(producerIdList.size());
        for (int index = 0; index < productIdList.size(); index++) {
            var productResponse = new ProductResponse(
                    productIdList.get(index),
                    productNameList.get(index),
                    productWeightList.get(index),
                    producerIdList.get(index),
                    productTypeIdList.get(index)
            );
            productResponseList.add(productResponse);
        }
    }

    @BeforeEach
    void setUp() {
        setUpProductList();
        setUpProductResponseList();
    }

    @Test
    void testProductGetAll() {
        when(productMapper.toProductResponseList(productList)).thenReturn(productResponseList);
        when(productGetAllStorage.getAll()).thenReturn(productList);

        var actualProductResponseList = productGetAllService.execute();
        assertThat(actualProductResponseList)
                .isNotNull()
                .isEqualTo(productResponseList);
    }
}
