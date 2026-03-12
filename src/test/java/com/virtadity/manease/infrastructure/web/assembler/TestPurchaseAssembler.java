package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseDTOMapper;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseDTOMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestPurchaseAssembler {

    private PurchaseAssembler purchaseAssembler;
    private final PurchaseDTOMapper purchaseDTOMapper = new PurchaseDTOMapperImpl();

    @BeforeEach
    public void setUp() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        purchaseAssembler = new PurchaseAssembler(purchaseDTOMapper);
    }

    @AfterEach
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void testPurchaseAssemblingToModel() {
        var purchaseId = UUID.randomUUID();
        var description = "Test purchase description";
        var creationDate = LocalDateTime.now();
        var deliveryDate = LocalDateTime.now();
        var producerId = UUID.randomUUID();

        var purchaseResponse = new PurchaseResponse(purchaseId, description, creationDate, deliveryDate, producerId);
        var purchaseModel = purchaseAssembler.toModel(purchaseResponse);

        assertThat(purchaseModel.getContent().getId()).isNotNull().isEqualTo(purchaseId);
        assertThat(purchaseModel.getContent().getDescription()).isNotNull().isEqualTo(description);
        assertThat(purchaseModel.getContent().getCreationDate()).isNotNull().isEqualTo(creationDate);
        assertThat(purchaseModel.getContent().getDeliveryDate()).isNotNull().isEqualTo(deliveryDate);
        assertThat(purchaseModel.getContent().getProducerId()).isNotNull().isEqualTo(producerId);

        assertThat(purchaseModel.getLink("self")).isPresent();
        assertThat(purchaseModel.getLink("purchases")).isPresent();

        var path = String.format("/purchases/%s", purchaseId);
        assertThat(purchaseModel.getRequiredLink("self").getHref().contains(path));

        var pathToProducer = String.format("/producers/%s", producerId);
        assertThat(purchaseModel.getRequiredLink("producer").getHref().contains(pathToProducer));
    }
}
