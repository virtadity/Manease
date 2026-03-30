package com.virtadity.manease.application.service.report;

import com.virtadity.manease.application.mapper.ReportMapper;
import com.virtadity.manease.application.model.report.ReportResponse;
import com.virtadity.manease.application.port.out.report.line.ReportLineOutputBoundary;
import com.virtadity.manease.domain.model.Report;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestReportService {

    @Mock
    private ReportMapper reportMapper;

    @Mock
    private ReportLineOutputBoundary reportLineStorage;

    @InjectMocks
    private ReportService reportService;

    @Test
    void testPrepareReport() {
        var reportBeforeDate = LocalDateTime.now();
        var reportAfterDate = LocalDateTime.now().minusYears(15);

        var report = new Report(
                reportAfterDate,
                reportBeforeDate,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                List.of()
        );

        var reportResponse = new ReportResponse(
                reportAfterDate,
                reportBeforeDate,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                List.of()
        );

        when(reportMapper.toReportResponse(report)).thenReturn(reportResponse);
        when(reportLineStorage.getAllBetweenDates(reportAfterDate, reportBeforeDate)).thenReturn(List.of());

        var actualReportResponse = reportService.execute(reportAfterDate, reportBeforeDate);
        assertThat(actualReportResponse).isNotNull().isEqualTo(reportResponse);
    }
}
