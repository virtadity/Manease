package com.virtadity.manease.application.service.purchase_line;

import com.virtadity.manease.application.mapper.PurchaseLineMapper;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;
import com.virtadity.manease.application.port.out.purchase_line.PurchaseLineGetAllOutputBoundary;
import com.virtadity.manease.domain.model.PurchaseLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestPurchaseLineGetAllService {

    @Mock
    private PurchaseLineMapper purchaseLineMapper;

    @Mock
    private PurchaseLineGetAllOutputBoundary purchaseLineGetAllStorage;

    @InjectMocks
    private PurchaseLineGetAllService purchaseLineGetAllService;

    private final List<UUID> purchaseIdList = List.of(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
    );

    private final List<UUID> productIdList = List.of(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
    );

    private final List<BigDecimal> priceList = List.of(
        BigDecimal.valueOf(100.0),
        BigDecimal.valueOf(101.1),
        BigDecimal.valueOf(102.2),
        BigDecimal.valueOf(103.3),
        BigDecimal.valueOf(104.4)
    );

    private final List<BigInteger> quantityList = List.of(
            BigInteger.valueOf(10),
            BigInteger.valueOf(10),
            BigInteger.valueOf(10),
            BigInteger.valueOf(10),
            BigInteger.valueOf(10)
    );

    private List<PurchaseLine> purchaseLineList;
    private List<PurchaseLineResponse> purchaseLineResponseList;

    private void setPurchaseLineList() {
        purchaseLineList = new ArrayList<>();
        for (int i = 0; i < purchaseIdList.size(); i++) {
            var purchaseId = purchaseIdList.get(i);
            var productId = productIdList.get(i);
            var price = priceList.get(i);
            var quantity = quantityList.get(i);
            var purchaseLine = new PurchaseLine(purchaseId, productId, price, quantity);
            purchaseLineList.add(purchaseLine);
        }
    }

    private void setPurchaseLineResponseList() {
        purchaseLineResponseList = new ArrayList<>();
        for (int i = 0; i < purchaseIdList.size(); i++) {
            var purchaseId = purchaseIdList.get(i);
            var productId = productIdList.get(i);
            var price = priceList.get(i);
            var quantity = quantityList.get(i);
            var purchaseLineResponse = new PurchaseLineResponse(purchaseId, productId, price, quantity);
            purchaseLineResponseList.add(purchaseLineResponse);
        }
    }

    @BeforeEach
    void setUp() {
        setPurchaseLineList();
        setPurchaseLineResponseList();
    }

    @Test
    void testPurchaseLineGetAll() {
        when(purchaseLineMapper.toPurchaseLineResponseList(purchaseLineList)).thenReturn(purchaseLineResponseList);
        when(purchaseLineGetAllStorage.getAll()).thenReturn(purchaseLineList);

        var actualPurchaseLineGetAll = purchaseLineGetAllService.execute();
        assertThat(actualPurchaseLineGetAll)
                .isNotNull()
                .isEqualTo(purchaseLineResponseList);
    }

}
