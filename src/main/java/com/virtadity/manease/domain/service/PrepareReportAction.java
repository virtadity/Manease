package com.virtadity.manease.domain.service;

import com.virtadity.manease.domain.model.Report;
import com.virtadity.manease.domain.model.ReportLine;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public class PrepareReportAction {

    public Report execute(List<ReportLine> reportLineList, LocalDateTime fromDate, LocalDateTime toDate) {
        var totalCost = reportLineList
                .parallelStream()
                .map(ReportLine::totalPurchaseCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalWeight = reportLineList
                .parallelStream()
                .map(ReportLine::totalPurchaseWeight)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Report(fromDate, toDate, totalCost, totalWeight, reportLineList);
    }
}
