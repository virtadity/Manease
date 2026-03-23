package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.purchase_line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;
import com.virtadity.manease.infrastructure.web.dto.purchase_line.PurchaseLineRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.purchase_line.PurchaseLineResponseDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestPurchaseLineDTOMapper {

    private final PurchaseLineDTOMapper purchaseLineDTOMapper = new PurchaseLineDTOMapperImpl();

    @Test
    public void testMappingToPurchaseLineRequest() {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        var price = BigDecimal.valueOf(100.0);
        var quantity = BigInteger.valueOf(100);

        var purchaseLineRequestDTO = new PurchaseLineRequestDTO(purchaseId, productId, price, quantity);
        var expectedPurchaseLineRequest = new PurchaseLineRequest(purchaseId, productId, price, quantity);
        var actualPurchaseLineRequest = purchaseLineDTOMapper.toPurchaseLineRequest(purchaseLineRequestDTO);
        assertThat(actualPurchaseLineRequest).isNotNull().isEqualTo(expectedPurchaseLineRequest);
    }

    @Test
    public void testMappingToPurchaseLineResponseDTO() {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        var price = BigDecimal.valueOf(100.0);
        var quantity = BigInteger.valueOf(100);

        var purchaseLineResponse = new PurchaseLineResponse(purchaseId, productId, price, quantity);
        var expectedPurchaseLineResponseDTO = new PurchaseLineResponseDTO(purchaseId, productId, price, quantity);
        var actualPurchaseLineResponseDTO = purchaseLineDTOMapper.toPurchaseLineResponseDTO(purchaseLineResponse);
        assertThat(actualPurchaseLineResponseDTO).isNotNull().isEqualTo(expectedPurchaseLineResponseDTO);
    }

}
