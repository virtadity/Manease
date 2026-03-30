package com.virtadity.manease.application.model.report;

import com.virtadity.manease.application.model.report.line.ReportLineResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ReportResponse(
        LocalDateTime afterDate,
        LocalDateTime beforeDate,
        BigDecimal totalCost,
        BigDecimal totalWeight,
        List<ReportLineResponse> reportLineResponseList
) {
}
