package com.virtadity.manease.infrastructure.database.dao;

import com.virtadity.manease.application.port.out.report_line.ReportLineOutputBoundary;
import com.virtadity.manease.domain.model.ReportLine;
import com.virtadity.manease.infrastructure.database.mapper.ReportLineReadModelMapper;
import com.virtadity.manease.infrastructure.database.read_model.ReportLineReadModel;
import com.virtadity.manease.infrastructure.database.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class ReportLineDAO implements ReportLineOutputBoundary {
    private final ReportLineReadModelMapper reportLineReadModelMapper;
    private final PurchaseRepository purchaseRepository;

    @Override
    public List<ReportLine> getAllBetweenDates(LocalDateTime fromDate, LocalDateTime toDate) {
        var reportLineListBetweenDates = purchaseRepository.getReportLineBetweenDates(fromDate, toDate);
        return reportLineReadModelMapper.toReportLineList(reportLineListBetweenDates);
    }
}
