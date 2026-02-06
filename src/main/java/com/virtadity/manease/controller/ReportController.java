package com.virtadity.manease.controller;

import com.virtadity.manease.dto.ReportDTO;

public interface ReportController {

    ReportDTO makeReportBetweenDates(String fromDate, String toDate);
}
