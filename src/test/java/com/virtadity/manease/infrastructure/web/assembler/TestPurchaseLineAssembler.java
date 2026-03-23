package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseLineDTOMapper;
import com.virtadity.manease.infrastructure.web.mapper.PurchaseLineDTOMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestPurchaseLineAssembler {
    private PurchaseLineAssembler purchaseLineAssembler;
    private final PurchaseLineDTOMapper purchaseLineDTOMapper = new PurchaseLineDTOMapperImpl();

    @BeforeEach
    public void setUp() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        purchaseLineAssembler = new PurchaseLineAssembler(purchaseLineDTOMapper);
    }

    @AfterEach
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void testPurchaseAssemblingToModel() {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        var price = BigDecimal.valueOf(100.0);
        var quantity = BigInteger.valueOf(100);

        var purchaseLineResponse = new PurchaseLineResponse(purchaseId, productId, price, quantity);
        var purchaseLineModel = purchaseLineAssembler.toModel(purchaseLineResponse);

        assertThat(purchaseLineModel.getContent().getPurchaseId()).isNotNull().isEqualTo(purchaseId);
        assertThat(purchaseLineModel.getContent().getProductId()).isNotNull().isEqualTo(productId);
        assertThat(purchaseLineModel.getContent().getPrice()).isNotNull().isEqualTo(price);
        assertThat(purchaseLineModel.getContent().getQuantity()).isNotNull().isEqualTo(quantity);

        assertThat(purchaseLineModel.getLink("self")).isPresent();
        assertThat(purchaseLineModel.getLink("purchase_lines")).isPresent();

        var pathToPurchase = String.format("/purchases/%s", purchaseId);
        assertThat(purchaseLineModel.getRequiredLink("purchase").getHref())
                .isNotNull()
                .contains(pathToPurchase);

        var pathToProduct = String.format("/products/%s", productId);
        assertThat(purchaseLineModel.getRequiredLink("product").getHref())
                .isNotNull()
                .contains(pathToProduct);
    }
}
