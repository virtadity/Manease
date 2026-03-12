package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.report_line.ReportLineResponse;
import com.virtadity.manease.infrastructure.web.dto.report.ReportLineResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReportLineDTOMapper {
    ReportLineResponseDTO toReportLineResponseDTO(ReportLineResponse reportLineResponse);
    List<ReportLineResponseDTO> toReportLineResponseDTOList(List<ReportLineResponse> reportLineResponseList);
}
