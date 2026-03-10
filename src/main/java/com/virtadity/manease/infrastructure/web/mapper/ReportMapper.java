package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.report.ReportResponse;
import com.virtadity.manease.application.model.report_line.ReportLineResponse;
import com.virtadity.manease.infrastructure.web.dto.report.ReportLineResponseDTO;
import com.virtadity.manease.infrastructure.web.dto.report.ReportResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReportMapper {
    @Mapping(target = "reportLines",
            expression = "java(reportLineAssembler.toCollectionModel(reportResponse.reportLineResponseList()))"
    )
    ReportResponseDTO toReportResponseDTO(
            ReportResponse reportResponse,
            RepresentationModelAssembler<ReportLineResponse, EntityModel<ReportLineResponseDTO>> reportLineAssembler
    );
}
