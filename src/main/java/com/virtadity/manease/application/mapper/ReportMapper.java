package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.report.ReportResponse;
import com.virtadity.manease.domain.model.Report;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = ReportLineMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ReportMapper {

    ReportResponse toReportResponse(Report report);
}
