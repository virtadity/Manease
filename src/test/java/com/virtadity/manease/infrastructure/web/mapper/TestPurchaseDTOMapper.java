package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.infrastructure.web.dto.purchase.PurchaseRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.purchase.PurchaseResponseDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestPurchaseDTOMapper {
    private final PurchaseDTOMapper purchaseDTOMapper = new PurchaseDTOMapperImpl();

    @Test
    public void testMappingToPurchaseRequest() {
        var id = UUID.randomUUID();
        var description = "Test purchase description";
        var creationDate = LocalDateTime.now();
        var deliveryDate = LocalDateTime.now();
        var producerId = UUID.randomUUID();

        var purchaseRequestDTO = new PurchaseRequestDTO(id, description, creationDate, deliveryDate, producerId);
        var expectedPurchaseRequest = purchaseDTOMapper.toPurchaseRequest(purchaseRequestDTO);
        var actualPurchaseRequest = purchaseDTOMapper.toPurchaseRequest(purchaseRequestDTO);

        assertThat(actualPurchaseRequest).isNotNull().isEqualTo(expectedPurchaseRequest);
    }

    @Test
    public void testMappingToPurchaseResponseDTO() {
        var id = UUID.randomUUID();
        var description = "Test purchase description";
        var creationDate = LocalDateTime.now();
        var deliveryDate = LocalDateTime.now();
        var producerId = UUID.randomUUID();

        var purchaseResponse = new PurchaseResponse(id, description, creationDate, deliveryDate, producerId);
        var expectedPurchaseResponseDTO =
                new PurchaseResponseDTO(id, description, creationDate, deliveryDate, producerId);
        var actualPurchaseResponseDTO = purchaseDTOMapper.toPurchaseResponseDTO(purchaseResponse);

        assertThat(actualPurchaseResponseDTO).isNotNull().isEqualTo(expectedPurchaseResponseDTO);
    }
}
