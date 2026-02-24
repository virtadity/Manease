package com.virtadity.manease.application.port.in.report;

import com.virtadity.manease.application.model.report.ReportResponse;

import java.time.LocalDateTime;

public interface ReportInputBoundary {
    ReportResponse execute(LocalDateTime fromDate, LocalDateTime toDate);
}
