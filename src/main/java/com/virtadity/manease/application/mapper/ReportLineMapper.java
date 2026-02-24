package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.report_line.ReportLineResponse;
import com.virtadity.manease.domain.model.ReportLine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReportLineMapper {
    ReportLineResponse toReportLineResponse(ReportLine reportLine);
    List<ReportLineResponse> toReportLineResponseList(List<ReportLine> reportLine);
}
