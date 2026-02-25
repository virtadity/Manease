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
) {}
