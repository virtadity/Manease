package com.virtadity.manease.application.port.out.report_line;

import com.virtadity.manease.domain.model.ReportLine;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportLineOutputBoundary {
    List<ReportLine> getAllBetweenDates(LocalDateTime fromDate, LocalDateTime toDate);
}
