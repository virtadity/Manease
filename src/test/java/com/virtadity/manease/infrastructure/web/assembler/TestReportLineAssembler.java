package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.report_line.ReportLineResponse;
import com.virtadity.manease.infrastructure.web.mapper.ReportLineDTOMapper;
import com.virtadity.manease.infrastructure.web.mapper.ReportLineDTOMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestReportLineAssembler {

    private ReportLineAssembler reportLineAssembler;
    private final ReportLineDTOMapper reportLineDTOMapper = new ReportLineDTOMapperImpl();

    @BeforeEach
    public void setUp() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        reportLineAssembler = new ReportLineAssembler(reportLineDTOMapper);
    }

    @AfterEach
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void testReportLineAssemblingToModel() {
        var deliveryDateTime = LocalDateTime.now().minusHours(10);
        var creationDateTime = LocalDateTime.now().minusHours(100);
        var purchaseId = UUID.randomUUID();
        var totalPurchaseWeight = BigDecimal.valueOf(100.0);
        var totalPurchaseCost = BigDecimal.valueOf(100.0);

        var reportLineResponse = new ReportLineResponse(
                deliveryDateTime,
                creationDateTime,
                purchaseId,
                totalPurchaseWeight,
                totalPurchaseCost
        );

        var reportLineModel = reportLineAssembler.toModel(reportLineResponse);

        assertThat(reportLineModel.getContent().getPurchaseId()).isNotNull().isEqualTo(purchaseId);
        assertThat(reportLineModel.getContent().getDeliveryDate()).isNotNull().isEqualTo(deliveryDateTime);
        assertThat(reportLineModel.getContent().getCreationDate()).isNotNull().isEqualTo(creationDateTime);
        assertThat(reportLineModel.getContent().getTotalPurchaseWeight()).isNotNull().isEqualTo(totalPurchaseWeight);
        assertThat(reportLineModel.getContent().getTotalPurchaseCost()).isNotNull().isEqualTo(totalPurchaseCost);

        assertThat(reportLineModel.getLink("purchase")).isPresent();

        var pathToPurchase = String.format("/purchases/%s", purchaseId);
        assertThat(reportLineModel.getRequiredLink("purchase").getHref()).contains(pathToPurchase);
    }
}
