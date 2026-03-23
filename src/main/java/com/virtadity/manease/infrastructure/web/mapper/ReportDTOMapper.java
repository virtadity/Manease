package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.report.ReportResponse;
import com.virtadity.manease.infrastructure.web.dto.report.ReportLineResponseDTO;
import com.virtadity.manease.infrastructure.web.dto.report.ReportResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.hateoas.EntityModel;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReportDTOMapper {
    ReportResponseDTO toReportResponseDTO(
            ReportResponse reportResponse,
            List<EntityModel<ReportLineResponseDTO>> reportLines
    );
}
