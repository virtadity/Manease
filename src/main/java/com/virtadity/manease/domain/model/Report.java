package com.virtadity.manease.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record Report(
        LocalDateTime fromDate,
        LocalDateTime toDate,
        BigDecimal totalCost,
        BigDecimal totalWeight,
        List<ReportLine> reportLineList
) {
    public static Report fromRecordLineList(
            List<ReportLine> reportLineList,
            LocalDateTime fromDate,
            LocalDateTime toDate
    ) {

        var totalCost = reportLineList
                .parallelStream()
                .map(ReportLine::totalSupplyCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalWeight = reportLineList
                .parallelStream()
                .map(ReportLine::totalSupplyWeight)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Report(fromDate, toDate, totalCost, totalWeight, reportLineList);
    }
}
