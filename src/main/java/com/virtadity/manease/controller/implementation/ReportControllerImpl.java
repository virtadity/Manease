package com.virtadity.manease.controller.implementation;

import com.virtadity.manease.controller.ReportController;
import com.virtadity.manease.dto.ReportDTO;
import com.virtadity.manease.service.ReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportControllerImpl implements ReportController {

    private final ReportService reportService;

    public ReportControllerImpl(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public ReportDTO makeReportBetweenDates(String fromDate, String toDate) {
        return this.reportService.makeReportBetweenDates(fromDate, toDate);
    }
}
