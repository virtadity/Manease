package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.report.line.ReportLineResponse;
import com.virtadity.manease.domain.model.ReportLine;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestReportLineMapper {
    private final ReportLineMapper reportLineMapper = new ReportLineMapperImpl();

    @Test
    void testReportLineToReportLineResponse(){
        var deliveryDate = LocalDateTime.now();
        var creationDate = LocalDateTime.now();
        var purchaseId = UUID.randomUUID();
        var totalPurchaseWeight = BigDecimal.valueOf(100.0);
        var totalPurchaseCost = BigDecimal.valueOf(100.0);

        var reportLine = new ReportLine(deliveryDate, creationDate, purchaseId, totalPurchaseWeight, totalPurchaseCost);
        var expectedReportLineResponse = new ReportLineResponse(
                deliveryDate,
                creationDate,
                purchaseId,
                totalPurchaseWeight,
                totalPurchaseCost
        );
        var actualReportLineResponse = reportLineMapper.toReportLineResponse(reportLine);

        assertThat(actualReportLineResponse).isNotNull().isEqualTo(expectedReportLineResponse);
    }
}
