package com.virtadity.manease.application.service.report;

import com.virtadity.manease.application.mapper.ReportMapper;
import com.virtadity.manease.application.model.report.ReportResponse;
import com.virtadity.manease.application.port.in.report.ReportInputBoundary;
import com.virtadity.manease.application.port.out.report_line.ReportLineOutputBoundary;
import com.virtadity.manease.domain.model.Report;
import com.virtadity.manease.domain.model.ReportLine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class ReportService implements ReportInputBoundary {
    private final ReportMapper reportMapper;
    private final ReportLineOutputBoundary reportLineStorage;

    @Override
    public ReportResponse execute(LocalDateTime fromDate, LocalDateTime toDate) {
        var reportLineList = reportLineStorage.getAllBetweenDates(fromDate, toDate);

        var totalCost = reportLineList
                .parallelStream()
                .map(ReportLine::totalSupplyCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalWeight = reportLineList
                .parallelStream()
                .map(ReportLine::totalSupplyWeight)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var report = new Report(fromDate, toDate, totalCost, totalWeight, reportLineList);
        return reportMapper.toReportResponse(report);
    }
}
