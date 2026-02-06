package com.virtadity.manease.service;

import com.virtadity.manease.dto.ReportDTO;

public interface ReportService {
    ReportDTO makeReportBetweenDates(String fromDate, String toDate);
}
