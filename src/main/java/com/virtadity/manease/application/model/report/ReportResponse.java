package com.virtadity.manease.application.model.report;

import com.virtadity.manease.domain.model.ReportLine;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ReportResponse(
        LocalDateTime fromDate,
        LocalDateTime toDate,
        BigDecimal totalCost,
        BigDecimal totalWeight,
        List<ReportLine> reportLineList
) {
}
