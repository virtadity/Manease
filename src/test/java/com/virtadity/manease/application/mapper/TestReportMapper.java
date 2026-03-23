package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.report.ReportResponse;
import com.virtadity.manease.domain.model.Report;
import com.virtadity.manease.domain.model.ReportLine;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestReportMapper {

    private final ReportLineMapper reportLineMapper = new ReportLineMapperImpl();
    private final ReportMapper reportMapper = new ReportMapperImpl(reportLineMapper);

    @Test
    void testReportToReportResponse() {
        var reportLineList = prepareReportLineList();
        var reportLineResponseList = reportLineMapper.toReportLineResponseList(reportLineList);
        var afterDate = LocalDateTime.now();
        var beforeDate = LocalDateTime.now();
        var totalCost = BigDecimal.valueOf(450.0);
        var totalWeight = BigDecimal.valueOf(450.0);

        var report = new Report(afterDate, beforeDate, totalCost, totalWeight, reportLineList);

        var expectedReportResponse = new ReportResponse(
                afterDate,
                beforeDate,
                totalCost,
                totalWeight,
                reportLineResponseList
        );

        var actualReportResponse = reportMapper.toReportResponse(report);
        assertThat(actualReportResponse).isNotNull().isEqualTo(expectedReportResponse);
    }

    private List<ReportLine> prepareReportLineList() {
        return List.of(
                new ReportLine(
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        UUID.randomUUID(),
                        BigDecimal.valueOf(100.0),
                        BigDecimal.valueOf(100.0)
                ),
                new ReportLine(
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        UUID.randomUUID(),
                        BigDecimal.valueOf(150.0),
                        BigDecimal.valueOf(150.0)
                ),
                new ReportLine(
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        UUID.randomUUID(),
                        BigDecimal.valueOf(200.0),
                        BigDecimal.valueOf(200.0)
                )
        );
    }
}
