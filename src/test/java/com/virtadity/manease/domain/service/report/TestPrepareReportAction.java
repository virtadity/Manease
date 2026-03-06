package com.virtadity.manease.domain.service.report;


import com.virtadity.manease.application.model.report_line.ReportLineResponse;
import com.virtadity.manease.domain.model.Report;
import com.virtadity.manease.domain.model.ReportLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestPrepareReportAction {

    private final PrepareReportAction prepareReportAction = new PrepareReportAction();

    private final List<UUID> purchaseIdList = List.of(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
    );

    private final List<LocalDateTime> deliveryDateTimeList = List.of(
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now()
    );

    private final List<LocalDateTime> creationDateTimeList = List.of(
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now()
    );

    private final List<BigDecimal> totalPurchaseWeightList = List.of(
            BigDecimal.valueOf(111.1),
            BigDecimal.valueOf(222.2),
            BigDecimal.valueOf(333.3),
            BigDecimal.valueOf(444.4),
            BigDecimal.valueOf(555.5)
    );

    private final List<BigDecimal> totalPurchaseCostList = List.of(
            BigDecimal.valueOf(1000),
            BigDecimal.valueOf(2000),
            BigDecimal.valueOf(3000),
            BigDecimal.valueOf(4000),
            BigDecimal.valueOf(5000)
    );

    private List<ReportLine> reportLineList;

    private void setUpReportLineList() {
        reportLineList = new ArrayList<>();
        for (int index = 0; index < purchaseIdList.size(); index++) {
            var purchaseId = purchaseIdList.get(index);
            var deliveryDate = deliveryDateTimeList.get(index);
            var creationDate = creationDateTimeList.get(index);
            var totalPurchaseWeight = totalPurchaseWeightList.get(index);
            var totalPurchaseCost = totalPurchaseCostList.get(index);
            var reportLine = new ReportLine(
                    deliveryDate,
                    creationDate,
                    purchaseId,
                    totalPurchaseWeight,
                    totalPurchaseCost
            );
            reportLineList.add(reportLine);
        }
    }

    @BeforeEach
    void setUp() {
        setUpReportLineList();
    }

    private BigDecimal sum(List<BigDecimal> numbers) {
        return numbers.stream().parallel().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Test
    void testPrepareReportAction() {
        var totalWeightExpected = sum(totalPurchaseWeightList);
        var totalCostExpected = sum(totalPurchaseCostList);
        var beforeDate = LocalDateTime.now();
        var afterDate = LocalDateTime.now();
        var reportExpected = new Report(afterDate, beforeDate, totalCostExpected, totalWeightExpected, reportLineList);
        var reportActual = prepareReportAction.execute(reportLineList, beforeDate, afterDate);
        assertThat(reportActual).isNotNull().isEqualTo(reportExpected);
    }

}
