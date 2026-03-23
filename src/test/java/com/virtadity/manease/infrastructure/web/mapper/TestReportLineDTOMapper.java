package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.report_line.ReportLineResponse;
import com.virtadity.manease.infrastructure.web.dto.report.ReportLineResponseDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestReportLineDTOMapper {
    private final ReportLineDTOMapper reportLineDTOMapper = new ReportLineDTOMapperImpl();

    @Test
    public void testMappingToReportLineResponseDTO() {
        var purchaseId = UUID.randomUUID();
        var deliveryDate = LocalDateTime.now();
        var creationDate = LocalDateTime.now();
        var totalPurchaseWeight = BigDecimal.valueOf(100.0);
        var totalPurchaseCost = BigDecimal.valueOf(100.0);

        var reportLineResponse =
                new ReportLineResponse(deliveryDate, creationDate, purchaseId, totalPurchaseWeight, totalPurchaseCost);

        var expectedLineResponseDTO =
                new ReportLineResponseDTO(
                        purchaseId,
                        deliveryDate,
                        creationDate,
                        totalPurchaseWeight,
                        totalPurchaseCost
                );

        var actualLineResponseDTO = reportLineDTOMapper.toReportLineResponseDTO(reportLineResponse);
        assertThat(actualLineResponseDTO).isNotNull().isEqualTo(expectedLineResponseDTO);
    }
}
