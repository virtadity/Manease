package com.virtadity.manease.application.service.report;

import com.virtadity.manease.application.mapper.ReportMapper;
import com.virtadity.manease.application.model.report.ReportResponse;
import com.virtadity.manease.application.port.in.report.ReportInputBoundary;
import com.virtadity.manease.application.port.out.report_line.ReportLineOutputBoundary;
import com.virtadity.manease.domain.service.PrepareReportAction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class ReportService implements ReportInputBoundary {
    private final ReportMapper reportMapper;
    private final ReportLineOutputBoundary reportLineStorage;
    private final PrepareReportAction prepareReportAction;

    @Override
    public ReportResponse execute(LocalDateTime fromDate, LocalDateTime toDate) {
        var reportLineList = reportLineStorage.getAllBetweenDates(fromDate, toDate);
        var report = prepareReportAction.execute(reportLineList, fromDate, toDate);
        return reportMapper.toReportResponse(report);
    }
}
